package ru.itmo.se.bl.lab3.adapter.jakarta.transaction;

import jakarta.transaction.*;
import ru.itmo.se.bl.lab3.adapter.javax.transaction.JavaxSynchronization;

import javax.transaction.xa.XAResource;
import java.util.Objects;

public class JakartaTransaction implements Transaction {
    private final javax.transaction.Transaction transaction;

    public JakartaTransaction(javax.transaction.Transaction transaction) {
        Objects.nonNull(transaction);

        this.transaction = transaction;
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
    public boolean delistResource(XAResource xaRes, int flag) throws IllegalStateException, SystemException {
        try {
            return transaction.delistResource(xaRes, flag);
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public boolean enlistResource(XAResource xaRes) throws RollbackException, IllegalStateException, SystemException {
        try {
            return transaction.enlistResource(xaRes);
        } catch (javax.transaction.RollbackException e) {
            throw new RollbackException(e.getMessage());
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
    public void registerSynchronization(Synchronization sync) throws RollbackException, IllegalStateException, SystemException {
        try {
            transaction.registerSynchronization(new JavaxSynchronization(sync));
        } catch (javax.transaction.RollbackException e) {
            throw new RollbackException(e.getMessage());
        } catch (javax.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void rollback() throws IllegalStateException, SystemException {
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
}
