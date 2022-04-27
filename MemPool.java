import java.util.*;

import javax.print.event.PrintEvent;

import java.security.*;
import java.sql.*;

public class MemPool {
	private int id;
	private List<String> transactions;

	public MemPool(int id) {
		this.id = id;
		transactions = new ArrayList<String>();
	}

	public void addTransaction(User u, byte[] trans, byte[] hash, PublicKey userPublicKey) throws Exception {
		String original = TransactionDecrypter.decryptTransaction(trans, u.getUserPublicKey());

		byte[] verifyHash = TransactionHasher.hashTransaction(original);

		if (Arrays.equals(hash, verifyHash)) {

			//to add transaction name to db for retrieval later
			String transacName = original.substring(0, original.indexOf("~"));
			// System.out.println(transacName);

			transactions.add(original);
			writeToDB(original);
		}

		else {
			System.out.println("[WARN] Transaction labelled " + original + " does not match.");
		}
	}

	public String getTransactions() {
		return id + ":" + String.join(",", transactions);
	}

	public void clearTransactions() {
		transactions.clear();
	}

	void writeToDB(String transaction) {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			Class.forName("org.postgresql.Driver");
			//Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "root");
			Connection con = DriverManager .getConnection("jdbc:postgresql://localhost:5432/blockchain", "postgres", "12345678");
			PreparedStatement stmt = con.prepareStatement("insert into blockchain values(?, ?, ?) ");
			//PreparedStatement stmt = con.prepareStatement("insert into test values( \"A\" , \"B\" , \"C\" ) ");
			//PreparedStatement stmt = con.prepareStatement("insert into test values( \"123\" ) ");
			String[] parts = transaction.split("~");

			
			String transacName = parts[0];
			String userName =  parts[1];
			String transData =  parts[2];

			System.out.println(transData);

			
			stmt.setString(1, transacName);
			stmt.setString(2, userName);
			stmt.setString(3, transData); 
			stmt.executeUpdate();
			System.out.println(stmt);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Database Error");
		}
	}
}
