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

	public void display() {
		System.out.println(data + "@" + Long.toString(timeStamp) + ":" + hash);
	}
	
	public String getData() {
		return data;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public int getNonce() {
		return nonce;
	}

	public void setNonce(int nonce) {
		this.nonce = nonce;
	}

	public abstract String calculateBlockHash() throws Exception;
	public abstract String getHash();
	public abstract void resetHash() throws Exception;
	public abstract String getPrev();
	public abstract void setPrev(String prev);
}



