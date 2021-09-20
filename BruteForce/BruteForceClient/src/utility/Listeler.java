package utility;

import entities.Gorev;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Listeler {

    private static ObservableList<String> listGenel;


    public static synchronized final void addListGenel(String metin) {
        getListGenel().add(metin);
    }

    
    
    
    public static final ObservableList<String> getListGenel() {
        if (listGenel == null) {
            listGenel = FXCollections.synchronizedObservableList(FXCollections.observableArrayList());
        }
        return listGenel;
    }

    
    
    
    //OVERLOAD metod arkaPlandaEkle
    
    
    public static void arkaPlandaEkle( String mesaj) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                addListGenel(mesaj);
            }
        });
    }
    
    
    
    
    //future nesnesi arka planda ekler
    public static  void arkaPlandaEkle(String title, Future<String> coredenDonenMesaj) {
        try {
            if(coredenDonenMesaj.get()==null)return;
            arkaPlandaEkle(title+" "+(String) coredenDonenMesaj.get());
        } catch (InterruptedException | ExecutionException ex) {
            arkaPlandaEkle("Future nesnesi listeye aktarılamadı. Detay: "+ex.getMessage());
        }
    }
    
    
    
    
    //Genel listesine ekleme yapar ve bu liste listener sayesinde otomatik olarak Viewe aktarılmaktadır 
    public static void listGeneleObjeYaz(String baslik, Object obj){
        if (obj instanceof String) {
            Listeler.arkaPlandaEkle(baslik+" Metin: " + obj.toString());
        } else Listeler.arkaPlandaEkle( baslik+" Nesne: " + ((Gorev)obj).toString());
    }
}
