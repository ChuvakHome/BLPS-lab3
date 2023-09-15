package ru.itmo.se.bl.lab3.config;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;
import jakarta.transaction.TransactionManager;
import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import ru.itmo.se.bl.lab3.adapter.jakarta.transaction.JakartaTransactionManager;
import ru.itmo.se.bl.lab3.data.UsersXmlCollection;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableTransactionManagement
public class AppConfig {
    @Value("${stomp.jms.factory.broker-uri}")
    private String brokerURI;

    @Value("${stomp.jms.factory.username}")
    private String username;

    @Value("${stomp.jms.factory.password}")
    private String password;

    private BitronixTransactionManager bitronixTransactionManager;

    @Bean()
    public TransactionManager bitronixTransactionManager() throws Throwable {
        if (bitronixTransactionManager == null) {
            bitronixTransactionManager = TransactionManagerServices.getTransactionManager();
            bitronixTransactionManager.setTransactionTimeout((int) Duration.ofMinutes(10).toSeconds());
        }

        return new JakartaTransactionManager(bitronixTransactionManager);
    }

//    @Bean
//    public UserTransaction bitronixUserTransaction() throws Throwable {
//        if (bitronixTransactionManager == null) {
//            BitronixTransactionManager bitronixTransactionManager = TransactionManagerServices.getTransactionManager();
//            bitronixTransactionManager.setTransactionTimeout((int) Duration.ofMinutes(10).toSeconds());
//        }
//
//        return new JakartaUserTransaction(bitronixTransactionManager);
//    }

    @Bean
    public UsersXmlCollection usersCollection() {
        return new UsersXmlCollection();
    }

    @Bean
    public PlatformTransactionManager transactionManager(TransactionManager transactionManager) {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(transactionManager);
        jtaTransactionManager.setAllowCustomIsolationLevels(true);

        return jtaTransactionManager;
    }

//    @Bean
//    public ActiveMQConnectionFactory messageConnectionFactory() {
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
//        activeMQConnectionFactory.setBrokerURL(brokerURL);
//
//        return activeMQConnectionFactory;
//    }

    @Bean
    public StompJmsConnectionFactory stompJmsConnectionFactory() {
        StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
        factory.setDisconnectTimeout(TimeUnit.SECONDS.toMillis(30));
        factory.setBrokerURI(brokerURI);
        factory.setUsername(username);
        factory.setPassword(password);

        return factory;
    }

//    @Bean
//    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
//        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
//        factory.setConnectionFactory(stompJmsConnectionFactory());
//        factory.setSessionTransacted(true);
//        factory.setConcurrency("5");
//
//        return factory;
//    }
}
