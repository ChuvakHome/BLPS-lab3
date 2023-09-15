package ru.itmo.se.bl.lab3.adapter.javax.transaction;

import ru.itmo.se.bl.lab3.adapter.jakarta.transaction.JakartaSynchronization;

import javax.transaction.*;
import javax.transaction.xa.XAResource;
import java.util.Objects;

public class JavaxTransaction implements Transaction {
    private final jakarta.transaction.Transaction transaction;

    public JavaxTransaction(jakarta.transaction.Transaction transaction) {
        Objects.requireNonNull(transaction);

        this.transaction = transaction;
    }

    @Override
    public void commit() throws RollbackException, HeuristicMixedException, HeuristicRollbackException, SecurityException, IllegalStateException, SystemException {
        try {
            transaction.commit();
        } catch (jakarta.transaction.RollbackException e) {
            throw new RollbackException(e.getMessage());
        } catch (jakarta.transaction.HeuristicMixedException e) {
            throw new HeuristicMixedException(e.getMessage());
        } catch (jakarta.transaction.HeuristicRollbackException e) {
            throw new HeuristicRollbackException(e.getMessage());
        } catch (jakarta.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public boolean delistResource(XAResource xaRes, int flag) throws IllegalStateException, SystemException {
        try {
            return transaction.delistResource(xaRes, flag);
        } catch (jakarta.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public boolean enlistResource(XAResource xaRes) throws RollbackException, IllegalStateException, SystemException {
        try {
            return transaction.enlistResource(xaRes);
        } catch (jakarta.transaction.RollbackException e) {
            throw new RollbackException(e.getMessage());
        } catch (jakarta.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public int getStatus() throws SystemException {
        try {
            return transaction.getStatus();
        } catch (jakarta.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void registerSynchronization(Synchronization sync) throws RollbackException, IllegalStateException, SystemException {
        try {
            transaction.registerSynchronization(new JakartaSynchronization(sync));
        } catch (jakarta.transaction.RollbackException e) {
            throw new RollbackException(e.getMessage());
        } catch (jakarta.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void rollback() throws IllegalStateException, SystemException {
        try {
            transaction.rollback();
        } catch (jakarta.transaction.SystemException e) {
            throw new SystemException(e.getMessage());
        }
    }

    @Override
    public void setRollbackOnly() throws IllegalStateException, SystemException {
        try {
            transaction.setRollbackOnly();
        } catch (jakarta.transaction.SystemException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
