package ru.itmo.se.bl.lab3.config;

import org.fusesource.stomp.jms.StompJmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableTransactionManagement
@EnableJms
public class AppConfig {
    @Value("${stomp.jms.factory.broker-uri}")
    private String brokerURI;

    @Value("${stomp.jms.factory.username}")
    private String username;

    @Value("${stomp.jms.factory.password}")
    private String password;

    @Bean
    public StompJmsConnectionFactory stompJmsConnectionFactory() {
        StompJmsConnectionFactory factory = new StompJmsConnectionFactory();
        factory.setDisconnectTimeout(TimeUnit.SECONDS.toMillis(30));
        factory.setBrokerURI(brokerURI);
        factory.setUsername(username);
        factory.setPassword(password);

        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(stompJmsConnectionFactory());
        factory.setSessionTransacted(true);
        factory.setConcurrency("5");

        return factory;
    }
}
