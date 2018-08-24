package springbootaxon.account.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import springbootaxon.account.model.Account;

@Repository
public interface AccountRepository extends MongoRepository<Account, String>{
	
	
	public Account findByAccountNo(String accNo);
	


}
