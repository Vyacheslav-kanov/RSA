import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigInteger;

public class TestEncryption {

    private static long p = 53;
    private static long q = 67;
    private static long phi = (p - 1) * (q - 1);
    private static long pub = 0;

    @Test
    @Before
    public void calculatePublicExponent(){
        pub = Encryption.calculatePublicExponentFromTesting(phi);
        Assert.assertTrue(pub == 5);
    }

    @Test
    public void greatestCommonDivisor(){
        long gcd = Encryption.gcd(5, phi);
        Assert.assertTrue(gcd == 1);
    }

    @Test
    public void calculateSecretExponent(){
        long secret = Encryption.calculateSecretExponentFromTesting(pub, phi);
        Assert.assertTrue(secret == 1373);
    }

    @Test
    public void readFile(){
        String str = "Write a string encryption function using the RSA algorithm.";
        Assert.assertTrue(Encryption.readFile("src/main/resources/testInputText.txt").equals(str));
    }

    @Test
    public void bigPow(){
        BigInteger val1 = new BigInteger("5");
        BigInteger val2 = new BigInteger("5");
        BigInteger result = new BigInteger("3125");
        BigInteger pow = Encryption.bigPow(val1, val2);

        Assert.assertTrue(pow.equals(result));
    }

    @Test
    @After
    public void decoding(){
        Encryption encryption = new Encryption(p, q);
        String result = Encryption.readFile("src/main/resources/testInputText.txt");

        encryption.setMessage(result);
        encryption.encoding();
        encryption.decoding();

        Assert.assertTrue(encryption.getMessage().equals(result));
    }
}
