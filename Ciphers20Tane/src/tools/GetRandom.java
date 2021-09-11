package tools;

import java.security.SecureRandom;

public class GetRandom {
    SecureRandom secureRandom = new SecureRandom();

    //random sayı return eder ancak string olarak yani "123" gibi
    public String asStr(int i){return String.valueOf(secureRandom.nextInt(i));}


    //random sayı return eder 123 gibi
    public int  asInt(int i){return secureRandom.nextInt(i);}
}
