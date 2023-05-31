package ru.fyodor;

import ru.fyodor.client.Account;
import ru.fyodor.client.AccountServiceImpl;
import ru.fyodor.p2p.Node;
import ru.fyodor.services.BlockChain;
import ru.fyodor.services.TransactionService;

public class AppClient {
    public static void main(String[] args) {
        try {
            foo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void foo() throws Exception {
        AccountServiceImpl as = new AccountServiceImpl();
        Account account = as.createAccount(
                "seed".getBytes(),
                "127.0.0.1",
                8081
                );

        BlockChain blockChain = BlockChain.generateBlockChain(as.getPeer());
        TransactionService ts = new TransactionService(blockChain);

        Node node = new Node(as.getPeer(), ts);
        node.connectToNodes();
    }
}
