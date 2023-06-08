// import java.io.*;
// import java.security.*;
// import java.util.Scanner;

// import javax.crypto.*;

// public class FileEncryptionUtil {
//     static String filePath;
//         // 輸入要加密或解密的檔案路徑

//     static boolean isEncrypt;
//         // 判斷要加密還是解密
//         // true: 加密 / false: 解密

//     public FileEncryptionUtil() throws Exception 
//     {
//         if (isEncrypt) 
//         {
//             // 生成RSA公鑰和私鑰
//             KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
//             keyGen.initialize(2048);
//             KeyPair keyPair = keyGen.generateKeyPair();
//             PublicKey publicKey = keyPair.getPublic();
//             PrivateKey privateKey = keyPair.getPrivate();

//             // 保存私鑰到檔案中
//             ObjectOutputStream publicKeyOS = new ObjectOutputStream(
//                     new FileOutputStream("private_key.txt"));
//             publicKeyOS.writeObject(publicKey);
//             publicKeyOS.close();

//             // 創建Cipher物件並加密檔案
//             Cipher cipher = Cipher.getInstance("RSA");
//             cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//             FileInputStream inputStream = new FileInputStream(filePath);
//             byte[] inputBytes = new byte[(int) new File(filePath).length()];
//             inputStream.read(inputBytes);
//             byte[] outputBytes = cipher.doFinal(inputBytes);
//             FileOutputStream outputStream = new FileOutputStream(filePath + ".encrypted");
//             outputStream.write(outputBytes);
//             inputStream.close();
//             outputStream.close();

//             System.out.println("加密完成！私鑰已保存至 private_key.txt");
//         }
//         else 
//         {
//             // 輸入私鑰的檔案路徑
//             String privateKeyPath = "private_key.txt";

//             // 讀取私鑰檔案
//             ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(privateKeyPath));
//             PrivateKey privateKey = (PrivateKey) inputStream.readObject();
//             inputStream.close();

//             // 創建Cipher物件並解密檔案
//             Cipher cipher = Cipher.getInstance("RSA");
//             cipher.init(Cipher.DECRYPT_MODE, privateKey);

//             try (FileInputStream inputStream2 = new FileInputStream(filePath)) {
//                 byte[] inputBytes = new byte[(int) new File(filePath).length()];
//                 inputStream2.read(inputBytes);
//                 byte[] outputBytes = cipher.doFinal(inputBytes);
//                 FileOutputStream outputStream = new FileOutputStream(filePath.replace(".encrypted", ""));
//                 outputStream.write(outputBytes);
//             }
//             System.out.println("解密完成！私鑰已刪除。");

//             // 刪除私鑰
//             File privateFile = new File(privateKeyPath);
//             privateFile.delete();
//         }
//     }
//         public static void main(String[] args) throws Exception 
//         {
//             // if (args.length < 2)
//             // {
//             //     System.out.println("USAGE: java FileEncryptionUtil file_path");	
//             //     System.exit(1);
//             // }
//             // filePath = args[0];
//             // isEncrypt = args[1].equals("encrypt") ? true : false;
//             Scanner scanner = new Scanner(System.in);
//             System.out.println("請輸入檔案路徑：");
//             filePath = scanner.nextLine();
//             System.out.println("請輸入要加密或解密：");
//             isEncrypt = scanner.nextLine().equals("encrypt") ? true : false;
//             scanner.close();
//             FileEncryptionUtil fileEncryptionUtil = new FileEncryptionUtil();
//     }
// }
