import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class EncryptDecrypt {
    private static final byte[] buffer = new byte[1024];
    public static byte[] encryptFileStream(InputStream inputStream, SecretKey secretKey) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
            outputStream.write(encryptedBytes);
        }

        byte[] finalBytes = cipher.doFinal();
        outputStream.write(finalBytes);

        return outputStream.toByteArray();
    }

    public static byte[] decryptFileStream(File inputFile, byte[] keyBytes) throws Exception {
        FileInputStream inputStream = new FileInputStream(inputFile);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] decryptedBytes = cipher.update(buffer, 0, bytesRead);
            outputStream.write(decryptedBytes);
        }

        byte[] finalBytes = cipher.doFinal();
        outputStream.write(finalBytes);

        return outputStream.toByteArray();
    }
}
