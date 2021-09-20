package error;

import entities.ErrCheck;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class ErrorHelper {

    //sınfın değişkenleri
    private final StringBuilder msg;        //oluşan hatalar bu stringde tutulacaktır
    private int sayac;                      //hataların sayısını tutmaktadır

    //Constructure
    public ErrorHelper() {
        this.msg = new StringBuilder();
    }

    
    
    private  int integereCevir(final ErrCheck strNum) {
        return Integer.valueOf(metniEldeEt(strNum));
    }
    
    
    //verilen ErrCheck nesnesinin stringini alır
    private String metniEldeEt(final ErrCheck obj){
        if (obj.getField() instanceof TextField) 
            return ((TextField) obj.getField()).getText();
        if(obj.getField() instanceof String)
            return (String) obj.getField();
        if(obj.getField() instanceof Integer)
            return String.valueOf(obj.getField());
        return null;
    }
    
    
    
    //gelen nodelerden her hatalı node için bir mesaj oluşturur   
    public void buFieldlerBosOlamaz(final ErrCheck... errChecks) {
        for (ErrCheck e : errChecks) {
            if (isNullOrEmpty(e)) {
                msg.append(++sayac).append(") ").append(e.getDescription()).append(" boş veya null olamaz \n");
            }
        }
    }
    
    
    
    //gelen nodelerden her hatalı node için bir mesaj oluşturur   
    public void buFieldlerdenIlkiIkincisindenBuyukOlamaz(final ErrCheck errBir, final ErrCheck errIkı) {
        if(integereCevir(errBir)>integereCevir(errIkı)){
            msg.append(++sayac).append(") ").append(errBir.getDescription()).append(", ").append(errIkı.getDescription()).append("'den büyük olamaz\n");
        }
    }
    
    
    
  
    //gelen nodelerden her hatalı node için bir mesaj oluşturur   
    public void buFieldlerdeTextLengthSiniriUygula(final ErrCheck[] errChecks, final int... boyut) {
        int i = 0;
        for (ErrCheck e : errChecks) {
            if (metniEldeEt(e).length() !=boyut[i++]) {
                msg.append(++sayac).append(") ").append(e.getDescription()).append(" girdiğiniz değerde karakter uzunluğu sınırı var. Geçerli uzunluk ").append(boyut[i]).append(" olmalı.\n");
            }
        }
    }  
    

    //gelen nodelerden her hatalı node için bir mesaj oluşturur   
    public void buFieldlerMinDegerinAltinaInemez(final ErrCheck[] errChecks, final int... minDegerler) {
        int i = -1;
        for (ErrCheck e : errChecks) {
            if (integereCevir(e) < minDegerler[++i]) {
                msg.append(++sayac).append(") ").append(e.getDescription()).append("na girdiğiniz değer min değerin altında. En düşük ").append(minDegerler[i]).append(" girebilirsiniz.\n");
            }
        }
    }
    


       
    public void buFieldlerMaxDegeriAsamaz(final ErrCheck[] errChecks, final int... maxDegerler) {
        int i = -1;
        for (ErrCheck e : errChecks) {
            if (integereCevir(e) > maxDegerler[++i]) {
                msg.append(++sayac).append(") ").append(e.getDescription()).append("na girdiğiniz değer max sınırı aşıyor. En fazla ").append(maxDegerler[i]).append(" olabilir.\n");
            }
        }
    }       

    
    

    
    
    //gelen nodelerden her hatalı node için bir mesaj oluşturur  //null ve boş kontrolü de yapmaktadır 
    public void buFieldlerNumericOlmali(final ErrCheck... errChecks) {
        for (ErrCheck e : errChecks) {
            if (isNullOrEmpty(e)||!isNumeric(e)) {
                msg.append(++sayac).append(") ").append(e.getDescription()).append(" alanına sayı girilmek zorunda.\n");
            }
        }
    }




    
    public boolean HataliAlanVarsaMsjGoruntule() {
        if (msg.length() > 0) {
            new MyAlert().showErrorAlert(msg.toString(), "Tespit Edilen Hata/Hatalar");
            return true;
        }
        return false;
    } 
        

    

    
    //textedit ve observablelistlerin boş veya null olup olmadığını tespit eder
    private boolean isNullOrEmpty(final ErrCheck err) {
        if(err.getField() == null )return true;
        if(buBirTextField(err))return  ((TextField)err.getField()).getText().isEmpty();
        else return ((ObservableList)err.getField()).isEmpty();
    }
    
    
    
    
    private boolean buBirTextField(final ErrCheck err){
        return err.getField() instanceof TextField;
    }
    
    
    
    
    
    //verilen string numeric ise true döndürür
    private boolean isNumeric(final ErrCheck obj) {
        String metin = ((TextField) obj.getField()).getText();
        try {
            double d = Double.parseDouble(metin);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

}
