public class BlockchainTest {
    public static void main(String[] args) throws Exception {
        Chain bc = Chain.getChain(2, "00");

        MemPool mp = new MemPool(1);

        User u1 = new User("hrishit", mp);
        User u2 = new User("divya", mp);
        User u3 = new User("ishita", mp);
        User u4 = new User("doraemon", mp);

        u1.setTransaction("Paid Rs 100 to Divya");
        u2.setTransaction("Paid Rs 200 to Ishita");
        u3.setTransaction("Paid Rs 150 to Hrishit");
        u4.setTransaction("I'm doraemon");

        Miner man = new Miner();
        
        Block newBl = man.mineBlock(bc.getPrefixString(), mp);
        bc.addBlock(newBl);

        bc.displayAll();
    }
}
