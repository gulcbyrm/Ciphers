package network;

import parolaKir.Sha256;
import entities.Gorev;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import lombok.Getter;
import utility.Listeler;


public class Client {
     
    
    //variables
    @Getter private Socket socket;
    private final int port;
    private final InetAddress serverIp;
    private ObjectOutputStream objectOutput;
    private ObjectInputStream objectInput;
    private int clientinIsleyecegiPart=-1; //partNo görevin hangi kısmını bu clientin işleyeceğini belirler ve server tarafından atanır
    
    
    
    //Contructure
    public Client(final int port, final InetAddress serverIp) {
        this.port = port;
        this.serverIp = serverIp;
    }

    
    
    
    
    //bu metod serverden gelen part bildirimlerini algılar
    private void gelenMsgPartBildirimMi(String gelenString) {
        if(gelenString.contains("PartNo: ")) clientinIsleyecegiPart=Integer.valueOf(gelenString.substring(8));
    }

    
    
    
    
    private void gelenMsgSifreKirildiBildirimiMi(String gelenString) {
    //bu metod serverden gelen part bildirimlerini algılar
        if(gelenString.contains("ŞİFRE KIRILDI")) {
            servereBaglantiyiKes();
            System.out.println("server bağlantıyı kes gönderdi client no:");
        }
    }    

    

   
   
    // İnner Class
    // Thread olarak çalışacak olan inner sınıf
    public class ListenerForInput extends Thread {
        
        @Override
        public void run() {
            while (true) {
                if (socket.isClosed()) {
                    break;
                }
                receiveObject();
            }
            servereBaglantiyiKes();
        }
    }
    
    
    
    
    
    //soket oluşturarak, port dinleyecek listeneri thread olarak çalıştırır
    public final boolean baslat() {
        try {
            socket = new Socket(serverIp, port);
            
            createObjInputObjOutput(socket);
            Listeler.addListGenel("Server bağlantısı sağlandı...");
            
            //thread'i başlat
            Thread serverListener = new ListenerForInput();
            serverListener.setDaemon(true);
            serverListener.start();
            
            Listeler.addListGenel("Listener oluşturuldu Görev bekleniyor");
        } catch (IOException ex) {
            Listeler.addListGenel("İnternet bağlantınız olduğundan "
                    + "ve Serverin başlatıldığından emin olunuz \nSistemin Mesajı: " + ex.getMessage());
            return false;
        }
        return true;
    }
    
    
    
    
    
    //Server bağlantısını keser. Sonlandırma işareti gönderir
    public final void servereBaglantiyiKes() {
        
        //threadler çalışmayı durdursun
        Sha256.setThreadlerCalismayiDurdursun(true);
        if(!socket.isClosed()){
            try {
                //serverin clienti devam ettirmemesi için sonlandırma işareti
                sendObj("SON");
                //ObjectOutputStream ve ObjectInputStream soketle birlikte kapanacağından ayrıca kapatmadık
                this.socket.close();
                Listeler.arkaPlandaEkle("Soket başarıyla kapatıldı: ");
            } catch (IOException ex) {
                Listeler.arkaPlandaEkle("disconnect esnasında sorun yaşandı detayı: " + ex.getMessage());
            }
        }
    }



    
    
    
    
    //servere Nesne gönderir
    public final synchronized void  sendObj(Object obj) {
        try {
            this.objectOutput.writeObject(obj);
            this.objectOutput.flush();
            Listeler.listGeneleObjeYaz("Gönderilen",obj);
        } catch (IOException ex) {
            Listeler.addListGenel("Gönderme işlemi başarısız oldu. Detaylar:"+ex.getMessage());
        }
    }
    



    
    
    //serverden gelen nesneleri yakalayan metod
    public Object receiveObject() {
        Object receivedObj = null;
        try {
            
            receivedObj = this.objectInput.readObject();
            Listeler.listGeneleObjeYaz("Gelen",receivedObj);
            if (receivedObj instanceof String) gelenMsgSifreKirildiBildirimiMi((String)receivedObj);
            if (receivedObj instanceof String) gelenMsgPartBildirimMi((String)receivedObj);; 
            if (receivedObj instanceof Gorev) {
                final Sha256 sha256Class=new Sha256();
                //static değişken olduğundan her ne kadar new Sha256() dense de alttaki değişkenin değerini düzeltmek
                Sha256.setThreadlerCalismayiDurdursun(false);
                sha256Class.parolayiKir((Gorev) receivedObj, clientinIsleyecegiPart);
            } 
            return receivedObj;
        } catch (IOException | ClassNotFoundException ex) {
            Listeler.arkaPlandaEkle("Server bağlantıyı kesti.  Detay: " + ex.getMessage());
            servereBaglantiyiKes();
            return null;
        }
    }
    
    
     
    
    
    
    //ObjectInputStream ve ObjectOutputStream nesnelerini oluşturur
    private void createObjInputObjOutput(Socket cs) {
        try {
            this.objectInput = new ObjectInputStream(cs.getInputStream());
            this.objectOutput = new ObjectOutputStream(cs.getOutputStream());

        } catch (IOException ex) {
            Listeler.addListGenel("Giriş çıkış hatası oluştu Detay: " + ex.getMessage());
        }
    }

}
