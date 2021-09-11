package ciphers.substitution;

import ciphers.des.sabitler.Mode;
import org.junit.Test;

public class SubstitionTest {

    Substitution substition = new Substitution();

    @Test
    public void testSubsSifreleYeni() throws Exception {
        String s=substition.sifreleSifreCoz("inonuuniversitesibilgisayarmuhendisligibilgiguvenligi",Mode.Encript);
        substition.sifreleSifreCoz(s, Mode.Decript);
    }
}

