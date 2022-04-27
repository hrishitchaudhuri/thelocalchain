import java.util.*;
import java.sql.*;


public class BlockchainTest {
    public static void main(String[] args) throws Exception {
        Chain bc = Chain.getChain(2, "00");
        ArrayList<User> usersList = new ArrayList<>();
        int flag=1;
        MemPool mp = new MemPool(1);
        int existing_users=0;
        int total_users=0;

        while (flag!=0)
        {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of users:");
        int new_users = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the users:");
        for ( int i = 0; i < new_users; i++) {
            String user_name = sc.nextLine();
            usersList.add(new User(user_name, mp));
          }
        total_users=existing_users+new_users;
        
        // System.out.println("Enter the no of transactions:");
        // int no_of_trans = sc.nextInt();
        // sc.nextLine(); 
        System.out.println("Enter the corresponding transaction name, and transaction details in the nextline:");
        for (int i = existing_users; i < total_users; i++) {
            String transacName = sc.nextLine();
            String transac= sc.nextLine();
            String usern= usersList.get(i).getUserName();
            User u = usersList.get(i);
            TransactionHelper.setTransaction(u,transac, transacName, usern);
            // usersList.get(i).setTransaction(transac, transacName);

            //System.out.println("The username is -" +usersList.get(i).getUserName());
          }

        Miner man = new Miner();
        
        Block newBl = man.mineBlock(bc.getPrefixString(), mp);
        bc.addBlock(newBl);

        bc.displayAll();
        System.out.println("Do u wanna exit: 0. Yes 1. No");
        flag=sc.nextInt();
        existing_users=total_users;
        }
    }
}



