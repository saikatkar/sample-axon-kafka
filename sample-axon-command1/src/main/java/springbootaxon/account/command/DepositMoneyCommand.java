package springbootaxon.account.command;

import java.math.BigDecimal;

/**
 * @author saikatkar1
 *
 */
public class DepositMoneyCommand extends BaseCommand<String> {
	private final BigDecimal amount;

	public DepositMoneyCommand(String id, BigDecimal amount) {
		super(id);
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}
}