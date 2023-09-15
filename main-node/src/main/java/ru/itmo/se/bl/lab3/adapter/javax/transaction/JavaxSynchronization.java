package ru.itmo.se.bl.lab3.adapter.javax.transaction;

import javax.transaction.Synchronization;
import java.util.Objects;

public class JavaxSynchronization implements Synchronization {
    private final jakarta.transaction.Synchronization synchronization;

    public JavaxSynchronization(jakarta.transaction.Synchronization synchronization) {
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
