package springbootaxon.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
@EnableEurekaClient
public class AccountOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountOperationsApplication.class, args);
	}
}
