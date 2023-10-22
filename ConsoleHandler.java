import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.Objects;
import java.util.Scanner;

public class ConsoleHandler {
    public static void main(String[] args) throws Exception {
        System.out.println("Would you like to encrypt or decrypt a file? (e/d).");
        Scanner scanner = new Scanner(System.in);

        boolean valid = false;
        while(!valid) {
            String response = scanner.nextLine();
            if (Objects.equals(response, "e")) {
                valid = true;
                Encrypt();
            } else if (Objects.equals(response, "d")) {
                valid = true;
                Decrypt();
            } else {
                System.out.println("Invalid response, try again.");
            }
        }
    }

    public static void Encrypt() throws Exception {
        System.out.println("Enter the file path of the file you wish to encrypt.");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();
        try {
            //Generate AES key randomly
            SecureRandom random = new SecureRandom();
            byte[] keyBytes = new byte[16];
            random.nextBytes(keyBytes);
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
            System.out.println("Key is: " + Arrays.toString(keyBytes));

            //Generate encrypted file
            File inputFile = new File(filePath);
            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] encryptedBytes = EncryptDecrypt.encryptFileStream(inputStream, secretKey);

            //Save the encrypted file
            System.out.println("What would you like to call this file?");
            String fileName = scanner.nextLine();
            SaveFile(encryptedBytes, fileName, pruneFilePath(filePath));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void SaveFile(byte[] encryptedFileByte, String fileName, String filePath) throws IOException {
        File unencryptedFile = new File(filePath + fileName);
        FileOutputStream outputStream = new FileOutputStream(unencryptedFile);
        outputStream.write(encryptedFileByte);
        outputStream.close();
        System.out.println("File encrypted and saved as " + fileName + ", at: " + filePath);
    }

    public static String pruneFilePath(String filePath){
         for(int i = filePath.length()-1; i>=0; i--){
             if(filePath.charAt(i) != '\\') {
                 filePath = filePath.substring(0, i) + filePath.substring(i + 1);
             } else{
                 break;
             }
         }
         return filePath;
    }

    public static void Decrypt() throws IOException {
        System.out.println("Enter the file path of the file you wish to encrypt.");
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Enter the key used to encrypt the file.");
        String keyString = reader.readLine();
        String[] keyStringArray = keyString.split(", ");
        byte[] keyBytes = new byte[16];
        for(int i = 0; i < keyBytes.length; i++){
            int num = Integer.parseInt(keyStringArray[i]);
            keyBytes[i] = (byte) num;
        }

        SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");
        try {
            File encryptedFile = new File(filePath);
            byte[] decryptedBytes = EncryptDecrypt.decryptFileStream(encryptedFile, keyBytes);

            //Save the decrypted file
            System.out.println("What would you like to call this file?");
            String fileName = scanner.nextLine();
            SaveFile(decryptedBytes, fileName, pruneFilePath(filePath));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
