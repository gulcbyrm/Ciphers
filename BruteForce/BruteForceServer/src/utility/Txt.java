package utility;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class Txt {


    /**
     * Kaydet diyalog kutusunu gösterir
     * @return kullanıcının seçtiği file return eder
     */
    public File keydetDialog() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT Dosya (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
        }
        return file;
    }



    /**
     * Aç diyalog kutusunu gösterir
     * @return kullanıcının seçtiği file return eder
     */
    public File dosyadanAlDialog() throws IOException {
        FileChooser fileChooser = new FileChooser();
        return fileChooser.showOpenDialog(new Stage());
    }




    /**
     * dosyayı txt olarak kaydeder
     * @param file kaydedilecek dosya adı
     * @param rows txt ye kaydedilcek string listesi
     */
    public void insertTXT(File file, List<String> rows) {
        try (BufferedWriter br = new BufferedWriter(new FileWriter(file, true))) {
            if (file.length() > 0) {
                br.newLine();
            }
            for (String column : rows) {
                br.write(column );
            }
        } catch (IOException e) {
            System.out.println("insertTXT metodunda HATA. Dosya okunurken hata oluştu " + file.toString());
        }
    }




    /**
     * TXT yi okur satırları listeye doldurarak return eder
     * @param file okunan dosya
     * @return txt dosyasının içeriğini string list olarak return eder
     * @throws IOException dosya bulunamazsa bu hata meydana gelir
     */
    public List<String> readAllFromFileToList(File file) throws IOException {
        List<String> list = new ArrayList<>();
        String line;

        BufferedReader br = new BufferedReader(new FileReader(file));
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        return list;
    }
}
