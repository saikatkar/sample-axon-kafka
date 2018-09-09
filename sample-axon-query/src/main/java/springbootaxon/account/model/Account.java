package springbootaxon.account.model;

import java.io.Serializable;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;

/**
 * @author saikatkar1
 *
 */
public class Account implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -683252464592318120L;

	@Id
	private String accountNo;
	
	private BigDecimal balance;
	
	private String accHolder;
	
	private String accHolderName;

	private String lastUpdated;
	
	public Account(String accountNo, BigDecimal balance, String accHolder, String accHolderName,String lastUpdated) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.accHolder = accHolder;
		this.lastUpdated = lastUpdated;
		this.accHolderName = accHolderName;
	}

	public String getAccHolder() {
		return accHolder;
	}

	public void setAccHolder(String accHolder) {
		this.accHolder = accHolder;
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

	public String getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(String lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public String getAccHolderName() {
		return accHolderName;
	}

	public void setAccHolderName(String accHolderName) {
		this.accHolderName = accHolderName;
	}

}
