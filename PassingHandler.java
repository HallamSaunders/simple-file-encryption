import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Scanner;

public class PassingHandler {
    public SecretKey generateKey(String[] args) {
        try {
            //Generate random AES key
            SecureRandom random = new SecureRandom();
            byte[] keyBytes = new byte[16];
            random.nextBytes(keyBytes);
            return new SecretKeySpec(keyBytes, "AES");
        } catch (Exception e) {
            e.printStackTrace();
        }

    /*public static byte[] fileEncryption() {
        Scanner scanner = new Scanner(System.in);
        String filePathSpecified = scanner.nextLine();

        try {
            InputStream inputStream = FileInOutHandler.getInputStream(filePathSpecified);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //ADD RETURN STATEMENT WHEN ENCRYPTION IS DONE*/
    }
}
