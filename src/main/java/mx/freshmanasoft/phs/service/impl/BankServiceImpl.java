package mx.freshmanasoft.phs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.freshmanasoft.phs.entity.Bank;
import mx.freshmanasoft.phs.repository.BankRepository;
import mx.freshmanasoft.phs.service.BankService;
import mx.freshmanasoft.phs.service.impl.bankaccount.BankAccountServiceImpl;

@Service("bankService")
public class BankServiceImpl implements BankService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountServiceImpl.class);

	@Autowired
	private BankRepository repository;
	
	@Override
	public Iterable<Bank> fetch() {
		LOGGER.info("---- CONSULTANDO BANCOS ----");
		return repository.findByStatusOrderByNameAsc(1);
	}

	@Override
	public Bank post(Bank entity) {
		
		return repository.save(entity);
	}

	@Override
	public Bank put(Bank entity) {
		return repository.save(entity);
	}

	@Override
	public Bank delete(Bank entity) {
		return repository.save(entity);
	}

}
