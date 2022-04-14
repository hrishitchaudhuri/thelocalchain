import java.time.ZonedDateTime;

public class Miner {
	public Block mineBlock(String prefixString, MemPool pool) throws Exception {
    	String transactionString = pool.getTransactions();

		Block bl = new Block(transactionString, ZonedDateTime.now().toInstant().toEpochMilli());

		while (!bl.getHash().substring(0, prefixString.length()).equals(prefixString)) {
        	bl.setNonce(bl.getNonce() + 1);
        	bl.resetHash();
    	}

		pool.clearTransactions();

    	return bl;
	}
}
