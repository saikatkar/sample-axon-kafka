package springbootaxon.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"springbootaxon.account.repo"})
public class AccountQueryOperationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountQueryOperationsApplication.class, args);
	}
}
