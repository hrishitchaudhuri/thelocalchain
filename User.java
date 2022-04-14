import java.security.*;
import javax.crypto.Cipher;

public class User {
	private String userName;
	private PublicKey userPublicKey;
	private PrivateKey userPrivateKey;

	private MemPool publicMemPool;

	public User(String userName, MemPool mp) {
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

	public void setTransaction(String transaction) {
		Cipher encryptCipher = Cipher.getInstance("RSA");
		encryptCipher.init(Cipher.ENCRYPT_MODE, userPublicKey);
	}
}
