package network;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;
import static org.junit.Assert.*;

public class ClientTest {

    private int port = 6000;
    private String ip = "localhost";

    private ServerSocket serverSocket;

    
    
    
    private void serveriBaslat() {
        try {
            serverSocket = new ServerSocket(6000);
            
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        serverSocket.accept();
                    } catch (IOException ex) {
                        Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });
            t.setDaemon(true);
            t.start();

        } catch (IOException ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
    @Test
    public void testClientiBaslat() {
        serveriBaslat();
        System.out.println("baslat");
        Client instance = createNewClient();
        boolean expResult = false;
        boolean result = instance.baslat();
        if (result) {
            System.out.println("Client sorunsuz başlatıldı");
        }
        assertEquals(expResult, result);
        try {
            serverSocket.close();
            instance.getSocket().close();
        } catch (IOException ex) {
            Logger.getLogger(ClientTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    @Test
    public void testServereBaglantiyiKes() {
        Client instance = createNewClient();
        System.out.println("servereBaglantiyiKes");
        if (instance.baslat()) {
            System.out.println("Soket zaten kapalı bu testi tekrar gözden geçir");
        } else {
            //eğer soket açık ise
            instance.servereBaglantiyiKes();
            assertEquals(instance.getSocket().isClosed(), false);
        }
    }

    
    
    
    @Test
    public void testSendObj() {
        System.out.println("sendObj");
        Object obj = null;
        Client instance = createNewClient();
        instance.sendObj(obj);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testReceiveObject() {
        System.out.println("receiveObject");
        Client instance = createNewClient();
        Object expResult = null;
        Object result = instance.receiveObject();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    @Test
    public void testGetSocket() {
        serveriBaslat();
        System.out.println("getSocket");
        Client client = createNewClient();
        boolean sc = client.baslat();
        Socket expResult = null;
        Socket result = client.getSocket();
        System.out.println("result" + result);
        assertEquals(expResult, result);
    }

    private Client createNewClient() {
        try {
            System.out.println("Client başarıyla oluşturuldu");
            return new Client(port, InetAddress.getByName(ip));

        } catch (UnknownHostException ex) {

            System.out.println("client oluşturulamıyor");
            return null;
        }
    }

}
