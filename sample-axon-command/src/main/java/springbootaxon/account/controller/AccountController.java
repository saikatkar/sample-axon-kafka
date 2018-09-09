package springbootaxon.account.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.model.AggregateNotFoundException;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import springbootaxon.account.command.AccountCreateCommand;
import springbootaxon.account.command.DepositMoneyCommand;
import springbootaxon.account.command.WithdrawMoneyCommand;
import springbootaxon.account.exception.InsufficientBalanceException;
import springbootaxon.account.model.AccountOwner;
import springbootaxon.account.model.Amount;


@RestController
@RequestMapping("/accounts")
public class AccountController {
	

	private final CommandGateway commandGateway;

	private final EventStore eventStore;

	public AccountController(CommandGateway commandGateway, EventStore eventStore) {
		this.commandGateway = commandGateway;
		this.eventStore = eventStore;
	}

	@GetMapping("/events/{id}")
	public List<Object> getEvents(@PathVariable String id) {
		return eventStore.readEvents(id).asStream().map(s -> s.getPayload()).collect(Collectors.toList());
	}

//	@PostMapping
//	public CompletableFuture<String> createAccount(@RequestBody AccountOwner user) {
//		String id = UUID.randomUUID().toString();
//		return commandGateway.send(new AccountCreateCommand(id, user.getName()));
//	}

	@PutMapping(path = "/deposit/{accountId}")
	public CompletableFuture<String> deposit(@RequestBody Amount ammount, @PathVariable String accountId) {
		
			return commandGateway.send(new DepositMoneyCommand(accountId, ammount.getAmount()));
		
	}

	@PutMapping(path = "/withdraw/{accountId}")
	public CompletableFuture<String> withdraw(@RequestBody Amount ammount, @PathVariable String accountId) {
		
			return commandGateway.send(new WithdrawMoneyCommand(accountId, ammount.getAmount()));
		
	}

//	@DeleteMapping("{id}")
//	public CompletableFuture<String> delete(@PathVariable String id) {
//		return commandGateway.send(new CloseAccountCommand(id));
//	}

	@ExceptionHandler(AggregateNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void notFound() {
	}

	@ExceptionHandler(InsufficientBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String insufficientBalance(InsufficientBalanceException exception) {
	    return exception.getMessage();
    }

}

