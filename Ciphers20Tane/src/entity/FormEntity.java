package entity;

//Builder tasarım kalıbı kullanılarak yazıldı
public class FormEntity {
    private final String formBasligi;
    private final String btnHakkindaBasligi;
    private final String aciklama;

    private FormEntity(FormEntityBuilder builder) {
        this.formBasligi = builder.formBasligi;
        this.btnHakkindaBasligi = builder.btnHakkindaBasligi;
        this.aciklama = builder.aciklama;
    }

    public String getFormBasligi() {
        return formBasligi;
    }

    public String getBtnHakkindaBasligi() {
        return btnHakkindaBasligi;
    }

    public String getAciklama() {
        return aciklama;
    }

    public static class FormEntityBuilder{
        private  String formBasligi;
        private  String btnHakkindaBasligi;
        private  String aciklama;

        public FormEntityBuilder() {
        }

        public FormEntityBuilder setFormBasligi(String formBasligi) {
            this.formBasligi = formBasligi;
            return this;
        }

        public FormEntityBuilder setBtnHakkindaBasligi(String btnHakkindaBasligi) {
            this.btnHakkindaBasligi = btnHakkindaBasligi;
            return this;
        }

        public FormEntityBuilder setAciklama(String aciklama) {
            this.aciklama = aciklama;
            return this;
        }
        public FormEntity build(){
            return new FormEntity(this);
        }
    }
}
