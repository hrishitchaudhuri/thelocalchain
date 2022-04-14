import java.util.*;
import java.security.*;

public class MemPool {
	private List<String> transactions;

	public MemPool() {
		transactions = new ArrayList<String>();
	}

	public void addTransaction(byte[] trans, byte[] hash, PublicKey userPublicKey) throws Exception {
		String original = TransactionDecrypter.decryptTransaction(trans, userPublicKey);

		byte[] verifyHash = TransactionHasher.hashTransaction(original);

		if (verifyHash.equals(hash)) {
			transactions.add(original);
		}
	}
}
