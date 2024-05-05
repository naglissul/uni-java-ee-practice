package lt.vu.transactions;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.Transactional;
import java.io.Serializable;

@Named
@SessionScoped
public class Second implements Serializable {
    @Resource
    private TransactionSynchronizationRegistry tx;


    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void hehehe2() {
        System.out.println("Transaction key: " + tx.getTransactionKey());
    }
}
