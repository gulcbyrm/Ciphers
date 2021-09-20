
package utility;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import network.ServerWorker;
import org.junit.Test;
import static org.junit.Assert.*;


public class ListelerTest {
    
    public ListelerTest() {
    }


    
    @Test
    public void testRemoveWorker() {
        try {
            ServerSocket sc=new ServerSocket(6000);
            System.out.println("removeWorker");
            ServerWorker silinecekWorker = new ServerWorker(new Socket(InetAddress.getLocalHost(), 6000)) ;
            Listeler.removeWorker(silinecekWorker);
        } catch (IOException ex) {
            Logger.getLogger(ListelerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    
    
    
    @Test
    public void testArkaPlandaGenelListeEkle() {
        System.out.println("arkaPlandaGenelListeEkle");
        String mesaj = "TEST";
        Listeler.arkaPlandaGenelListeEkle(mesaj);
        System.out.println(Listeler.getListGenel().get(0));
    }


    
    
    
    @Test
    public void testArkaPlandaWorkerListeEkle() {
        System.out.println("arkaPlandaWorkerListeEkle");
        ServerWorker worker = null;
        Listeler.arkaPlandaWorkerListeEkle(worker);
    }


    
    
    
    
    
    @Test
    public void testAddList() {
        System.out.println("addList");
        ObservableList<String> list = null;
        String mesaj = "";
        Listeler.addList(list, mesaj);
    }


    
    
    
    
    
    @Test
    public void testGetListWorker() {
        System.out.println("getListWorker");
        ObservableList<ServerWorker> expResult = null;
        ObservableList<ServerWorker> result = Listeler.getListWorker();
        assertEquals(expResult, result);
    }


    
    
    
    
    @Test
    public void testGetListGenel() {
        System.out.println("getListGenel");
        ObservableList<String> expResult = null;
        ObservableList<String> result = Listeler.getListGenel();
        assertEquals(expResult, result);
    }


    
    
    
    
    @Test
    public void testGetListServerDurum() {
        System.out.println("getListServerDurum");
        ObservableList<String> expResult = null;
        ObservableList<String> result = Listeler.getListServerDurum();
        assertEquals(expResult, result);
    }
    
}
