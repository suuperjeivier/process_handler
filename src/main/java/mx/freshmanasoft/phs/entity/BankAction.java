package mx.freshmanasoft.phs.entity;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;

import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

public class BankAction {

	@Id
	private Long id;
	private String name;
	private String cusip;
	private String serie;
	private Double value;
	@ManyToOne(cascade = CascadeType.ALL)
	private BankAccount account;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(Double value) {
		this.value = value;
	}
	public BankAccount getAccount() {
		return account;
	}
	public void setAccount(BankAccount account) {
		this.account = account;
	}

}
