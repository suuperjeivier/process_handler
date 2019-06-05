package mx.freshmanasoft.phs.entity.bankaccount;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BankAccount {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String banco;

	private String tipoCuenta;
	private String sucursal;

	private Integer noCliente;
	private String clabe;
	private String cuenta;

	private String noTargeta;
	private String swif;
	private String moneda;
	private String contacto;

	private String telefonoContacto;

	private Boolean cardexActivo;
	private Boolean predeterminado;
	
	private Integer status;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the banco
	 */
	public String getBanco() {
		return banco;
	}

	/**
	 * @param banco the banco to set
	 */
	public void setBanco(String banco) {
		this.banco = banco;
	}

	/**
	 * @return the tipoCuenta
	 */
	public String getTipoCuenta() {
		return tipoCuenta;
	}

	/**
	 * @param tipoCuenta the tipoCuenta to set
	 */
	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	/**
	 * @return the sucursal
	 */
	public String getSucursal() {
		return sucursal;
	}

	/**
	 * @param socursal the sucursal to set
	 */
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	/**
	 * @return the noCliente
	 */
	public Integer getNoCliente() {
		return noCliente;
	}

	/**
	 * @param noCliente the noCliente to set
	 */
	public void setNoCliente(Integer noCliente) {
		this.noCliente = noCliente;
	}

	/**
	 * @return the clabe
	 */
	public String getClabe() {
		return clabe;
	}

	/**
	 * @param clabe the clabe to set
	 */
	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	/**
	 * @return the cuenta
	 */
	public String getCuenta() {
		return cuenta;
	}

	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	/**
	 * @return the noTargeta
	 */
	public String getNoTargeta() {
		return noTargeta;
	}

	/**
	 * @param noTargeta the noTargeta to set
	 */
	public void setNoTargeta(String noTargeta) {
		this.noTargeta = noTargeta;
	}

	/**
	 * @return the swif
	 */
	public String getSwif() {
		return swif;
	}

	/**
	 * @param swif the swif to set
	 */
	public void setSwif(String swif) {
		this.swif = swif;
	}

	/**
	 * @return the moneda
	 */
	public String getMoneda() {
		return moneda;
	}

	/**
	 * @param moneda the moneda to set
	 */
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	/**
	 * @return the contacto
	 */
	public String getContacto() {
		return contacto;
	}

	/**
	 * @param contacto the contacto to set
	 */
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	/**
	 * @return the telefonoContacto
	 */
	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	/**
	 * @param telefonoContacto the telefonoContacto to set
	 */
	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	/**
	 * @return the cardexActivo
	 */
	public Boolean getCardexActivo() {
		return cardexActivo;
	}

	/**
	 * @param cardexActivo the cardexActivo to set
	 */
	public void setCardexActivo(Boolean cardexActivo) {
		this.cardexActivo = cardexActivo;
	}

	/**
	 * @return the prederterminado
	 */
	public Boolean getPredeterminado() {
		return predeterminado;
	}

	/**
	 * @param prederterminado the prederterminado to set
	 */
	public void setPredeterminado(Boolean predeterminado) {
		this.predeterminado = predeterminado;
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
		return "BankAccount [id=" + id + ", status=" + status + "]";
	}
}
