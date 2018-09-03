package springbootaxon.account.config;

import org.axonframework.boot.autoconfig.AxonAutoConfiguration;
import org.axonframework.kafka.eventhandling.DefaultKafkaMessageConverter;
import org.axonframework.kafka.eventhandling.KafkaMessageConverter;
import org.axonframework.serialization.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author saikatkar1
 *
 */
@Configuration
@AutoConfigureAfter(AxonAutoConfiguration.class)
public class AccountQueryConfig {

//	@Bean
//	public SpringAMQPMessageSource AccountsQueue(Serializer serializer) {
//		return new SpringAMQPMessageSource(new DefaultAMQPMessageConverter(serializer)) {
//
//			@RabbitListener(queues = "Accounts")
//			@Override
//			public void onMessage(Message message, Channel channel) {
//				super.onMessage(message, channel);
//			}
//		};
//	}
	
	 @ConditionalOnMissingBean
	 @Bean
	  public KafkaMessageConverter<String, byte[]> kafkaMessageConverter(
	      @Qualifier("eventSerializer") Serializer eventSerializer) {
	    return new DefaultKafkaMessageConverter(eventSerializer);
	  }
	

}
