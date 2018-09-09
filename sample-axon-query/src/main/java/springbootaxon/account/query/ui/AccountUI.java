package springbootaxon.account.query.ui;

import java.math.BigDecimal;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.queryhandling.QueryGateway;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.annotation.SpringViewDisplay;
import com.vaadin.ui.Button;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import springbootaxon.account.command.AccountCreateCommand;
import springbootaxon.account.command.DepositMoneyCommand;
import springbootaxon.account.command.WithdrawMoneyCommand;
import springbootaxon.account.model.Account;

@SpringUI(path="/ui")
@Theme("valo")
@Title("AccountsPortal")
public class AccountUI extends UI{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 9207491870482212797L;
	
	private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    private AccountSummaryDataProvider accSummaryDataProvider;

    public AccountUI(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }
	@Override
	protected void init(VaadinRequest vaadinRequest) {
        HorizontalLayout commandBar = new HorizontalLayout();
        commandBar.setSizeFull();
        commandBar.addComponents(createPanel(),issuePanel(), redeemPanel());

        VerticalLayout layout = new VerticalLayout();
        layout.addComponents(commandBar, summaryGrid());
        layout.setHeight(95, Unit.PERCENTAGE);

        setContent(layout);

        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            /**
			 * 
			 */
			private static final long serialVersionUID = -7866251300351152162L;

			@Override
            public void error(com.vaadin.server.ErrorEvent event) {
                Throwable cause = event.getThrowable();
                while(cause.getCause() != null) cause = cause.getCause();
                Notification.show("Error", cause.getMessage(), Notification.Type.ERROR_MESSAGE);
            }
        });
    }
	
	private Panel createPanel() {
        TextField id = new TextField("Reseller Id");
        TextField name = new TextField("Reseller Name");
        Button submit = new Button("Submit");

        submit.addClickListener(evt -> {
            commandGateway.sendAndWait(new AccountCreateCommand(UUID.randomUUID().toString(),id.getValue(), name.getValue()));
            Notification.show("Success", Notification.Type.HUMANIZED_MESSAGE)
                    .addCloseListener(e -> accSummaryDataProvider.refreshAll());
        });

        FormLayout form = new FormLayout();
        form.addComponents(id, name, submit);
        form.setMargin(true);

        Panel panel = new Panel("Create Account");
        panel.setContent(form);
        return panel;
   }
	private Panel issuePanel() {
        TextField id = new TextField("Account No");
        TextField amount = new TextField("Amount");
        Button submit = new Button("Submit");

        submit.addClickListener(evt -> {
            commandGateway.sendAndWait(new DepositMoneyCommand(id.getValue(), new BigDecimal(amount.getValue())));
            Notification.show("Success", Notification.Type.HUMANIZED_MESSAGE)
                    .addCloseListener(e -> accSummaryDataProvider.refreshAll());
        });

        FormLayout form = new FormLayout();
        form.addComponents(id, amount, submit);
        form.setMargin(true);

        Panel panel = new Panel("Deposit Amount");
        panel.setContent(form);
        return panel;
   }
	
	private Panel redeemPanel() {
        TextField id = new TextField("Account No");
        TextField amount = new TextField("Amount");
        Button submit = new Button("Submit");

        submit.addClickListener(evt -> {
            commandGateway.sendAndWait(new WithdrawMoneyCommand(id.getValue(), new BigDecimal(amount.getValue())));
            Notification.show("Success", Notification.Type.HUMANIZED_MESSAGE)
                    .addCloseListener(e -> accSummaryDataProvider.refreshAll());
        });

        FormLayout form = new FormLayout();
        form.addComponents(id, amount, submit);
        form.setMargin(true);

        Panel panel = new Panel("Withdraw Amount");
        panel.setContent(form);
        return panel;
    }

    private Grid<Account> summaryGrid() {
        accSummaryDataProvider = new AccountSummaryDataProvider(queryGateway);
        Grid<Account> grid = new Grid<>();
        grid.addColumn(Account::getAccountNo).setCaption("Account No");
        grid.addColumn(Account::getAccHolder).setCaption("Reseller Id");
        grid.addColumn(Account::getAccHolderName).setCaption("Reseller Name");
        grid.addColumn(Account::getBalance).setCaption("Account Balance");
        grid.addColumn(Account::getLastUpdated).setCaption("Last Updated");
        grid.setSizeFull();
        grid.setDataProvider(accSummaryDataProvider);
        return grid;
    }

	

}
