package entities;

import org.junit.Test;
import static org.junit.Assert.*;

public class CoreGorevTest {

    private CoreGorev instance;

    
    public CoreGorevTest() {
        instance = createCoreGorev();
    }

    
    
    @Test
    public void testGetBaslangic() {
        System.out.println("getBaslangic");
        int expResult = 10;
        int result = instance.getBaslangic();
        assertEquals(expResult, result);
    }

    
    
    @Test
    public void testGetBitis() {
        System.out.println("getBitis");
        int expResult = 20;
        int result = instance.getBitis();
        assertEquals(expResult, result);
    }

    
    
    
    @Test
    public void testSetBaslangic() {
        System.out.println("setBaslangic");
        int baslangic = 54;
        instance.setBaslangic(baslangic);
        int expResult=instance.getBaslangic();
        assertEquals(expResult, baslangic);
    }
    
    

    @Test
    public void testSetBitis() {
        System.out.println("setBitis");
        int bitis = 77;
        instance.setBitis(bitis);
        int expResult=instance.getBitis();
        assertEquals(expResult, bitis);
    }

    
    


    @Test
    public void testToString() {
        System.out.println("toString");
        String expResult = "CoreGorev(baslangic=10, bitis=20)";
        String result = instance.toString();
        System.out.println(result.toString());
        assertEquals(expResult, result);
    }

    private CoreGorev createCoreGorev() {
        return new CoreGorev(10, 20);
    }

}
