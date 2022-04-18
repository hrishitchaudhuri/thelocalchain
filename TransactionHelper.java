public class TransactionHelper {
    
    public static void setTransaction(User u,String transaction, String transacName, String userName) throws Exception {
		String transactionString = transacName + "~" + userName + ">>" + transaction;
		byte[] encryptedTransaction = TransactionEncrypter.encryptTransaction(transactionString, u.getUserPrivateKey());
		byte[] hashedTransaction = TransactionHasher.hashTransaction(transactionString);

		u.getUserMemPool().addTransaction(u, encryptedTransaction, hashedTransaction, u.getUserPublicKey());
	}
}
