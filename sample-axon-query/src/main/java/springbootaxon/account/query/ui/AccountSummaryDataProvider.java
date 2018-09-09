package springbootaxon.account.query.ui;


import org.axonframework.queryhandling.QueryGateway;

import com.vaadin.data.provider.CallbackDataProvider;

import springbootaxon.account.model.Account;
import springbootaxon.account.query.handler.CountAccountSummariesQuery;
import springbootaxon.account.query.handler.FindAccountSummariesQuery;
import springbootaxon.account.query.response.CountAccountSummariesResponse;
import springbootaxon.account.query.response.FindAccountSummariesResponse;

public class AccountSummaryDataProvider extends CallbackDataProvider<Account, Void>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1737230807250572798L;
	
	public AccountSummaryDataProvider(QueryGateway queryGateway) {
        super(
                q -> {
                    FindAccountSummariesQuery query = new FindAccountSummariesQuery();
                    FindAccountSummariesResponse response = queryGateway.query(
                            query, FindAccountSummariesResponse.class).join();
                    return response.getAccounts().stream();
                },
                q -> {
                    CountAccountSummariesQuery query = new CountAccountSummariesQuery();
                    CountAccountSummariesResponse response = queryGateway.query(
                            query, CountAccountSummariesResponse.class).join();
                    return response.getCount();
                }
        );
    }

}
