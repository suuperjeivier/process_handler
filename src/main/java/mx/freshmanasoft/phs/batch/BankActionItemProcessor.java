package mx.freshmanasoft.phs.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import mx.freshmanasoft.phs.entity.BankAction;
import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

public class BankActionItemProcessor implements ItemProcessor<BankAction, BankAction> {

    private static final Logger log = LoggerFactory.getLogger(BankActionItemProcessor.class);
    private Long accountId;

    public BankActionItemProcessor() {
    	
    }
    public BankActionItemProcessor(Long accountId) {
		this.accountId = accountId;
	}

	@Override
    public BankAction process(final BankAction action) throws Exception {
		
		
       
        final int _STATUS = 1;
        final Long accountIdFinal = accountId;
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
        transformedAction.setAccountingRecord(accountIdFinal);
        transformedAction.setStatus(_STATUS);
        transformedAction.setAccount(account);

        log.info("Converting (" + action + ") into (" + transformedAction + ")");

        return transformedAction;
    }

}