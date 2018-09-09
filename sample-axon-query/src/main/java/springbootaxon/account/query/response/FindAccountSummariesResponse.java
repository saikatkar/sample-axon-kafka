package springbootaxon.account.query.response;

import java.util.List;

import springbootaxon.account.model.Account;

public class FindAccountSummariesResponse {

	private List<Account> accounts;

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
}
