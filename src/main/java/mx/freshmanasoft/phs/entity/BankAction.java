package mx.freshmanasoft.phs.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

@Entity
public class BankAction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String name;
	private String cusip;
	private String serie;
	private String security;
	private Double quantity;
	private Double unitCost;
	private Double valueAtCost;
	private Date acquisitionDate;
	private String accountingRecord;
	private String originalCoin;
	private Double value;
	private int status;
	@ManyToOne
	@JoinColumn(name="FK_ID_BANK_ACCOUNT")
	private BankAccount account;
	
	public BankAction() {
	}
	
	public BankAction(String name, String cusip) {
		this.name = name;
		this.cusip = cusip;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the security
	 */
	public String getSecurity() {
		return security;
	}
	/**
	 * @param security the security to set
	 */
	public void setSecurity(String security) {
		this.security = security;
	}
	/**
	 * @return the quantity
	 */
	public Double getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}
	/**
	 * @return the unitCost
	 */
	public Double getUnitCost() {
		return unitCost;
	}
	/**
	 * @param unitCost the unitCost to set
	 */
	public void setUnitCost(Double unitCost) {
		this.unitCost = unitCost;
	}
	/**
	 * @return the valueAtCost
	 */
	public Double getValueAtCost() {
		return valueAtCost;
	}
	/**
	 * @param valueAtCost the valueAtCost to set
	 */
	public void setValueAtCost(Double valueAtCost) {
		this.valueAtCost = valueAtCost;
	}
	/**
	 * @return the acquisitionDate
	 */
	public Date getAcquisitionDate() {
		return acquisitionDate;
	}
	/**
	 * @param acquisitionDate the acquisitionDate to set
	 */
	public void setAcquisitionDate(Date acquisitionDate) {
		this.acquisitionDate = acquisitionDate;
	}
	/**
	 * @return the accountingRecord
	 */
	public String getAccountingRecord() {
		return accountingRecord;
	}
	/**
	 * @param accountingRecord the accountingRecord to set
	 */
	public void setAccountingRecord(String accountingRecord) {
		this.accountingRecord = accountingRecord;
	}
	/**
	 * @return the originalCoin
	 */
	public String getOriginalCoin() {
		return originalCoin;
	}
	/**
	 * @param originalCoin the originalCoin to set
	 */
	public void setOriginalCoin(String originalCoin) {
		this.originalCoin = originalCoin;
	}
	/**
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
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
	/**
	 * @return the account
	 */
	public BankAccount getAccount() {
		return account;
	}
	/**
	 * @param account the account to set
	 */
	public void setAccount(BankAccount account) {
		this.account = account;
	}
	
	@Override
	public String toString() {
		return "BankAction [id=" + id + ", name=" + name + ", cusip=" + cusip + ", serie=" + serie + ", security="
				+ security + ", quantity=" + quantity + ", unitCost=" + unitCost + ", valueAtCost=" + valueAtCost
				+ ", acquisitionDate=" + acquisitionDate + ", accountingRecord=" + accountingRecord + ", originalCoin="
				+ originalCoin + ", value=" + value + ", status=" + status + ", account=" + account + "]";
	}
}
