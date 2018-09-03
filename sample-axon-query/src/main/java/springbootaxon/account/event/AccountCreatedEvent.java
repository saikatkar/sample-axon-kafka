package springbootaxon.account.event;

import java.math.BigDecimal;

/**
 * @author saikatkar1
 *
 */
public class AccountCreatedEvent extends BaseEvent<String> {

	private String accHolder;
	private BigDecimal balance;
	public AccountCreatedEvent(String id,String accHolder,BigDecimal balance) {
		super(id);
		this.setAccHolder(accHolder);
		this.setBalance(balance);
	
	}
	
	public AccountCreatedEvent() {
		
	}
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

}
