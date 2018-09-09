package springbootaxon.account.command;

import org.axonframework.commandhandling.TargetAggregateIdentifier;
import org.springframework.util.Assert;

public class BaseCommand<T> {

	@TargetAggregateIdentifier
	private  T id;

	public BaseCommand(T id) {
		Assert.notNull(id, "Id must be not null");
		this.id = id;
	}
	public BaseCommand() {}

	public T getId() {
		return id;
	}
}
