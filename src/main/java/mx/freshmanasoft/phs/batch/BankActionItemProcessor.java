package mx.freshmanasoft.phs.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import mx.freshmanasoft.phs.entity.BankAction;

public class BankActionItemProcessor implements ItemProcessor<BankAction, BankAction> {

    private static final Logger log = LoggerFactory.getLogger(BankActionItemProcessor.class);

    @Override
    public BankAction process(final BankAction action) throws Exception {
        final String name = action.getName().toUpperCase();
        final String cusip = action.getCusip().toUpperCase();

        final BankAction transformedAction = new BankAction(name, cusip);

        log.info("Converting (" + action + ") into (" + transformedAction + ")");

        return transformedAction;
    }

}