package mx.freshmanasoft.phs.entity.bankaccount;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import mx.freshmanasoft.phs.entity.Bank;
import mx.freshmanasoft.phs.entity.Company;

@Entity
public class BankAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String accountType;
	private String accountNumber;
	private String address;
	private String telephone;
	private String client;
	private String portfolio;
	private String currency;
	private Date date;
	@ManyToOne
	@JoinColumn(name="FK_ID_COMPANY")
	private Company company;
	@ManyToOne
	@JoinColumn(name="FK_ID_BANK")
	private Bank bank;
	@Column(name="N_STATUS", columnDefinition = "INT(1) NOT NULL DEFAULT 0")
	private Integer status;
	public BankAccount() {
		
	}
	public BankAccount(Long id) {
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the accountType
	 */
	public String getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	/**
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	/**
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}
	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the client
	 */
	public String getClient() {
		return client;
	}
	/**
	 * @param client the client to set
	 */
	public void setClient(String client) {
		this.client = client;
	}
	/**
	 * @return the portfolio
	 */
	public String getPortfolio() {
		return portfolio;
	}
	/**
	 * @param portfolio the portfolio to set
	 */
	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}
	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}
	/**
	 * @param bank the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the company
	 */
	public Company getCompany() {
		return company;
	}
	/**
	 * @param company the company to set
	 */
	public void setCompany(Company company) {
		this.company = company;
	}
	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "BankAccount [id=" + id + ", accountType=" + accountType + ", accountNumber=" + accountNumber
				+ ", address=" + address + ", telephone=" + telephone + ", client=" + client + ", portfolio="
				+ portfolio + ", currency=" + currency + ", bank=" + bank + ", company="
				+ company + ", status=" + status + "]";
	}
	
}
