package springbootaxon.account.command;

import java.math.BigDecimal;

public class WithdrawMoneyCommand extends BaseCommand<String> {
	private  final BigDecimal amount;

	public WithdrawMoneyCommand(String id, BigDecimal amount) {
		super(id);
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}
}