import java.security.MessageDigest;
import java.util.concurrent.BlockingDeque;
import java.nio.charset.StandardCharsets;

public abstract class Block { 
	private String hash;
	public String prev;
	private String data;
	private long timeStamp;
	private int nonce;

	// public Block(String data, long timeStamp) throws Exception {
    //     this.data = data;
    //     this.timeStamp = timeStamp;
    //     // this.hash = calculateBlockHash();
    // }

	public abstract void display();
	public abstract String getData();
	public abstract long getTimeStamp();
	public abstract int getNonce();
	public abstract void setNonce(int nonce);
	public abstract String calculateBlockHash() throws Exception;
	public abstract String getHash();
	public abstract void resetHash() throws Exception;
	public abstract String getPrev();
	public abstract void setPrev(String prev);
}



