import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileInOutHandler {
    public static InputStream getInputStream(String filePath) throws IOException {
        return new FileInputStream(filePath);
    }
}
