package springbootaxon.account.event;

import java.math.BigDecimal;

public class MoneyWithdrawnEvent extends BaseEvent<String> {
	private  BigDecimal amount;

	public MoneyWithdrawnEvent(String id, BigDecimal amount) {
		super(id);
		this.amount = amount;
	}
	public MoneyWithdrawnEvent() {
		
	}

	public BigDecimal getAmount() {
		return amount;
	}
}