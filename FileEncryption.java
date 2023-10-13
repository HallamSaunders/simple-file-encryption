import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class FileEncryption {
    public static byte[] fileEncryption() {
        Scanner scanner = new Scanner(System.in);
        String filePathSpecified = scanner.nextLine();

        try {
            InputStream inputStream = FileInOut.getInputStream(filePathSpecified);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //ADD RETURN STATEMENT WHEN ENCRYPTION IS DONE
}
