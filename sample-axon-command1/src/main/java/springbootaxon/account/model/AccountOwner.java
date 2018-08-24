package springbootaxon.account.model;

import java.io.Serializable;

public class AccountOwner implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5357787298063565273L;

	public AccountOwner() {
		
	}
	
	private String name;


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}
