package entities;

import java.io.Serializable;
import java.util.Arrays;
import lombok.Getter;

/**
 * Gorev Entity BUILDER TASARIM KALIBI ile oluşturuldu
 *
 * Clientlere gönderilecektir.
 */


public class Gorev implements Serializable {

    private static final long serialVersionUID = 1789236985391498552L;

    @Getter private final String karakterSeti;
    @Getter private final String hashKod;
    @Getter private final long[] permutasyonDizisi;
    @Getter private final int minUzunluk;
    @Getter private final int maxUzunluk;
    @Getter private final GorevDizi[] clientGorevDizi;

    
    private Gorev(GorevBuilder gb) {
        this.karakterSeti = gb.karakterSeti;
        this.hashKod = gb.hashKod;
        this.permutasyonDizisi = gb.permutasyonDizisi;
        this.minUzunluk = gb.minUzunluk;
        this.maxUzunluk = gb.maxUzunluk;
        this.clientGorevDizi = gb.clientGorevDizi;
    }



    @Override
    public String toString() {
        return  "\n\tKarakter Seti \t\t: " + karakterSeti + 
                "\n\tMin Uzunluk\t\t: " + minUzunluk + 
                "\n\tMax Uzunluk\t\t: " + maxUzunluk + 
                "\n\tKırılacak Hash Kod\t: " + hashKod + 
                "\n\tPermütasyon Dizisi\t: " + Arrays.toString(permutasyonDizisi) + 
                "\n\tClient Görev Dizisi\t: " + Arrays.toString(clientGorevDizi) ;
    } 
    
    

    //INNER CLASS
    public static class GorevBuilder {

        private String karakterSeti;
        private String hashKod;
        private long[] permutasyonDizisi;
        private int minUzunluk;
        private int maxUzunluk;
        private GorevDizi[] clientGorevDizi;

        public GorevBuilder() {
        }

        public GorevBuilder setKarakterSeti(String karakterSeti) {
            this.karakterSeti = karakterSeti;
            return this;
        }

        public GorevBuilder setHashKod(String hashKod) {
            this.hashKod = hashKod;
            return this;
        }

        public GorevBuilder setPermutasyonDizisi(long[] permutasyonDizisi) {
            this.permutasyonDizisi = permutasyonDizisi;
            return this;
        }

        public GorevBuilder setMinUzunluk(int minUzunluk) {
            this.minUzunluk = minUzunluk;
            return this;
        }

        public GorevBuilder setMaxUzunluk(int maxUzunluk) {
            this.maxUzunluk = maxUzunluk;
            return this;
        }

        public GorevBuilder setClientGorevDizi(GorevDizi[] clientGorevDizi) {
            this.clientGorevDizi = clientGorevDizi;
            return this;
        }

        public Gorev build() {
            return new Gorev(this);
        }
    }
}
