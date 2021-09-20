package utility;

import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import network.ServerWorker;

public class Listeler {

    @Getter private final static ObservableList<ServerWorker> listWorker = FXCollections.observableList(new ArrayList<ServerWorker>());
    @Getter private final static ObservableList<String> listGenel = FXCollections.observableList(new ArrayList<String>());
    @Getter private final static ObservableList<String> listServerDurum = FXCollections.observableList(new ArrayList<String>());

    
    private final static synchronized <N> void arkaplandaEklemeYap(final ObservableList<N> oList, final N eklenecek) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                oList.add(eklenecek);
            }
        });
    }
    
    
    
    public final static synchronized  void removeWorker(final ServerWorker silinecekWorker) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                if(silinecekWorker!= null && ! listWorker.isEmpty())
                    listWorker.remove(silinecekWorker);
            }
        });
    }
    
    
    public final static  void arkaPlandaGenelListeEkle(final String mesaj) {
        arkaplandaEklemeYap(listGenel, mesaj);
    }

    public final static void arkaPlandaWorkerListeEkle(final ServerWorker worker) {
        arkaplandaEklemeYap(listWorker, worker);
    }

    public final static synchronized void addList(final ObservableList<String> list, final String mesaj) {
        list.add(mesaj);
    }
}