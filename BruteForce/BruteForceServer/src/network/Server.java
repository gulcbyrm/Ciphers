package network;

import java.io.IOException;
import java.net.ServerSocket;
import ui.SettingsController;
import utility.Listeler;


public class Server extends Thread {

    //sınıfın değişkenleri
    private ServerSocket server;
    public static int uniqueID;
    
    
    
    //thread run metod
    @Override
    public void run() {
        try {
            this.server = new ServerSocket(SettingsController.getDinlenecekPort());
            Listeler.arkaPlandaGenelListeEkle("server başlatıldı, bağlantı almaya hazır...\n");

            while (true) {
                if (this.server.isClosed() || server == null) {
                    break;
                }

                ServerWorker ch = new ServerWorker(this.server.accept());
                ch.setDaemon(true);
                Listeler.arkaPlandaWorkerListeEkle(ch);
                Listeler.arkaPlandaGenelListeEkle(ch.id + " nolu clientin bağlantı isteği kabul edildi...");
                ch.start();
            }

        } catch (IOException ex) {
            Listeler.arkaPlandaGenelListeEkle( "ServerSocket ve Socket kapalı, Detaylar:" + ex.getMessage());
        } 
    }

    
    
    
    
    //stop server
    public boolean serveriDurdur() {
        try {
            for (ServerWorker serverWorker : Listeler.getListWorker()) {
                serverWorker.closeThisSocket();
                serverWorker.removeThisWorker();
            }
            if (this.server != null) this.server.close();
            Listeler.arkaPlandaGenelListeEkle("server ve tüm bağlantılar kapatıldı");
        } catch (IOException ex) {
            Listeler.arkaPlandaGenelListeEkle("server kapatılamıyor.. Detay: " + ex.getMessage());
            return false;
        }
        return true;
    }
    



    public void broadCast(String msg, ServerWorker exceptThis){
        for (ServerWorker serverWorker : Listeler.getListWorker()) {
            if(!serverWorker.equals(exceptThis))serverWorker.sendOBJ(msg);
        }
    }
}






//                if (Server.isSifreKirildi()) {
//                    Thread.sleep(2000); //uyuması lazım çünkü diğer işler bitmeden listeye dahi yazamadan server kapatılabiliyor
//                    serveriDurdur();
//                    break;
//                }
                //Socket socket = server.accept();