import java.util.*;

public class Chain {
	List<Block> chain;
	int prefix;
	String prefixString;

	static Chain blockChain;

	private Chain(int prefix, String prefixString) {
		this.chain = new ArrayList<Block>();
		this.prefix = prefix;
		this.prefixString = prefixString;
	}

	public void addBlock(Block bl_) {
		if (bl_.getHash().substring(0, prefix).equals(prefixString)) {
			if (blockChain.chain.size() >= 1) {
				String previous = blockChain.chain.get(blockChain.chain.size() - 1).getHash();
				bl_.setPrev(previous);
			}

			else {
				String previous = "0000";
				bl_.setPrev(previous);
			}

			blockChain.chain.add(bl_);
		}

		else {
			System.out.println("[ERR] Block has illegal hash value.");
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

	public String getPrefixString() {
		return prefixString;
	}

	public void displayAll() {
		for (Block block : chain) {
			block.display();
		}
	}
}
