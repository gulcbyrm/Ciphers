package network;

import entities.Gorev;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import ui.ServerController;
import utility.Listeler;

public class ServerWorker extends Thread {

    //sınıfın değişkenleri
    public final Socket socket;
    private final ObjectOutputStream objectOutputStream;
    private final ObjectInputStream objectInputStream;
    public int id;

    
    
    
    //Constructure
    public ServerWorker(Socket socket) throws IOException {
        this.id = setWorkerID();
        this.socket = socket;
        this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        this.objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    
    
    
    //id atamasını sırasıyla 1,2,3... diye verecektir
    private final int setWorkerID() {
        return ++Server.uniqueID;
    }

    
    
    
    // thread'in run metodu: sonsuz döngüde gelecek nesneyi beklemektedir
    @Override
    public void run() {
        Object received;

        while (true) {
            try {
                if (socket.isClosed()) {
                    break;
                }
                
                received = this.objectInputStream.readObject();
                if (isClientSendCLOSE(received)) {
                    break;
                }
                
                listGeneleEkle("Client " + this.id + " Gelen", received);
                //tüm clientlerin threadlerini durdurması için mesaj
                if(isCliendSendParolaKirildi(received)){
                    ServerController.getServer().broadCast("ŞİFRE KIRILDI", this);
                    break;
                }
                

            } catch (IOException | ClassNotFoundException e) {
                Listeler.arkaPlandaGenelListeEkle(socket + " client kapatıldı. data çekilemiyor. Detay:  " + e.getMessage());
                break;
            }
        }

        closeThisSocket();
        removeThisWorker();
    }

    
    
    
    //statik olan worker listesinden bu workeri siler
    public synchronized void removeThisWorker() {
        Listeler.removeWorker(this);
    }

    
    
    
    //soketi kapatır ve worker listten clienti çıkarır
    //objectOutputStream, objectInputStream nesnelerinde .close() kullanmadım çünkü soketin kapatılması zaten bu iki nesneyi de kapatacaktır
    public synchronized void closeThisSocket() {
        try {
            if (!socket.isClosed()) {
                //soketi kapat
                socket.close();
                Listeler.arkaPlandaGenelListeEkle(this.id + " id ye sahip Client ayrıldı...");
            }
        } catch (IOException e) {
            Listeler.arkaPlandaGenelListeEkle("Kapatma işlemleri yürütülürken hata oluştu detay: " + e.getMessage());
        }
    }

    
    
    
    
    //Nesne gönderir
    public synchronized boolean sendOBJ(Object obj) {
        try {
            if (isSocketConnected()) {
                //Cliente Gönder
                this.objectOutputStream.writeObject(obj);
                this.objectOutputStream.flush();
                //Listeye yaz
                listGeneleEkle(this.id+" Cliente Gönderilen: ", obj);
                return true;
            }
        } catch (IOException ex) {
            Listeler.arkaPlandaGenelListeEkle("Görev gönderilemedi detay: " + ex.getMessage());
            return false;
        }
        return false;
    }

    
    
    
    
    private synchronized void listGeneleEkle(String baslik, Object obj) {
        if (obj instanceof String) {
            Listeler.arkaPlandaGenelListeEkle(baslik + " Metin: " + obj.toString());
        } else {
            Listeler.arkaPlandaGenelListeEkle(baslik + " Nesne: " + ((Gorev) obj).toString());
        }
    }

    
    
    
    
    public synchronized boolean isSocketConnected() {
        if (!socket.isConnected()) {
            closeThisSocket();
            removeThisWorker();
            Listeler.arkaPlandaGenelListeEkle("Client kapalı.");
            return false;
        }
        return true;
    }

    
    
    
    
    private boolean isClientSendCLOSE(Object received) { 
        return received instanceof String && received.equals("SON");
    }
    
    
    
    private boolean isCliendSendParolaKirildi(Object received) {        
        return (received instanceof String) && ((String)received).contains("Parola Kırıldı SONUÇ:");
    }
    
    
    

    @Override
    public String toString() {
        return "Client " + id
                + "\n-----------------------------------------"
                + "\nDurumu\t: " + (socket.isClosed() ? "Kapalı" : "Aktif")
                + "\nPort\t\t: " + socket.getPort()
                + "\nAdresi\t: " + socket.getInetAddress()
                + "\nLocal Port\t: " + socket.getLocalPort() + "\n\n";
    }


}
