package springbootaxon.account.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.commandhandling.model.AggregateIdentifier;
import org.axonframework.eventsourcing.EventSourcingHandler;

import static org.axonframework.commandhandling.model.AggregateLifecycle.apply;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.util.Assert;

import springbootaxon.account.command.AccountCreateCommand;
import springbootaxon.account.command.DepositMoneyCommand;
import springbootaxon.account.command.WithdrawMoneyCommand;
import springbootaxon.account.event.AccountCreatedEvent;
import springbootaxon.account.event.MoneyDepositedEvent;
import springbootaxon.account.event.MoneyWithdrawnEvent;
import springbootaxon.account.exception.InsufficientBalanceException;

@Aggregate
public class AccountAggregate {

	@AggregateIdentifier
	private String accountNo;
	
	private BigDecimal balance;
	
	private String accountHolder;

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
	public AccountAggregate() {
		// constructor needed for reconstructing the aggregate
	}
	
	@CommandHandler
	public AccountAggregate(AccountCreateCommand command) {
		
		Assert.hasLength(command.getAccountHolder(), "Account holder must have a name");
		Assert.hasLength(command.getId(), "Account id must have length greater than Zero");
		apply(new AccountCreatedEvent(command.getId(), command.getAccountHolder(), new BigDecimal("0")));
	}
	
	@EventSourcingHandler
	public void handle(AccountCreatedEvent event) {
		
		this.accountNo = event.getId();
		this.accountHolder = event.getAccHolder();
		this.balance = event.getBalance();
	}
	
	@CommandHandler
	public void on(DepositMoneyCommand command) {
		Assert.isTrue(command.getAmount().compareTo(BigDecimal.ZERO) > 0 , "Amount should be a positive number");
		apply(new MoneyDepositedEvent(command.getId(), command.getAmount()));
	}
	
	@EventSourcingHandler
	public void handle(MoneyDepositedEvent event) {
		this.balance = this.balance.add(event.getAmount());
	}
	
	@CommandHandler
	public void on(WithdrawMoneyCommand command) {
		Assert.isTrue(command.getAmount().compareTo(BigDecimal.ZERO) > 0 , "Amount should be a positive number");
		if(command.getAmount().compareTo(this.balance) > 0 ) {
			throw new InsufficientBalanceException("Insufficient balance. Trying to withdraw:" + command.getAmount() + ", but current balance is: " + this.balance);
		}
		apply(new MoneyWithdrawnEvent(command.getId(), command.getAmount()));
	}
	
	@EventSourcingHandler
	public void handle(MoneyWithdrawnEvent event) {
		this.balance = this.balance.subtract((event.getAmount()));
	}
}
