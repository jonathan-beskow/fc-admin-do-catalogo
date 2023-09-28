package com.fullcycle.admin.catalogo.configuration;

import com.fullcycle.admin.catalogo.configuration.amqp.QueueProperties;
import com.fullcycle.admin.catalogo.configuration.annotations.VideoCreatedQueue;
import com.fullcycle.admin.catalogo.services.EventService;
import com.fullcycle.admin.catalogo.services.impl.RabbitEventService;
import com.fullcycle.admin.catalogo.services.local.InMemoryStorageService;
import org.springframework.amqp.rabbit.core.RabbitOperations;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class EventConfig {

//    @Bean
//    @VideoCreatedQueue
//    @Profile({"development"})
//    EventService localVideoCreatedEventService() {
//        return new InMemoryStorageService();
//    }

    @Bean
    @VideoCreatedQueue
    @ConditionalOnMissingBean
    EventService videoCreatedEventService(
            @VideoCreatedQueue final QueueProperties props,
            final RabbitOperations ops
    ) {
        return new RabbitEventService(props.getExchange(), props.getRoutingKey(), ops);
    }
}