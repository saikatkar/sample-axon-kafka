package springbootaxon.account.event;

import java.math.BigDecimal;

public class MoneyDepositedEvent extends BaseEvent<String> {
	private  BigDecimal amount;

	public MoneyDepositedEvent(String id, BigDecimal amount) {
		super(id);
		this.amount = amount;
	}

	
	public MoneyDepositedEvent() {
		
	}
	public BigDecimal getAmount() {
		return amount;
	}
}