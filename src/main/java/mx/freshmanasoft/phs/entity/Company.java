package mx.freshmanasoft.phs.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Company {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private String name;
	private String friendlyAccount;
	private String rfc;
	private int status;

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
	 * @return the friendlyAccount
	 */
	public String getRfc() {
		return rfc;
	}

	/**
	 * @param friendlyAccount the friendlyAccount to set
	 */
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	/**
	 * @return the friendlyAccount
	 */
	public String getFriendlyAccount() {
		return friendlyAccount;
	}

	/**
	 * @param friendlyAccount the friendlyAccount to set
	 */
	public void setFriendlyAccount(String friendlyAccount) {
		this.friendlyAccount = friendlyAccount;
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
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", friendlyAccount=" + friendlyAccount + ", number=" + number
				+ ", status=" + status + "]";
	}
	
}
