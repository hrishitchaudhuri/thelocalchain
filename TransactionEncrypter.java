import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;

public class TransactionEncrypter {
    static public byte[] encryptTransaction(String transaction, PrivateKey userPrivateKey) throws Exception  {
        Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, userPrivateKey);

		byte[] transactionByteArray = transaction.getBytes(StandardCharsets.UTF_8);
		byte[] encryptedTransaction = encryptCipher.doFinal(transactionByteArray);

        return encryptedTransaction;
    }
}