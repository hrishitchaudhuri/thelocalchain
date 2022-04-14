import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class TransactionDecrypter {
    static public String decryptTransaction(byte[] transactionByteArray, PublicKey userPublicKey) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, userPublicKey);

        byte[] decryptedMessageBytes = decryptCipher.doFinal(transactionByteArray);
        String decryptedMessage = new String(decryptedMessageBytes, StandardCharsets.UTF_8);

        return decryptedMessage;
    } 
}
