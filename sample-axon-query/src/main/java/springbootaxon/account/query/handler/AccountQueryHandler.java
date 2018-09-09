package springbootaxon.account.query.handler;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import springbootaxon.account.query.response.CountAccountSummariesResponse;
import springbootaxon.account.query.response.FindAccountSummariesResponse;
import springbootaxon.account.repo.AccountRepository;

@Component
public class AccountQueryHandler {
	
	@Autowired
	private AccountRepository accRepo;
	
	@QueryHandler
	public FindAccountSummariesResponse handle(FindAccountSummariesQuery query) {
		FindAccountSummariesResponse response = new FindAccountSummariesResponse();
		response.setAccounts(accRepo.findAll());
		return response;
	}
	
	@QueryHandler
	public CountAccountSummariesResponse handle(CountAccountSummariesQuery query) {
		CountAccountSummariesResponse response = new CountAccountSummariesResponse();
		response.setCount(accRepo.findAll().size());
		return response;
	}


}
