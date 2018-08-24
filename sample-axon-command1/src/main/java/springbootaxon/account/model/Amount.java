package springbootaxon.account.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Amount implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5684258230592753293L;
	
	public Amount() {
		
	}
	
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

}
