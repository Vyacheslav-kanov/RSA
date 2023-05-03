import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Encryption {
    private long p;
    private long q;
    private long n;
    private long phi;
    private long publicExponent;
    private long secretExponent;

    private long outX = 0;
    private long outY = 1;

    private String message = "";
    private List<Integer> intPool = new ArrayList<>();
    private BigInteger bigTest = BigInteger.ZERO;

    public Encryption(long p, long q) {
        this.p = p;
        this.q = q;

        n = p * q;
        System.out.println("N: " + n);

        phi = (p - 1) * (q - 1);
        System.out.println("phi: " + phi);

        publicExponent = calculatePublicExponent();
        System.out.println("Pub: " + publicExponent);

        secretExponent = calculateSecretExponent();
        System.out.println("Secret: " + secretExponent);
        System.out.println();
    }

    private long calculateSecretExponent() {
        return (1 + (2 * phi)) / publicExponent;
    }

    public static long calculateSecretExponentFromTesting(long exp, long phi){
        return (1 + (2 * phi))/exp;
    }

    private long calculatePublicExponent() {
        int e = 2;
        double count;

        while(e < phi){
            count = gcd(e, phi);
            if(count == 1)
                break;
            else
                e++;
        }
        return e;
    }

    public static long calculatePublicExponentFromTesting(long phi){
        int e = 2;
        double count;

        while(e < phi){
            count = gcd(e, phi);
            if(count == 1)
                break;
            else
                e++;
        }
        return e;
    }

    static long gcd(long a, long h)
    {
        long temp;
        while(true)
        {
            temp = a % h;
            if(temp == 0)
                return h;
            a = h;
            h = temp;
        }
    }

    public static String readFile(String path) {
        StringBuilder builder = new StringBuilder();
        try (FileReader reader = new FileReader(path)) {

            int i;
            while ((i = reader.read()) != -1) {

                builder.append((char) i);
            }
        } catch (IOException ex) {
        }

        return builder.toString();
    }

    public void encoding() {
        List result = new ArrayList<Integer>();
        int val = 0;
        int sym = 0;

        for(char e: message.toCharArray()){
            sym = e;
            val = (bigPow(new BigInteger(sym + ""), new BigInteger(publicExponent + "")).mod(new BigInteger(n + ""))).intValue();
            System.out.println("char: " + sym + " ecod: " + val);
            result.add(val);
        }

        charsToSymbols(result);
        intPool = result;
    }

    public void decoding() {
        List<Integer> result = new ArrayList<>();

        int code = 0;
        for(int e: intPool){
            code = (bigPow(new BigInteger(e + ""), new BigInteger(secretExponent + "")).mod(new BigInteger(n + ""))).intValue();
            System.out.println("char: " + e + " decod: " + code);
            result.add(code);
        }

        charsToSymbols(result);
        intPool = result;
    }

    public static BigInteger bigPow(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }

    private void charsToSymbols(List<Integer> list) {
        StringBuilder builder = new StringBuilder();

        for (int e : list) {
            builder.append((char) e);
        }
        message = builder.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
