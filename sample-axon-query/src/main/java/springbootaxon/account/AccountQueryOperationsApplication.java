package springbootaxon.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.kafka.KafkaAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = KafkaAutoConfiguration.class)
@EnableMongoRepositories(basePackages = {"springbootaxon.account.repo"})
public class AccountQueryOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountQueryOperationsApplication.class, args);
	}
}
