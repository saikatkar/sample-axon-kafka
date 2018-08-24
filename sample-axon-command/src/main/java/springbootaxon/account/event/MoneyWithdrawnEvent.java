package springbootaxon.account.event;

import java.math.BigDecimal;

public class MoneyWithdrawnEvent extends BaseEvent<String> {
	private final BigDecimal amount;

	public MoneyWithdrawnEvent(String id, BigDecimal amount) {
		super(id);
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}
}