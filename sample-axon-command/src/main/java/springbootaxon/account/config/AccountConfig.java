package springbootaxon.account.config;

import org.axonframework.boot.autoconfig.AxonAutoConfiguration;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.AggregateFactory;
import org.axonframework.eventsourcing.AggregateSnapshotter;
import org.axonframework.eventsourcing.EventCountSnapshotTriggerDefinition;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.eventsourcing.SnapshotTrigger;
import org.axonframework.eventsourcing.SnapshotTriggerDefinition;
import org.axonframework.eventsourcing.Snapshotter;
import org.axonframework.eventsourcing.eventstore.EventStorageEngine;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.mongo.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mongodb.MongoClient;

import springbootaxon.account.aggregate.AccountAggregate;

/**
 * @author saikatkar1
 *
 */
@Configuration
@AutoConfigureAfter(AxonAutoConfiguration.class)
public class AccountConfig {
	
	@Autowired
	private EventStore myEventStore;

	@Bean
    public EventStorageEngine eventStore(MongoClient client) {
        return new MongoEventStorageEngine(new DefaultMongoTemplate(client));
    }
	@Bean
	public AggregateFactory<AccountAggregate> aggregateFactory(){
		return new GenericAggregateFactory<AccountAggregate>(AccountAggregate.class);
	}
	
	@Bean
	public Snapshotter snapShotter(AggregateFactory<AccountAggregate> aggregateFactory){
		return new AggregateSnapshotter(myEventStore, aggregateFactory);
	}
	
	@Bean
	public SnapshotTriggerDefinition snapshotTriggerDefinition(Snapshotter snapshotter) {
		return new EventCountSnapshotTriggerDefinition(snapshotter, 5);
	}
	@Bean
	public Repository<AccountAggregate> accountAggregateRepository(SnapshotTriggerDefinition snapshotTriggerDefinition,AggregateFactory<AccountAggregate> aggregateFactory){
		return new EventSourcingRepository<AccountAggregate>(aggregateFactory, myEventStore,snapshotTriggerDefinition);
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
