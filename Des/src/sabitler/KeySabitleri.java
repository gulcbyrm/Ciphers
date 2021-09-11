package sabitler;


public class KeySabitleri extends SabitlerAbstract{

    // K anahtarı ilk aşamada bu tabloya tabi tutularak karıştırılır K+ anahtarı elde edilir
    //56 bite indirger
    private final int[] PC1_Tablosu = {
            57, 49, 41, 33, 25, 17, 9,
            1, 58, 50, 42, 34, 26, 18,
            10, 2, 59, 51, 43, 35, 27,
            19, 11, 3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14, 6, 61, 53, 45, 37, 29,
            21, 13, 5, 28, 20, 12, 4
    };


    //Bu tablo 56 bit olarak hazırlanmış olan keyi son haline yani 48 bite indirmek için kullanılır
    //101010101010110101010101001010101010101
    //10.......1
    private final int[] PC2 = {
            14, 17, 11, 24, 1, 5,
            3, 28, 15, 6, 21, 10,
            23, 19, 12, 4, 26, 8,
            16, 7, 27, 20, 13, 2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
    };



    //Sınıfın GET metodları
    public int[] getPC2() {
        return PC2;
    }

    public int[] getPC1_Tablosu() {
        return PC1_Tablosu;
    }
}