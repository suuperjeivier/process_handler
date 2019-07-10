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
@Table(name="bank_action_valuation")
public class BankActionValuation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(precision=15, scale=6)
	private BigDecimal marketPrice;
	@Column(precision=15, scale=4)
	private BigDecimal valorDeMercado;
	@Column(precision=15, scale=4)
	private BigDecimal plusvMinusvMensual;
	@Column(precision=19, scale=4)
	private BigDecimal plusvMinusvAcumulado;
	@Column(precision=15, scale=4)
	private BigDecimal intDevMensual;
	@Column(precision=15, scale=4)
	private BigDecimal intDevAcumulado;
	private Date fechaFinalDelPeriodo;
	@Column(precision=15, scale=4)
	private BigDecimal tcInicial;
	@Column(precision=15, scale=4)
	private BigDecimal tcFinal;
	@Column(precision=15, scale=4)
	private BigDecimal valuacionDolaresPeriodoAnterior;
	@Column(precision=15, scale=4)
	private BigDecimal valuacionDolaresAlFinal;
	@Column(precision=15, scale=4)
	private BigDecimal utilidadPerdidaPorValuacion;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public BigDecimal getMarketPrice() {
		return marketPrice;
	}
	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}
	public BigDecimal getValorDeMercado() {
		return valorDeMercado;
	}
	public void setValorDeMercado(BigDecimal valorDeMercado) {
		this.valorDeMercado = valorDeMercado;
	}
	public BigDecimal getPlusvMinusvMensual() {
		return plusvMinusvMensual;
	}
	public void setPlusvMinusvMensual(BigDecimal plusvMinusvMensual) {
		this.plusvMinusvMensual = plusvMinusvMensual;
	}
	public BigDecimal getPlusvMinusvAcumulado() {
		return plusvMinusvAcumulado;
	}
	public void setPlusvMinusvAcumulado(BigDecimal plusvMinusvAcumulado) {
		this.plusvMinusvAcumulado = plusvMinusvAcumulado;
	}
	public BigDecimal getIntDevMensual() {
		return intDevMensual;
	}
	public void setIntDevMensual(BigDecimal intDevMensual) {
		this.intDevMensual = intDevMensual;
	}
	public BigDecimal getIntDevAcumulado() {
		return intDevAcumulado;
	}
	public void setIntDevAcumulado(BigDecimal intDevAcumulado) {
		this.intDevAcumulado = intDevAcumulado;
	}
	public Date getFechaFinalDelPeriodo() {
		return fechaFinalDelPeriodo;
	}
	public void setFechaFinalDelPeriodo(Date fechaFinalDelPeriodo) {
		this.fechaFinalDelPeriodo = fechaFinalDelPeriodo;
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
	public BigDecimal getValuacionDolaresPeriodoAnterior() {
		return valuacionDolaresPeriodoAnterior;
	}
	public void setValuacionDolaresPeriodoAnterior(BigDecimal valuacionDolaresPeriodoAnterior) {
		this.valuacionDolaresPeriodoAnterior = valuacionDolaresPeriodoAnterior;
	}
	public BigDecimal getValuacionDolaresAlFinal() {
		return valuacionDolaresAlFinal;
	}
	public void setValuacionDolaresAlFinal(BigDecimal valuacionDolaresAlFinal) {
		this.valuacionDolaresAlFinal = valuacionDolaresAlFinal;
	}
	public BigDecimal getUtilidadPerdidaPorValuacion() {
		return utilidadPerdidaPorValuacion;
	}
	public void setUtilidadPerdidaPorValuacion(BigDecimal utilidadPerdidaPorValuacion) {
		this.utilidadPerdidaPorValuacion = utilidadPerdidaPorValuacion;
	}
	
}
