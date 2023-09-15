package ru.itmo.se.bl.lab3.adapter.jakarta.transaction;

import jakarta.transaction.*;

import java.util.Objects;

public class JakartaUserTransaction implements UserTransaction {
    private final javax.transaction.UserTransaction transaction;

    public JakartaUserTransaction(javax.transaction.UserTransaction transaction) {
        Objects.nonNull(transaction);

        this.transaction = transaction;
    }

    @Override
    public void begin() throws NotSupportedException, SystemException {
        try {
            transaction.begin();
        } catch (javax.transaction.NotSupportedException e) {
            throw new NotSupportedException(e.getMessage());
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        try {
            transaction.commit();
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
    public void rollback() throws IllegalStateException, SecurityException, SystemException {
        try {
            transaction.rollback();
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, SystemException {
        try {
            transaction.setRollbackOnly();
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public int getStatus() throws SystemException {
        try {
            return transaction.getStatus();
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void setTransactionTimeout(int seconds) throws SystemException {
        try {
            transaction.setTransactionTimeout(seconds);
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }
}
