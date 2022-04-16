import java.util.*;

public class BlockchainTest {
    public static void main(String[] args) throws Exception {
        Chain bc = Chain.getChain(2, "00");
        ArrayList<User> usersList = new ArrayList<>();

        MemPool mp = new MemPool(1);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of users:");
        int no_of_users = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the users:");
        for (int i = 0; i < no_of_users; i++) {
            String user_name = sc.nextLine();
            usersList.add(new User(user_name, mp));
          }
        
        // System.out.println("Enter the no of transactions:");
        // int no_of_trans = sc.nextInt();
        // sc.nextLine(); 
        System.out.println("Enter the corresponding transaction name, and transaction details in the nextline:");
        for (int i = 0; i < no_of_users; i++) {
            String transacName = sc.nextLine();
            String transac= sc.nextLine();
            usersList.get(i).setTransaction(transac, transacName);

            System.out.println(transacName);
          }


        Miner man = new Miner();
        
        Block newBl = man.mineBlock(bc.getPrefixString(), mp);
        bc.addBlock(newBl);

        bc.displayAll();
    }
}
