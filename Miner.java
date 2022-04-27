import java.time.ZonedDateTime;

public class Miner {
	public int flag = 0;

	public Block mineBlock(String prefixString, MemPool pool) throws Exception {
    	String transactionString = pool.getTransactions();
		Block bl;
		if(flag!=0)
		{
			System.out.println("Closed block created");
			bl = new ClosedBlock(transactionString, ZonedDateTime.now().toInstant().toEpochMilli());
		}

		else
		{
			bl = new GenesisBlock(transactionString, ZonedDateTime.now().toInstant().toEpochMilli());
			System.out.println("Genesis block created");
			flag = 1;
		}

		while (!(bl.getHash().substring(0, prefixString.length()).equals(prefixString))) {
        	bl.setNonce(bl.getNonce() + 1);
        	bl.resetHash();
			//System.out.println("iteration");
    	}
		//System.out.println("exited loop");

		pool.clearTransactions();

    	return bl;
	}
}
