package ru.itmo.se.bl.lab3.adapter.jakarta.transaction;


import jakarta.transaction.Synchronization;

import java.util.Objects;

public class JakartaSynchronization implements Synchronization {
    private final javax.transaction.Synchronization synchronization;

    public JakartaSynchronization(javax.transaction.Synchronization synchronization) {
        Objects.nonNull(synchronization);

        this.synchronization = synchronization;
    }

    @Override
    public void beforeCompletion() {
        synchronization.beforeCompletion();
    }

    @Override
    public void afterCompletion(int status) {
        synchronization.afterCompletion(status);
    }
}
