package ciphers;


import arayuzler.CipherI;
import ciphers.affine.Affine;
import ciphers.bifid.Bifid;
import ciphers.caesar.Caesar;
import ciphers.des.Des;
import ciphers.hill.Hill;
import ciphers.permutasyon.Permutasyon;
import ciphers.playfair.PlayFair;
import ciphers.polybius.Polybius;
import ciphers.rot13.Rot13;
import ciphers.substitution.Substitution;
import ciphers.tevrat.Tevrat;
import ciphers.vernam.Vernam;
import ciphers.vigenera.Vigenera;
import ui.AnaPencereController;


public class CipherFactory extends AnaPencereController {
    public CipherI createCipher(SelectedCipher selectedCipher) {
        switch (selectedCipher) {
            case DES:
                return new Des();
            case CAESAR:
                return new Caesar();
            case VİGENERA:
                return new Vigenera();
            case AFFİNE:
                return new Affine();
            case SUBSTİTUTİON:
                return new Substitution();
            case BİFİD:
                return new Bifid();
            case PERMUTASYON:
                return new Permutasyon();
            case PLAYFAİR:
                return new PlayFair();
            case POLYBİUS:
                return new Polybius();
            case ROT13:
                return new Rot13();
            case TEVRAT:
                return new Tevrat();
            case VERNAM:
                return new Vernam();
            case HİLL:
                return new Hill();
            default:
                System.out.println("Cipher Factory sınıfında tanımlı değil");
                return null;
        }
    }
}
