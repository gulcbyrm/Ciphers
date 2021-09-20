
//BU SINIF IMPLEMENTATION SIRASINDA SİLİNDİĞİNDEN TEST İPTAL





/**
 *
 * @author blnk
 */
public class GrvAraligiHesapTest {

//    private final GorevIsle instance;
//
//    public GrvAraligiHesapTest() {
//        instance = new GorevIsle();
//    }
//
//    //SERİ KODLANAN 2 METODUN TESTİ
//    @Test
//    public void testGorevBaslangic_SERIKODLAMA() {
//        final int expResult = 0;
//        final int[] gorevDizi = new int[]{39, 38};
//        int clientNo = 0;
//        int result = instance.gorevBaslangic_Seri(gorevDizi, clientNo);
//        System.out.println("gorevBaslangic_Seri Metodu --> ClientNo: " + clientNo + " Gorev Baş:" + result);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void testGorevBitis_SERIKODLAMA() {
//        int expResult = 39;
//        final int[] gorevDizi = new int[]{39, 38};
//        int clientNo = 0;
//        int result = instance.gorevBitis_Seri(gorevDizi, clientNo);
//        System.out.println("gorevBitis_Seri Metodu     --> ClientNo: " + clientNo + " Gorev Bit:" + result);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void test2GorevBaslangic_SERIKODLAMA() {
//        final int expResult = 39;
//        final int[] gorevDizi = new int[]{39, 38};
//        int clientNo = 1;
//        int result = instance.gorevBaslangic_Seri(gorevDizi, clientNo);
//        System.out.println("gorevBaslangicSERI Metodu --> ClientNo: " + clientNo + " Gorev Baş:" + result);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void test2GorevBitis_SERIKODLAMA() {
//        int expResult = 77;
//        final int[] gorevDizi = new int[]{39, 38};
//        int clientNo = 1;
//        int result = instance.gorevBitis_Seri(gorevDizi, clientNo);
//        System.out.println("gorevBitis_Seri Metodu     --> ClientNo: " + clientNo + " Gorev Bit:" + result);
//        assertEquals(expResult, result);
//    }
//
//
//
//    @Test
//    public void test3GorevBitis_SERIKODLAMA() {
//        int expResult = 192;
//        final int[] gorevDizi = new int[]{39, 39, 38, 38, 38};
//        int clientNo = 4;
//        int result = instance.gorevBitis_Seri(gorevDizi, clientNo);
//        System.out.println("gorevBitis_Seri Metodu     --> ClientNo: " + clientNo + " Gorev Bit:" + result);
//        assertEquals(expResult, result);
//    }
//
//    //RECURSİVE METODLARIN TESTLERİ
//    @Test
//    public void test1GorevBaslangic_rec() {
//        int expResult = 0;
//        final int[] gorevDizi = new int[]{30, 38, 45};
//        int clientNo = 1;
//        int result = instance.gorevBaslangic_Recursive(gorevDizi, clientNo);
//        System.out.println("gorevBaslangic_Rec Metodu     --> ClientNo: " + clientNo + " Gorev Bit:" + result);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void test1GorevBaslangic_rec1() {
//        int expResult = 30;
//        final int[] gorevDizi = new int[]{30, 38, 45};
//        int clientNo = 2;
//        int result = instance.gorevBaslangic_Recursive(gorevDizi, clientNo);
//        System.out.println("gorevBaslangic_Rec Metodu     --> ClientNo: " + clientNo + " Gorev Bit:" + result);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void test1GorevBitis_rec2() {
//        int expResult = 113;
//        final int[] gorevDizi = new int[]{30, 38, 45,55};
//        int clientNo = 3;
//        int result = instance.gorevBitis_Recursive(gorevDizi, clientNo);
//        System.out.println("gorevBitis_Rec Metodu     --> ClientNo: " + clientNo + " Gorev Bit:" + result);
//        assertEquals(expResult, result);
//    }
//    @Test
//    public void test1GorevBaslangic_rec2() {
//        int expResult = 68;
//        final int[] gorevDizi = new int[]{30, 38, 45};
//        int clientNo = 3;
//        int result = instance.gorevBaslangic_Recursive(gorevDizi, clientNo);
//        System.out.println("gorevBaslangic_Rec Metodu     --> ClientNo: " + clientNo + " Gorev Bit:" + result);
//        assertEquals(expResult, result);
//    }
//        @Test
//    public void test1GorevBitis_rec() {
//        int expResult = 30;
//        final int[] gorevDizi = new int[]{30, 38, 45};
//        int clientNo = 1;
//        int result = instance.gorevBitis_Recursive(gorevDizi, clientNo);
//        System.out.println("gorevBitis_Rec Metodu     --> ClientNo: " + clientNo + " Gorev Bit:" + result);
//        assertEquals(expResult, result);
//    }
//    
//    @Test
//    public void test3GorevBaslangic_SERIKODLAMA() {
//        final int expResult = 154;
//        final int[] gorevDizi = new int[]{39, 39, 38, 38, 38};
//        int clientNo = 4;
//        int result = instance.gorevBaslangic_Seri(gorevDizi, clientNo);
//        System.out.println("gorevBaslangicSERI Metodu --> ClientNo: " + clientNo + " Gorev Baş:" + result);
//        assertEquals(expResult, result);
//    }
//
//    @Test
//    public void test1GorevBitis_recursive() {
//        int expResult = 68;
//        final int[] gorevDizi = new int[]{30, 38, 45};
//        int clientNo = 2;
//        int result = instance.gorevBitis_Recursive(gorevDizi, clientNo);
//        System.out.println("gorevBitis_Rec Metodu     --> ClientNo: " + clientNo + " Gorev Bit:" + result);
//        assertEquals(expResult, result);
//    }


//    public void GorevIsleParalelTest(){ 
//        try {
//           ExecutorService service =  Executors.newSingleThreadExecutor();
//           BaslangicBelirleParalel myTask = new BaslangicBelirleParalel(new int[]{30, 38, 45,55}, 2);
//           Future<Integer> future = service.submit(myTask);
//           Integer result=future.get();
//           System.out.println(result);
//        } catch (InterruptedException | ExecutionException ex) {
//            Logger.getLogger(GrvAraligiHesapTest.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//    }
    
    
    
    
    
    
    
}
