import java.util.*;

public class Chain {
	List<Block> chain;
	int prefix;
	String prefixString = "amlcjdsh";

	static Chain blockChain = new Chain(8, "amdshjke");

	private Chain(int prefix, String prefixString) {
		this.chain = new ArrayList<Block>();
		this.prefix = prefix;
		this.prefixString = prefixString;
	}

	public void addBlock(Block bl_) {
		if (bl_.getHash().substring(0, prefix).equals(prefixString)) {
			blockChain.chain.add(bl_);
		}

		else {
			System.out.println("[ERR] Block has illegal hash value.");
		}
	}
}
