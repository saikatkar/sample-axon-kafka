package springbootaxon.account.event;

import org.springframework.util.Assert;

public class BaseEvent<T> {

	private  T id;
	
	public BaseEvent(T id) {
		Assert.notNull(id, "Id must be not null");
		this.id = id;
	}
	public BaseEvent() {
		
	}
	public T getId() {
		return id;
	}
}
