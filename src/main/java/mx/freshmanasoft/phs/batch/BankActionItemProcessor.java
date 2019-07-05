package mx.freshmanasoft.phs.batch;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import mx.freshmanasoft.phs.entity.BankAction;
import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

public class BankActionItemProcessor implements ItemProcessor<BankAction, BankAction> {

    private static final Logger log = LoggerFactory.getLogger(BankActionItemProcessor.class);
    private Long accountId = 0L;
    private int subAccountId = 0;

    public BankActionItemProcessor() {
    	
    }
    public BankActionItemProcessor(Long accountId, int subAccountId) {
    	log.debug("initializing proccesor");
		this.accountId = accountId;
		this.subAccountId = subAccountId;
	}

	@Override
    public BankAction process(final BankAction action) throws Exception {	
		
       
        final int _STATUS = 1;
        final Long accountIdFinal = accountId;
        final int subAccountIdFinal = subAccountId;
        final BankAccount account = new BankAccount(accountIdFinal);
        
        BankAction transformedAction = new BankAction();
        transformedAction = action;
        switch (transformedAction.getRegistroContable()) {
		case "Capital":
		case "Resultados":
			break;
		default:
			transformedAction.setRegistroContable("Capital");
			break;
		}
        
        switch (transformedAction.getValor()) {
		case "Acciones":
		case "Deuda, Bonos y Gubernamental en Pesos":	
		case "Deuda, Bonos y Gubernamental en USD":
		case "Efectivo":
			break;			
		default:
			transformedAction.setValor("Acciones");
			break;
		}
        switch (transformedAction.getMonedaOriginal()) {
        case "AUD":
        case "GBP":
		case "EUR":
		case "USD":	
		case "MXP":		
			break;			
		default:
			transformedAction.setMonedaOriginal("USD");
			break;
		}        
        if(action.getFechaInicio() != null && !action.getFechaInicio().isEmpty()) {
        	Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(action.getFechaInicio());  
        	transformedAction.setFechaInicioReal(date1);
        	transformedAction.setFechaDeAdquisicion(date1);
        }
        if(action.getFechaFinal() != null && !action.getFechaFinal().isEmpty()) {
        	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(action.getFechaFinal());  
        	transformedAction.setFechaFinalReal(date2);
        }
        
        if(action.getCusip().trim() != null && !action.getCusip().trim().isEmpty()) {
        	transformedAction.setCusip(action.getCusip().trim().toUpperCase());
        }else {
        	transformedAction.setCusip(null);
        }
        if(action.getIsinSerie().trim() != null && !action.getIsinSerie().trim().isEmpty()) {
        	transformedAction.setIsinSerie(action.getIsinSerie().trim().toUpperCase());
        }else {
        	transformedAction.setIsinSerie(null);
        }
        if(action.getSecId().trim() != null && !action.getSecId().trim().isEmpty()) {
        	transformedAction.setSecId(action.getSecId().trim().toUpperCase());
        }else {
        	transformedAction.setSecId(null);
        }
        transformedAction.setSubAccountType(subAccountIdFinal);
        transformedAction.setAccountingRecord(accountIdFinal);
        transformedAction.setStatus(_STATUS);
        transformedAction.setAccount(account);

        //log.info("Converting (" + action + ") into (" + transformedAction + ")");

        return transformedAction;
    }

}