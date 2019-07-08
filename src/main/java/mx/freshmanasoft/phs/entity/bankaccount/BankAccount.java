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
import mx.freshmanasoft.phs.entity.BankAccountType;
import mx.freshmanasoft.phs.entity.Company;

@Entity
public class BankAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@ManyToOne
	@JoinColumn(name="FK_ID_ACCOUNT_TYPE")
	private BankAccountType accountType;
	private String accountNumber;
	private String address;
	private String telephone;
	private String client;
	private String portfolio;
	private String currency;
	private Date date;
	@Column(name="N_IS_INVERSION_EN_DOLARES")
	private Boolean isInversionEnDolares;	
	private String inversionEnDolaresAccountNumber;
	@Column(name="N_IS_INVERSION_EN_PESOS")
	private Boolean isInversionEnPesos;
	private String inversionEnPesosAccountNumber;
	@Column(name="N_IS_EFECTIVO_EN_DOLARES")
	private Boolean isEfectivoEnDolares;
	private String efectivoEnDolaresAccountNumber;
	@Column(name="N_IS_EFECTIVO_EN_PESOS")
	private Boolean isEfectivoEnPesos;
	private String efectivoEnPesosAccountNumber;		
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
	public BankAccountType getAccountType() {
		return accountType;
	}
	/**
	 * @param accountType the accountType to set
	 */
	public void setAccountType(BankAccountType accountType) {
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
	
	public Boolean getIsInversionEnDolares() {
		return isInversionEnDolares;
	}
	public void setIsInversionEnDolares(Boolean isInversionEnDolares) {
		this.isInversionEnDolares = isInversionEnDolares;
	}
	public Boolean getIsInversionEnPesos() {
		return isInversionEnPesos;
	}
	public void setIsInversionEnPesos(Boolean isInversionEnPesos) {
		this.isInversionEnPesos = isInversionEnPesos;
	}
	public Boolean getIsEfectivoEnDolares() {
		return isEfectivoEnDolares;
	}
	public void setIsEfectivoEnDolares(Boolean isEfectivoEnDolares) {
		this.isEfectivoEnDolares = isEfectivoEnDolares;
	}
	public Boolean getIsEfectivoEnPesos() {
		return isEfectivoEnPesos;
	}
	public void setIsEfectivoEnPesos(Boolean isEfectivoEnPesos) {
		this.isEfectivoEnPesos = isEfectivoEnPesos;
	}
	public String getInversionEnDolaresAccountNumber() {
		return inversionEnDolaresAccountNumber;
	}
	public void setInversionEnDolaresAccountNumber(String inversionEnDolaresAccountNumber) {
		this.inversionEnDolaresAccountNumber = inversionEnDolaresAccountNumber;
	}
	public String getInversionEnPesosAccountNumber() {
		return inversionEnPesosAccountNumber;
	}
	public void setInversionEnPesosAccountNumber(String inversionEnPesosAccountNumber) {
		this.inversionEnPesosAccountNumber = inversionEnPesosAccountNumber;
	}
	public String getEfectivoEnPesosAccountNumber() {
		return efectivoEnPesosAccountNumber;
	}
	public void setEfectivoEnPesosAccountNumber(String efectivoEnPesosAccountNumber) {
		this.efectivoEnPesosAccountNumber = efectivoEnPesosAccountNumber;
	}
	public String getEfectivoEnDolaresAccountNumber() {
		return efectivoEnDolaresAccountNumber;
	}
	public void setEfectivoEnDolaresAccountNumber(String efectivoEnDolaresAccountNumber) {
		this.efectivoEnDolaresAccountNumber = efectivoEnDolaresAccountNumber;
	}
	@Override
	public String toString() {
		return  "Account type=" + accountType + ", Número de cuenta=" + accountNumber
				+ ", Dirección=" + address + ", Teléfono=" + telephone + ", Cliente=" + client + ", Portfolio="
				+ portfolio + ", currency=" + currency + ", Fecha=" + date + ", Is inversion en dolares="
				+ isInversionEnDolares + ", Inversión en dolares account number=" + inversionEnDolaresAccountNumber
				+ ", Is inversion en pesos=" + isInversionEnPesos + ", Inversión en pesos account number="
				+ inversionEnPesosAccountNumber + ", Is efectivo en dolares=" + isEfectivoEnDolares
				+ ", Efectivo en dolares account number=" + efectivoEnDolaresAccountNumber + ", Is efectivo en pesos="
				+ isEfectivoEnPesos + ", Efectivo en pesos account number=" + efectivoEnPesosAccountNumber + ", Empresa="
				+ company + ", Banco=" + bank + ", status=" + status;
	}
}
