package ciphers;

import org.junit.Assert;
import org.junit.Test;

public class ConvertTest {

    @Test
    public void testMatrisToStr() throws Exception {
        String expected="abcdØdefsØhjytØujhyØ";
        char[][] charDizi={{'a','b','c','d'},{'d','e','f','s'},{'h','j','y','t'},{'u','j','h','y'}};

        String  result = Convert.matrisToStr(charDizi);
        Assert.assertEquals(expected, result);
    }



    @Test
    public void testStrToMatris() throws Exception {
        String str="abcaØdefdfsØhjytØujhyØ";
        char[][] expected={{'a','b','c','d'},{'d','e','f','s'},{'h','j','y','t'},{'u','j','h','y'}};
        char[][] result = Convert.strToMatris(str);
       // Assert.assertArrayEquals(expected, result);
    }

    @Test
    public void testStrToMatris1() throws Exception {
        String str="abcd,defsss,hjyt,ujhy,";
        char[][] expected={{'a','b','c','d'},{'d','e','f','s'},{'h','j','y','t'},{'u','j','h','y'}};
        System.out.println(Convert.matrisToStr(Convert.strToMatris(str)));;
      //  Assert.assertArrayEquals(expected, result);
    }
}