import java.security.*;

public class User {
	private String userName;
	private PublicKey userPublicKey;
	private PrivateKey userPrivateKey;

	private MemPool publicMemPool;

	public User(String userName, MemPool mp) throws Exception {
		this.userName = userName;
		this.publicMemPool = mp;

		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		KeyPair pair = generator.generateKeyPair();

		this.userPublicKey = pair.getPublic();
		this.userPrivateKey = pair.getPrivate();
	}

	public String getUserName() {
		return this.userName;
	}

	public PublicKey getUserPublicKey() {
		return this.userPublicKey;
	}

	public MemPool getUserMemPool() {
		return this.publicMemPool;
	}

	public void setMemPool(MemPool mp) {
		this.publicMemPool = mp;
	}

	public void setTransaction(String transaction, String transacName) throws Exception {
		String transactionString = transacName + "~" + userName + ">>" + transaction;
		byte[] encryptedTransaction = TransactionEncrypter.encryptTransaction(transactionString, userPrivateKey);
		byte[] hashedTransaction = TransactionHasher.hashTransaction(transactionString);

		publicMemPool.addTransaction(encryptedTransaction, hashedTransaction, userPublicKey);
	}
}
