import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Block { 
	private String hash;
	private String prev;

	private String data;
	private long timeStamp;

	private int nonce;

	public Block(String data, String previousHash, long timeStamp) {
        	this.data = data;
        	this.prev = previousHash;
        	this.timeStamp = timeStamp;
        	this.hash = calculateBlockHash();
    	}

	public String calculateBlockHash() {
		String dataToHash = prev
      		+ Long.toString(timeStamp) 
      		+ Integer.toString(nonce) 
      		+ data;
    
		MessageDigest digest = null;
    		byte[] bytes = null;
    
		try {
        		digest = MessageDigest.getInstance("SHA-256");
        		bytes = digest.digest(dataToHash.getBytes("UTF_8"));
    		} catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
        		System.out.println(ex.getMessage());
    		}
    		
		StringBuffer buffer = new StringBuffer();
    		for (byte b : bytes) {
        		buffer.append(String.format("%02x", b));
    		}
    
		return buffer.toString();
	}

	public String getHash() {
		return hash;
	}

	public String getPrev() {
		return prev;
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
}
