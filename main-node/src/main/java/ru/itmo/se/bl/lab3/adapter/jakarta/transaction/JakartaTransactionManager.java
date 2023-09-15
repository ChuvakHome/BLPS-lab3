package ru.itmo.se.bl.lab3.adapter.jakarta.transaction;

import jakarta.transaction.*;
import ru.itmo.se.bl.lab3.adapter.javax.transaction.JavaxTransaction;

import java.util.Objects;

public class JakartaTransactionManager implements TransactionManager {
    private final javax.transaction.TransactionManager transactionManager;

    public JakartaTransactionManager(javax.transaction.TransactionManager transactionManager) {
        Objects.nonNull(transactionManager);

        this.transactionManager = transactionManager;
    }

    @Override
    public void begin() throws NotSupportedException, SystemException {
        try {
            transactionManager.begin();
        } catch (javax.transaction.NotSupportedException e) {
            throw new NotSupportedException(e.getMessage());
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        try {
            transactionManager.commit();
        } catch (javax.transaction.RollbackException e) {
            throw new RollbackException(e.getMessage());
        } catch (javax.transaction.HeuristicMixedException e) {
            throw new HeuristicMixedException(e.getMessage());
        } catch (javax.transaction.HeuristicRollbackException e) {
            throw new HeuristicRollbackException(e.getMessage());
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public int getStatus() throws SystemException {
        try {
            return transactionManager.getStatus();
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Transaction getTransaction() throws SystemException {
        try {
            return new JakartaTransaction(transactionManager.getTransaction());
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void resume(Transaction tobj) throws InvalidTransactionException, IllegalStateException, SystemException {
        try {
            transactionManager.resume(new JavaxTransaction(tobj));
        } catch (javax.transaction.InvalidTransactionException e) {
            throw new InvalidTransactionException(e.getMessage());
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void rollback() throws IllegalStateException, SecurityException, SystemException {
        try {
            transactionManager.rollback();
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, SystemException {
        try {
            transactionManager.setRollbackOnly();
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void setTransactionTimeout(int seconds) throws SystemException {
        try {
            transactionManager.setTransactionTimeout(seconds);
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public Transaction suspend() throws SystemException {
        try {
            return new JakartaTransaction(transactionManager.suspend());
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }
}
