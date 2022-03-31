public class Chain {
	static List<Block> chain;
	int prefix = 8;
	String prefixString = "amlcjdsh"

	static Chain blockChain = new Chain();

	private Chain() {
		chain = new ArrayList<>();
	}

	public addBlock(Block bl_) {
		assertTrue(bl_.getHash().substring(0, prefix).equals(prefixString);
		blockChain.chain.add(bl_);
	}
}
