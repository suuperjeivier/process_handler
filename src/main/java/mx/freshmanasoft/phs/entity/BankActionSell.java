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
	@Column(precision=19, scale=4)
	private BigDecimal saldoInicial;
	@Column(precision=19, scale=4)
	private BigDecimal cancelacionValuacionPorVenta;
	@Column(precision=19, scale=4)
	private BigDecimal ventas;
	@Column(precision=19, scale=4)
	private BigDecimal tcInicial;
	@Column(precision=19, scale=4)
	private BigDecimal tcFinal;
	private Date fechaVenta;	
	@Column(precision=19, scale=4)
	private BigDecimal valuacionDlsPeriodoAnterior;
	@Column(precision=19, scale=4)
	private BigDecimal valuacionDlsAlFinalDelPeriodo;
	@Column(precision=19, scale=4)
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
	public BigDecimal getCancelacionValuacionPorVenta() {
		return cancelacionValuacionPorVenta;
	}
	public void setCancelacionValuacionPorVenta(BigDecimal cancelacionValuacionPorVenta) {
		this.cancelacionValuacionPorVenta = cancelacionValuacionPorVenta;
	}
	public BigDecimal getVentas() {
		return ventas;
	}
	public void setVentas(BigDecimal ventas) {
		this.ventas = ventas;
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

	public BigDecimal getValuacionDlsPeriodoAnterior() {
		return valuacionDlsPeriodoAnterior;
	}
	public void setValuacionDlsPeriodoAnterior(BigDecimal valuacionDlsPeriodoAnterior) {
		this.valuacionDlsPeriodoAnterior = valuacionDlsPeriodoAnterior;
	}
	public BigDecimal getValuacionDlsAlFinalDelPeriodo() {
		return valuacionDlsAlFinalDelPeriodo;
	}
	public void setValuacionDlsAlFinalDelPeriodo(BigDecimal valuacionDlsAlFinalDelPeriodo) {
		this.valuacionDlsAlFinalDelPeriodo = valuacionDlsAlFinalDelPeriodo;
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
