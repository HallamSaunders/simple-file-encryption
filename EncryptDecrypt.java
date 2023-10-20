import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class EncryptDecrypt {
    public static byte[] encryptFileStream(InputStream inputStream, SecretKey secretKey) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byte[] encryptedBytes = cipher.update(buffer, 0, bytesRead);
            outputStream.write(encryptedBytes);
        }

        byte[] finalBytes = cipher.doFinal();
        outputStream.write(finalBytes);

        return outputStream.toByteArray();
    }
}
