public class Miner {
	static int count = 0;
	
	public String mineBlock(int prefix) {
		String prefixString = new String(new char[prefix]).replace('\0', '0');
    		
		while (!hash.substring(0, prefix).equals(prefixString)) {
        		nonce++;
        		hash = calculateBlockHash();
    		}

    		return hash;
	}
}
