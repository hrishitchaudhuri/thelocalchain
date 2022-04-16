public class TransactionHelper {
    
    public void setTransaction(String transaction, String transacName, String userName) throws Exception {
		String transactionString = transacName + "~" + userName + ">>" + transaction;
		byte[] encryptedTransaction = TransactionEncrypter.encryptTransaction(transactionString, User.getUserPrivateKey);
		byte[] hashedTransaction = TransactionHasher.hashTransaction(transactionString);

		publicMemPool.addTransaction(encryptedTransaction, hashedTransaction, User.getUserPublicKey);
	}
}
