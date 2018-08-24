package springbootaxon.account.command;

/**
 * @author saikatkar1
 *
 */
public class AccountCreateCommand extends BaseCommand<String>{

	private String accountHolder;
	
	public AccountCreateCommand(String id,String holder) {
		super(id);
		this.accountHolder = holder;
	
		// TODO Auto-generated constructor stub
	}

	public String getAccountHolder() {
		return this.accountHolder;
	}


	
	

}
