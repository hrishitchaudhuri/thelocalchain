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
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager .getConnection("jdbc:postgresql://localhost:5432/blockchain", "postgres", "12345678");
			PreparedStatement stmt = con.prepareStatement("insert into blockchain values(?, ?, ?) ");
			String[] parts = transaction.split("~");

			String transacName = parts[0];
			String userName =  parts[1];
			String transData =  parts[2];

			//System.out.println(transData);
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

	static private void displayDB(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.println(rs.getString("transaction_name") + "\t"
                    + rs.getString("user_name") + "\t"
                    + rs.getString("transaction_data"));

        }
    }

	static void retrieveTransaction(String transName) {
        String SQL = "SELECT \"transaction_name\", \"user_name\", \"transaction_data\""
                + "FROM blockchain "
                + "WHERE transaction_name = ?";

        try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager .getConnection("jdbc:postgresql://localhost:5432/blockchain", "postgres", "12345678");
            PreparedStatement pstmt = con.prepareStatement(SQL);
			pstmt.setString(1, transName);
			ResultSet rs = pstmt.executeQuery();
			displayDB(rs);
		}
    
		catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }	
}
