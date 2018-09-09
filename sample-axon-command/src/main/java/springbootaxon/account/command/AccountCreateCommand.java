package springbootaxon.account.command;

/**
 * @author saikatkar1
 *
 */
public class AccountCreateCommand extends BaseCommand<String>{

	private String accountHolder;
	private String accountHolderName;
	
	public AccountCreateCommand(String id,String holder,String accountHolderName) {
		super(id);
		this.accountHolder = holder;
		this.accountHolderName = accountHolderName;
	
		// TODO Auto-generated constructor stub
	}
	public AccountCreateCommand() {}

	public String getAccountHolder() {
		return this.accountHolder;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}


	
	

}
