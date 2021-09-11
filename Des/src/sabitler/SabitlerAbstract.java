package sabitler;

public abstract class SabitlerAbstract {
    /**
     * bu fonksiyon tablolar ile bit stringlerini karıştırır
     * örnek: girdi-->0001001100110100010101110111100110011011101111001101111111110001 olarak gelen bu string
     * PC1 tablosuna göre karıştırılırsa.
     * 11110000110011001010101011110101010101100110011110001111 stringi elde edilmeli
     * DİKKAT: tabloda 1 olarak aslında stringin ilk yani 0 indexi kasdedilir
     *
     * @param tablo  hangi tablo baz alınarak karıştırılacak
     * @param bitStr Karistirilacak Bit Stringi
     * @return karıştırılmış bitler
     */
    public String karistir(int[] tablo, String bitStr) {
        StringBuilder karismisBitStr = new StringBuilder();
        for (int index : tablo) {
            karismisBitStr.append(bitStr.charAt(index - 1));
        }
        return karismisBitStr.toString();
    }

}
