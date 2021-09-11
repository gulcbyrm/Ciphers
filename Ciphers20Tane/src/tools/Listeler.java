package tools;

import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;


public class Listeler {

    private static final  ObservableList<String> listDetay = FXCollections.observableList(new ArrayList<String>());
    private static final  ObservableList<String> listDuzMetin = FXCollections.observableList(new ArrayList<String>());
    private static final  ObservableList<String> listSifreliMetin = FXCollections.observableList(new ArrayList<String>());



    public final static synchronized void addListDuzMetin(final String eklenecekStr) {
        addList(Listeler.listDuzMetin,eklenecekStr);
    }
    public final static synchronized void addListDetay(final String eklenecekStr) {
        addList(Listeler.listDetay,eklenecekStr);
    }
    public final static synchronized void addListSifreliMetin(final String eklenecekStr) {
        addList(Listeler.listSifreliMetin,eklenecekStr);
    }

    public static final void addList(ObservableList<String> eklenecekListe, String eklenecekStr){
        eklenecekListe.add(eklenecekStr);
    }




    public static ObservableList<String> getListDetay() {
        return listDetay;
    }

    public static ObservableList<String> getListDuzMetin() {
        return listDuzMetin;
    }

    public static ObservableList<String> getListSifreliMetin() {
        return listSifreliMetin;
    }
}