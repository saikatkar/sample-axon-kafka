package springbootaxon.account.config;

import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.kafka.eventhandling.DefaultKafkaMessageConverter;
import org.axonframework.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;

/**
 * @author saikatkar1
 *
 */
@Configuration
public class AccountConfig {

	@Bean
    public EventStorageEngine eventStore(MongoClient client) {
        return new MongoEventStorageEngine(new DefaultMongoTemplate(client));
    }
//	@ConditionalOnMissingBean
//	@Bean
//	public KafkaMessageConverter<String, byte[]> kafkaMessageConverter(
//			@Qualifier("eventSerializer") Serializer eventSerializer) {
//		return new DefaultKafkaMessageConverter(eventSerializer);
//	}
//	@Bean
//    public Exchange exchange() {
//        return ExchangeBuilder.fanoutExchange("Accounts").build();
//    }
//
//    @Bean
//    public Queue queue() {
//        return QueueBuilder.durable("Accounts").build();
//    }
//
//    @Bean
//    public Binding binding() {
//        return BindingBuilder.bind(queue()).to(exchange()).with("*").noargs();
//    }
}
