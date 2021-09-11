package ciphers.des;


import java.io.UnsupportedEncodingException;

public class Araclar {


    // 1111110011111100111111001111110011111100111111001111110011111100
    public String toStr(String binaryStr) throws UnsupportedEncodingException {
        StringBuilder metin = new StringBuilder();
        for (int i = 0; i < binaryStr.length(); i = i + 8) {
            String tmp = binaryStr.substring(i, i + 8);
            final char karakter = toStr8(tmp);
            metin.append(karakter);
        }
        return metin.toString();
    }


    // toStr metodundan gelen string türünden binari 8lisini char'a çevirir
    private char toStr8(String binary8lisi) throws UnsupportedEncodingException {
        switch (binary8lisi) {
            case "10110100":
                return 'İ';
            case "10110011":
                return 'ı';
            case "10110010":
                return 'Ğ';
            case "10110001":
                return 'ğ';
            case "10110000":
                return 'Ş';
            case "01111111":
                return 'ş';
        }
        int num = Integer.parseInt(binary8lisi, 2);
        return (char) num;
    }


    //For kullanılmadan java kodları kullanılmadan 4 bit binary sayıyı decimala çevirme kendi yapımımız....
    //2 veya 4 bitlik binary sayıyı decimale çevirir
    public int binaryToDecimal(String binary) {  //1111    1001=9 d[2][1]
        final int[][] a = {{0, 1}, {2, 3}};//1001
        final int i = a[toInt(binary.charAt(0))][toInt(binary.charAt(1))];//3      2
        if (binary.length() == 2) return i;
        final int j = a[toInt(binary.charAt(2))][toInt(binary.charAt(3))];//3      1
        final int[][] d = {{0, 1, 2, 3}, {4, 5, 6, 7}, {8, 9, 10, 11}, {12, 13, 14, 15}};
        return d[i][j];
    }


    //Binary sayıyı for kullanmadan hex sayıya çevirir
    // 4 bit olmak zorunda
    public String binaryToHex(String binary) {  //1111    1001=9 d[2][1]
        final int[][] a = {{0, 1}, {2, 3}};//1001
        final String[][] d = {{"0", "1", "2", "3"}, {"4", "5", "6", "7"}, {"8", "9", "A", "B"}, {"C", "D", "E", "F"}};
        final int i = a[toInt(binary.charAt(0))][toInt(binary.charAt(1))];//3      2
        final int j = a[toInt(binary.charAt(2))][toInt(binary.charAt(3))];//3      1
        return d[i][j];
    }


    public String toHex64BitBinary(String binary) {  //1111    1001=9 d[2][1]
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < binary.length() - 1; i += 4) {
            result.append(binaryToHex(binary.substring(i, i + 4)));
        }
        return result.toString();
    }


    public void hexToBinary(StringBuilder binary, String hex) {  //1111    1001=9 d[2][1]
        if (hex.length() == 0) return;

        switch (hex.substring(0, 1)) {
            case "0":
                binary.append("0000");break;
            case "1":
                binary.append("0001");break;
            case "2":
                binary.append("0010");break;
            case "3":
                binary.append("0011");break;
            case "4":
                binary.append("0100");break;
            case "5":
                binary.append("0101");break;
            case "6":
                binary.append("0110");break;
            case "7":
                binary.append("0111");break;
            case "8":
                binary.append("1000");break;
            case "9":
                binary.append("1001");break;
            case "A":
                binary.append("1010");break;
            case "B":
                binary.append("1011");break;
            case "C":
                binary.append("1100");break;
            case "D":
                binary.append("1101");break;
            case "E":
                binary.append("1110");break;
            case "F":
                binary.append("1111");break;
        }
        hexToBinary(binary, hex.substring(1));
    }


    private int toInt(char ch) {
        return ch == '0' ? 0 : 1;
    }


    //15e kadar olan decimal sayıyı binariye çevirir
    //S tabloları için yazdık. S tablolarından gelen sayıları binary stringe çevirdik
    public String decimalToBinary(final int decimal) {
        final String[] binary = {"0000", "0001", "0010", "0011", "0100", "0101", "0110", "0111", "1000", "1001", "1010", "1011", "1100", "1101", "1110", "1111"};
        return binary[decimal];
    }

}
