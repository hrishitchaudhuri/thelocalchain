import java.security.MessageDigest;
import java.nio.charset.StandardCharsets;

public class Block { 
	private String hash;
	private String prev;

	private String data;
	private long timeStamp;

	private int nonce;

	public Block(String data, long timeStamp) throws Exception {
        this.data = data;
        this.timeStamp = timeStamp;
        this.hash = calculateBlockHash();
    }

	public String calculateBlockHash() throws Exception {
		String hashableData = prev
      		+ Long.toString(timeStamp) 
      		+ Integer.toString(nonce) 
      		+ data;
    
		MessageDigest digest = null;
    	byte[] bytes = null;

        digest = MessageDigest.getInstance("SHA-256");
        bytes = digest.digest(hashableData.getBytes(StandardCharsets.UTF_8));
    		
		StringBuffer buffer = new StringBuffer();
    	for (byte b : bytes) {
        	buffer.append(String.format("%02x", b));
    	}
    
		return buffer.toString();
	}

	public void display() {
		System.out.println(data + "@" + Long.toString(timeStamp) + ":" + hash);
	}

	public String getHash() {
		return hash;
	}

	public void resetHash() throws Exception {
		this.hash = calculateBlockHash();
	}

	public String getPrev() {
		return prev;
	}

	public void setPrev(String prev) {
		this.prev = prev;
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
}
