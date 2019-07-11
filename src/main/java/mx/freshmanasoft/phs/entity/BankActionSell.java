package mx.freshmanasoft.phs.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="bank_action_sell")
public class BankActionSell {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(precision=10, scale=4)
	private BigDecimal saldoInicial;
	@Column(precision=10, scale=4)
	private BigDecimal tcInicial;
	@Column(precision=10, scale=4)
	private BigDecimal tcFinal;
	private Date fechaVenta;	
	@Column(precision=10, scale=4)
	private BigDecimal valuacionDlsCompra;
	@Column(precision=10, scale=4)
	private BigDecimal valuacionDlsAfectadosAlFinalDelPeriodo;
	@Column(precision=10, scale=4)
	private BigDecimal utilidadPerdidaPorValuacion;
	@Column(name="N_IS_VENDIDA",
            columnDefinition="INT(2) NOT NULL DEFAULT 0")
	private int isVendida;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}
	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}
	public BigDecimal getTcInicial() {
		return tcInicial;
	}
	public void setTcInicial(BigDecimal tcInicial) {
		this.tcInicial = tcInicial;
	}
	public BigDecimal getTcFinal() {
		return tcFinal;
	}
	public void setTcFinal(BigDecimal tcFinal) {
		this.tcFinal = tcFinal;
	}
	public Date getFechaVenta() {
		return fechaVenta;
	}
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta = fechaVenta;
	}
	public BigDecimal getValuacionDlsCompra() {
		return valuacionDlsCompra;
	}
	public void setValuacionDlsCompra(BigDecimal valuacionDlsCompra) {
		this.valuacionDlsCompra = valuacionDlsCompra;
	}
	public BigDecimal getValuacionDlsAfectadosAlFinalDelPeriodo() {
		return valuacionDlsAfectadosAlFinalDelPeriodo;
	}
	public void setValuacionDlsAfectadosAlFinalDelPeriodo(BigDecimal valuacionDlsAfectadosAlFinalDelPeriodo) {
		this.valuacionDlsAfectadosAlFinalDelPeriodo = valuacionDlsAfectadosAlFinalDelPeriodo;
	}
	public BigDecimal getUtilidadPerdidaPorValuacion() {
		return utilidadPerdidaPorValuacion;
	}
	public void setUtilidadPerdidaPorValuacion(BigDecimal utilidadPerdidaPorValuacion) {
		this.utilidadPerdidaPorValuacion = utilidadPerdidaPorValuacion;
	}
	public int getIsVendida() {
		return isVendida;
	}
	public void setIsVendida(int isVendida) {
		this.isVendida = isVendida;
	}

}
