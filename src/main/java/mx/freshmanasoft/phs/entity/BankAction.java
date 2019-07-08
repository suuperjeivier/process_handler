package mx.freshmanasoft.phs.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

@Entity
@Table(name="bank_action")
public class BankAction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String institucion;
	private String instrumento;	
	private String name;
	private String cusip;	
	private String isinSerie;
	private String secId;
	private Long titulos;
	@Column(precision=10, scale=4)
	private BigDecimal unitCost;	
	@Column(precision=10, scale=4)
	private BigDecimal marketPrice;
	@Column(precision=10, scale=4)
	private BigDecimal valorDeMercado;
	@Column(precision=10, scale=4)
	private BigDecimal plusvMinusvAcumulado;
	@Column(precision=10, scale=4)
	private BigDecimal intDevengado;
	@Column(precision=10, scale=4)
	private BigDecimal tC;
	@Column(precision=10, scale=4)
	private BigDecimal mxnDls;
	private String registroContable;
	private String valor;
	private String monedaOriginal;	
	private String fechaInicio;
	private Date fechaInicioReal;
	private Date fechaDeAdquisicion;	
	private String fechaFinal;
	private Date fechaFinalReal;
	@Column(precision=10, scale=4)
	private BigDecimal saldoInicial;
	@Column(precision=10, scale=4)
	private BigDecimal depositos;
	@Column(precision=10, scale=4)
	private BigDecimal dividendos;
	@Column(precision=10, scale=4)
	private BigDecimal interesesDevengado;
	@Column(precision=10, scale=4)
	private BigDecimal interesesCobrado;
	@Column(precision=10, scale=4)
	private BigDecimal cambioMxnVsDis;
	@Column(precision=10, scale=4)
	private BigDecimal valuacionAlCierre;
	@Column(precision=10, scale=4)
	private BigDecimal cancelacionDeValuacionXVta;
	@Column(precision=10, scale=4)
	private BigDecimal cancelacionDeInteresDevengado;
	@Column(precision=10, scale=4)
	private BigDecimal valorActualRegistradoManualmente;
	@Column(precision=10, scale=4)
	private BigDecimal compras;
	@Column(precision=10, scale=4)
	private BigDecimal ventas;
	@Column(precision=10, scale=4)
	private BigDecimal utilidadPerdida;
	@Column(precision=10, scale=4)
	private BigDecimal retiros;
	@Column(precision=10, scale=4)
	private BigDecimal gastos;
	@Column(precision=10, scale=4)
	private BigDecimal impuestos;
	@Column(precision=10, scale=4)
	private BigDecimal netoMov;
	@Column(precision=10, scale=4)
	private BigDecimal saldoFinal;
	@Column(precision=10, scale=4)
	private BigDecimal dlsAlInicio;
	@Column(precision=10, scale=4)
	private BigDecimal tcInicial;
	@Column(precision=10, scale=4)
	private BigDecimal tcFinal;
	private Long valuacionDlsAlInicio;
	private Long valuacionDlsAlFinal;
	private Long utilidadPerdidaPorValuacion;
	private Long utilidadPerdidaPorValuacionMiles;
	private Long accountingRecord;//lo uso para almacenar de manera temporal el id de la cuenta
	@Column(name="N_SUB_ACCOUNT_TYPE", columnDefinition = "INT(1) NOT NULL DEFAULT 0")
	private int subAccountType;
	private int status;
	@Column(name="N_IS_VENDIDA",
            columnDefinition="INT(2) NOT NULL DEFAULT 0")
	private int isVendida;
	@ManyToOne
	@JoinColumn(name="FK_ID_BANK_ACCOUNT")
	private BankAccount account;
	
	public BankAction() {
	}

	public BankAction(String institucion, String instrumento, String name, String cusip, String isinSerie,
			String secId, Long titulos, BigDecimal unitCost, BigDecimal marketPrice,
			BigDecimal valorDeMercado, BigDecimal plusvMinusvAcumulado, BigDecimal intDevengado, BigDecimal tC,
			BigDecimal mxnDls, String registroContable, String valor, String monedaOriginal, String fechaInicio,
			String fechaFinal, BigDecimal saldoInicial, BigDecimal depositos, BigDecimal dividendos,
			BigDecimal interesesDevengado, BigDecimal interesesCobrado, BigDecimal cambioMxnVsDis,
			BigDecimal valuacionAlCierre, BigDecimal cancelacionDeValuacionXVta,
			BigDecimal cancelacionDeInteresDevengado, BigDecimal compras, BigDecimal ventas, BigDecimal utilidadPerdida,
			BigDecimal retiros, BigDecimal gastos, BigDecimal impuestos, BigDecimal netoMov, BigDecimal saldoFinal,
			BigDecimal dlsAlInicio, BigDecimal tcInicial, BigDecimal tcFinal, Long valuacionDlsAlInicio,
			Long valuacionDlsAlFinal, Long utilidadPerdidaPorValuacion, Long utilidadPerdidaPorValuacionMiles,
			Long accountingRecord,
			int status, BankAccount account) {
		super();
		
		this.institucion = institucion;
		this.instrumento = instrumento;
		this.name = name;
		this.cusip = cusip;
		this.isinSerie = isinSerie;
		this.secId = secId;
		this.titulos = titulos;
		this.unitCost = unitCost;		
		this.marketPrice = marketPrice;
		this.valorDeMercado = valorDeMercado;
		this.plusvMinusvAcumulado = plusvMinusvAcumulado;
		this.intDevengado = intDevengado;
		this.tC = tC;
		this.mxnDls = mxnDls;
		this.registroContable = registroContable;
		this.valor = valor;
		this.monedaOriginal = monedaOriginal;
		this.fechaInicio = fechaInicio;
		this.fechaFinal = fechaFinal;
		this.saldoInicial = saldoInicial;
		this.depositos = depositos;
		this.dividendos = dividendos;
		this.interesesDevengado = interesesDevengado;
		this.interesesCobrado = interesesCobrado;
		this.cambioMxnVsDis = cambioMxnVsDis;
		this.valuacionAlCierre = valuacionAlCierre;
		this.cancelacionDeValuacionXVta = cancelacionDeValuacionXVta;
		this.cancelacionDeInteresDevengado = cancelacionDeInteresDevengado;
		this.compras = compras;
		this.ventas = ventas;
		this.utilidadPerdida = utilidadPerdida;
		this.retiros = retiros;
		this.gastos = gastos;
		this.impuestos = impuestos;
		this.netoMov = netoMov;
		this.saldoFinal = saldoFinal;
		this.dlsAlInicio = dlsAlInicio;
		this.tcInicial = tcInicial;
		this.tcFinal = tcFinal;
		this.valuacionDlsAlInicio = valuacionDlsAlInicio;
		this.valuacionDlsAlFinal = valuacionDlsAlFinal;
		this.utilidadPerdidaPorValuacion = utilidadPerdidaPorValuacion;
		this.utilidadPerdidaPorValuacionMiles = utilidadPerdidaPorValuacionMiles;	
	
		this.accountingRecord = accountingRecord;
		this.status = status;
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstitucion() {
		return institucion;
	}

	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}

	public String getInstrumento() {
		return instrumento;
	}

	public void setInstrumento(String instrumento) {
		this.instrumento = instrumento;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCusip() {
		return cusip;
	}

	public void setCusip(String cusip) {
		this.cusip = cusip;
	}

	public String getIsinSerie() {
		return isinSerie;
	}

	public void setIsinSerie(String isinSerie) {
		this.isinSerie = isinSerie;
	}

	public String getSecId() {
		return secId;
	}

	public void setSecId(String secId) {
		this.secId = secId;
	}

	public Long getTitulos() {
		return titulos;
	}

	public void setTitulos(Long titulos) {
		this.titulos = titulos;
	}

	public BigDecimal getUnitCost() {
		return unitCost;
	}

	public void setUnitCost(BigDecimal unitCost) {
		this.unitCost = unitCost;
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

	public BigDecimal getPlusvMinusvAcumulado() {
		return plusvMinusvAcumulado;
	}

	public void setPlusvMinusvAcumulado(BigDecimal plusvMinusvAcumulado) {
		this.plusvMinusvAcumulado = plusvMinusvAcumulado;
	}

	public BigDecimal getIntDevengado() {
		return intDevengado;
	}

	public void setIntDevengado(BigDecimal intDevengado) {
		this.intDevengado = intDevengado;
	}

	public BigDecimal gettC() {
		return tC;
	}

	public void settC(BigDecimal tC) {
		this.tC = tC;
	}

	public BigDecimal getMxnDls() {
		return mxnDls;
	}

	public void setMxnDls(BigDecimal mxnDls) {
		this.mxnDls = mxnDls;
	}

	public String getRegistroContable() {
		return registroContable;
	}

	public void setRegistroContable(String registroContable) {
		this.registroContable = registroContable;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getMonedaOriginal() {
		return monedaOriginal;
	}

	public void setMonedaOriginal(String monedaOriginal) {
		this.monedaOriginal = monedaOriginal;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public BigDecimal getDepositos() {
		return depositos;
	}

	public void setDepositos(BigDecimal depositos) {
		this.depositos = depositos;
	}

	public BigDecimal getDividendos() {
		return dividendos;
	}

	public void setDividendos(BigDecimal dividendos) {
		this.dividendos = dividendos;
	}

	public BigDecimal getInteresesDevengado() {
		return interesesDevengado;
	}

	public void setInteresesDevengado(BigDecimal interesesDevengado) {
		this.interesesDevengado = interesesDevengado;
	}

	public BigDecimal getInteresesCobrado() {
		return interesesCobrado;
	}

	public void setInteresesCobrado(BigDecimal interesesCobrado) {
		this.interesesCobrado = interesesCobrado;
	}

	public BigDecimal getCambioMxnVsDis() {
		return cambioMxnVsDis;
	}

	public void setCambioMxnVsDis(BigDecimal cambioMxnVsDis) {
		this.cambioMxnVsDis = cambioMxnVsDis;
	}

	public BigDecimal getValuacionAlCierre() {
		return valuacionAlCierre;
	}

	public void setValuacionAlCierre(BigDecimal valuacionAlCierre) {
		this.valuacionAlCierre = valuacionAlCierre;
	}

	public BigDecimal getCancelacionDeValuacionXVta() {
		return cancelacionDeValuacionXVta;
	}

	public void setCancelacionDeValuacionXVta(BigDecimal cancelacionDeValuacionXVta) {
		this.cancelacionDeValuacionXVta = cancelacionDeValuacionXVta;
	}

	public BigDecimal getCancelacionDeInteresDevengado() {
		return cancelacionDeInteresDevengado;
	}

	public void setCancelacionDeInteresDevengado(BigDecimal cancelacionDeInteresDevengado) {
		this.cancelacionDeInteresDevengado = cancelacionDeInteresDevengado;
	}

	public BigDecimal getCompras() {
		return compras;
	}

	public void setCompras(BigDecimal compras) {
		this.compras = compras;
	}

	public BigDecimal getVentas() {
		return ventas;
	}

	public void setVentas(BigDecimal ventas) {
		this.ventas = ventas;
	}

	public BigDecimal getUtilidadPerdida() {
		return utilidadPerdida;
	}

	public void setUtilidadPerdida(BigDecimal utilidadPerdida) {
		this.utilidadPerdida = utilidadPerdida;
	}

	public BigDecimal getRetiros() {
		return retiros;
	}

	public void setRetiros(BigDecimal retiros) {
		this.retiros = retiros;
	}

	public BigDecimal getGastos() {
		return gastos;
	}

	public void setGastos(BigDecimal gastos) {
		this.gastos = gastos;
	}

	public BigDecimal getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(BigDecimal impuestos) {
		this.impuestos = impuestos;
	}

	public BigDecimal getNetoMov() {
		return netoMov;
	}

	public void setNetoMov(BigDecimal netoMov) {
		this.netoMov = netoMov;
	}

	public BigDecimal getSaldoFinal() {
		return saldoFinal;
	}

	public void setSaldoFinal(BigDecimal saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public BigDecimal getDlsAlInicio() {
		return dlsAlInicio;
	}

	public void setDlsAlInicio(BigDecimal dlsAlInicio) {
		this.dlsAlInicio = dlsAlInicio;
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

	public Long getValuacionDlsAlInicio() {
		return valuacionDlsAlInicio;
	}

	public void setValuacionDlsAlInicio(Long valuacionDlsAlInicio) {
		this.valuacionDlsAlInicio = valuacionDlsAlInicio;
	}

	public Long getValuacionDlsAlFinal() {
		return valuacionDlsAlFinal;
	}

	public void setValuacionDlsAlFinal(Long valuacionDlsAlFinal) {
		this.valuacionDlsAlFinal = valuacionDlsAlFinal;
	}

	public Long getUtilidadPerdidaPorValuacion() {
		return utilidadPerdidaPorValuacion;
	}

	public void setUtilidadPerdidaPorValuacion(Long utilidadPerdidaPorValuacion) {
		this.utilidadPerdidaPorValuacion = utilidadPerdidaPorValuacion;
	}

	public Long getUtilidadPerdidaPorValuacionMiles() {
		return utilidadPerdidaPorValuacionMiles;
	}

	public void setUtilidadPerdidaPorValuacionMiles(Long utilidadPerdidaPorValuacionMiles) {
		this.utilidadPerdidaPorValuacionMiles = utilidadPerdidaPorValuacionMiles;
	}

	public BigDecimal getValorActualRegistradoManualmente() {
		return valorActualRegistradoManualmente;
	}

	public void setValorActualRegistradoManualmente(BigDecimal valorActualRegistradoManualmente) {
		this.valorActualRegistradoManualmente = valorActualRegistradoManualmente;
	}

	public Long getAccountingRecord() {
		return accountingRecord;
	}

	public void setAccountingRecord(Long accountingRecord) {
		this.accountingRecord = accountingRecord;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}
	public Date getFechaInicioReal() {
		return fechaInicioReal;
	}

	public void setFechaInicioReal(Date fechaInicioReal) {
		this.fechaInicioReal = fechaInicioReal;
	}

	public Date getFechaFinalReal() {
		return fechaFinalReal;
	}

	public void setFechaFinalReal(Date fechaFinalReal) {
		this.fechaFinalReal = fechaFinalReal;
	}

	public int getIsVendida() {
		return isVendida;
	}

	public void setIsVendida(int isVendida) {
		this.isVendida = isVendida;
	}

	public Date getFechaDeAdquisicion() {
		return fechaDeAdquisicion;
	}

	public void setFechaDeAdquisicion(Date fechaDeAdquisicion) {
		this.fechaDeAdquisicion = fechaDeAdquisicion;
	}

	public int getSubAccountType() {
		return subAccountType;
	}

	public void setSubAccountType(int subAccountType) {
		this.subAccountType = subAccountType;
	}

	@Override
	public String toString() {
		return "Institución=" + institucion + ", Instrumento=" + instrumento + ", Nombre="
				+ name + ", Cusip=" + cusip + ", Isin serie=" + isinSerie + ", Sec id=" + secId + ", Titulos=" + titulos
				+ ", Unit cost=" + unitCost + ", Market price=" + marketPrice + ", Valor de mercado=" + valorDeMercado
				+ ", Plusv minusv acumulado=" + plusvMinusvAcumulado + ", IntDevengado=" + intDevengado + ", TC=" + tC
				+ ", Mxn dls=" + mxnDls + ", Registro contable=" + registroContable + ", Valor=" + valor
				+ ", Moneda original=" + monedaOriginal + ", Fecha inicio=" + fechaInicio + ", Fecha inicio real="
				+ fechaInicioReal + ", Fecha de adquisición=" + fechaDeAdquisicion + ", Fecha Final=" + fechaFinal
				+ ", Fecha final real=" + fechaFinalReal + ", Saldo inicial=" + saldoInicial + ", Depositos=" + depositos
				+ ", Dividendos=" + dividendos + ", Intereses devengado=" + interesesDevengado + ", Intereses cobrado="
				+ interesesCobrado + ", Cambio Mxn Vs Dis=" + cambioMxnVsDis + ", Valuación al cierre=" + valuacionAlCierre
				+ ", Cancelación deValuación XVta=" + cancelacionDeValuacionXVta + ", Cancelacion de interes devengado="
				+ cancelacionDeInteresDevengado + ", Valor actual registrado manualmente="
				+ valorActualRegistradoManualmente + ", Compras=" + compras + ", ventas=" + ventas
				+ ", Utilidad perdida=" + utilidadPerdida + ", Retiros=" + retiros + ", Gastos=" + gastos
				+ ", Impuestos=" + impuestos + ", Neto mov=" + netoMov + ", Saldo final=" + saldoFinal + ", Dls al inicio="
				+ dlsAlInicio + ", Tc Inicial=" + tcInicial + ", Tc final=" + tcFinal + ", Valuación Dls al inicio="
				+ valuacionDlsAlInicio + ", Valuación Dls al final=" + valuacionDlsAlFinal
				+ ", Utilidad perdida por valuación=" + utilidadPerdidaPorValuacion + ", Utilidad perdida por valuación miles="
				+ utilidadPerdidaPorValuacionMiles + ", Accounting record=" + accountingRecord + ", Sub account type="
				+ subAccountType + ", Status=" + status + ", Is vendida=" + isVendida + ", Account=" + account;
	}
}
