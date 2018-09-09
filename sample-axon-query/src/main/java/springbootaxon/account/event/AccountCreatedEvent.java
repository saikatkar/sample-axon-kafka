package springbootaxon.account.event;

import java.math.BigDecimal;

/**
 * @author saikatkar1
 *
 */
public class AccountCreatedEvent extends BaseEvent<String> {

	private String accHolder;
	private String accHolderName;
	private BigDecimal balance;
	public AccountCreatedEvent(String id,String accHolder,String accHolderName,BigDecimal balance) {
		super(id);
		this.setAccHolder(accHolder);
		this.setBalance(balance);
		this.setAccHolderName(accHolderName);
	
	}
	
	public AccountCreatedEvent() {}
	public String getAccHolder() {
		return accHolder;
	}
	public void setAccHolder(String accHolder) {
		this.accHolder = accHolder;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public String getAccHolderName() {
		return accHolderName;
	}
	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

}
