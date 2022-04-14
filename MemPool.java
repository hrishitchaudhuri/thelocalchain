import java.util.*;
import java.security.*;

public class MemPool {
	private int id;
	private List<String> transactions;

	public MemPool(int id) {
		this.id = id;
		transactions = new ArrayList<String>();
	}

	public void addTransaction(byte[] trans, byte[] hash, PublicKey userPublicKey) throws Exception {
		String original = TransactionDecrypter.decryptTransaction(trans, userPublicKey);

		byte[] verifyHash = TransactionHasher.hashTransaction(original);

		if (Arrays.equals(hash, verifyHash)) {
			transactions.add(original);
		}

		else {
			System.out.println("[WARN] Transaction labelled " + original + " does not match.");
		}
	}

	public String getTransactions() {
		return id + ":" + String.join(",", transactions);
	}

	public void clearTransactions() {
		transactions.clear();
	}
}
