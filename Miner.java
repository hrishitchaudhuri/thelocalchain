import java.time.ZonedDateTime;
// int flag = 0;

public class Miner {

	public Block mineBlock(String prefixString, MemPool pool, Chain bc) throws Exception {
    	String transactionString = pool.getTransactions();
		Block bl;
		if(bc.getSize() != 0)
		{
			bl = new ClosedBlock(transactionString, ZonedDateTime.now().toInstant().toEpochMilli());
			System.out.println("Closed block created");

		}

		else
		{
			bl = new GenesisBlock(transactionString, ZonedDateTime.now().toInstant().toEpochMilli());
			System.out.println("Genesis block created");
			//flag++; 
		}
		//System.out.println("Flag: " + flag);

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
