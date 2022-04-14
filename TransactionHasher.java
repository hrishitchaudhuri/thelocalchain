import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class TransactionHasher {
    static public byte[] hashTransaction(String transaction) throws Exception {
        MessageDigest digest = null;
    	byte[] bytes = null;

        digest = MessageDigest.getInstance("SHA-256");
        bytes = digest.digest(transaction.getBytes(StandardCharsets.UTF_8));

        return bytes;
    }
}
