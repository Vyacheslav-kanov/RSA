import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("¬ведите число p размера long:");
        long p = scan.nextLong();

        System.out.println("¬ведите число p размера long:");
        long q = scan.nextLong();

        System.out.println();
        Encryption encrypt = new Encryption(p, q);

        encrypt.setMessage(Encryption.readFile("src/main/resources/InputText.txt"));
        System.out.println("msg: " + encrypt.getMessage());

        encrypt.encoding();
        System.out.println();
        System.out.println("msg: " + encrypt.getMessage());
        System.out.println();

        encrypt.decoding();
        System.out.println();
        System.out.println("msg: " + encrypt.getMessage());
    }
}
