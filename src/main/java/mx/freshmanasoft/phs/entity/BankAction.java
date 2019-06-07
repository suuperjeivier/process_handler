package mx.freshmanasoft.phs.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

import javax.persistence.Id;

import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

@Entity
public class BankAction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String cusip;
	private String serie;
	private int status;
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
	/**
	 * @return the cusip
	 */
	public String getCusip() {
		return cusip;
	}
	/**
	 * @param cusip the cusip to set
	 */
	public void setCusip(String cusip) {
		this.cusip = cusip;
	}
	/**
	 * @return the serie
	 */
	public String getSerie() {
		return serie;
	}
	/**
	 * @param serie the serie to set
	 */
	public void setSerie(String serie) {
		this.serie = serie;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BankAction [id=" + id + ", name=" + name + ", cusip=" + cusip + ", serie=" + serie + ", status="
				+ status + ", value=" + value + ", account=" + account + "]";
	}
	
}
