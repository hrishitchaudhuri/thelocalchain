import java.util.*;

public class Chain {
	List<Block> chain;
	int prefix;
	String prefixString;

	public static Chain blockChain;

	private Chain(int prefix, String prefixString) {
		this.chain = new ArrayList<Block>();
		this.prefix = prefix;
		this.prefixString = prefixString;
	}

	public void verifychain(){
		// Chain vchain= getChain();

	 	for (int i = blockChain.chain.size() - 1; i > 0; i--) 
		{
			System.out.println("Blockchain created successfully");
	 		Block b = blockChain.chain.get(i);
			Block prevblock = blockChain.chain.get(i-1);
	 		if (prevblock.getHash().equals(b.getPrev())) {
				System.out.println(prevblock.getHash() + "matches" + b.getPrev());
	 			// System.out.println("Block number: "+ i);
	 		} else {
	 			throw new RuntimeException("Chain Invalid");
	 		}
		}
	}

	public void addBlock(Block bl_) {
		if (bl_.getHash().substring(0, prefix).equals(prefixString)) {
			if (blockChain.chain.size() >= 1) {
				System.out.println("Adding new block");
				verifychain();

				String previous = blockChain.chain.get(blockChain.chain.size() - 1).getHash();
				bl_.setPrev(previous);
			}

			else {
				String previous = "0000";
				bl_.setPrev(previous);
				// System.out.println("ok genesis ");
	
			}

			blockChain.chain.add(bl_);
		}

		else {
			System.out.println("[ERR] Block has illegal hash value");
		}
	}

	static public Chain getChain(int prefix, String prefixString) {
		if (blockChain == null) {
			blockChain = new Chain(prefix, prefixString);
		}

		return blockChain;
	}

	static public Chain getChain() {
		return blockChain;
	}

	static public int getSize() {
		System.out.println(blockChain.chain.size());
		return blockChain.chain.size();
	}

	public String getPrefixString() {
		return prefixString;
	}

	public void displayAll() {
		for (Block block : chain) {
			block.display();
		}
	}
	
}