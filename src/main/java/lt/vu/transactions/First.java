package lt.vu.transactions;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;

@Named
@SessionScoped
public class First implements java.io.Serializable{
    @Resource
    private TransactionSynchronizationRegistry tx;

    @Inject
    private Second second;

    public Second getSecond() {
        return second;
    }

    @Transactional
    public void hohoho() {
        System.out.println("Transaction key: " + tx.getTransactionKey());
        second.hehehe2();
    }
}
