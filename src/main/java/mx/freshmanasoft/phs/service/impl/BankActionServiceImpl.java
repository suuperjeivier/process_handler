package mx.freshmanasoft.phs.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.freshmanasoft.phs.entity.BankAction;
import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;
import mx.freshmanasoft.phs.repository.BankActionRepository;
import mx.freshmanasoft.phs.repository.bankaccount.BankAccountRepository;
import mx.freshmanasoft.phs.service.BankActionService;
import mx.freshmanasoft.phs.service.bankaccount.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("bankActionService")
public class BankActionServiceImpl implements BankActionService{
	private static final Logger LOGGER = LoggerFactory.getLogger(BankActionServiceImpl.class);
	
	@Autowired
	private BankActionRepository repository;

	@Override
	public Iterable<BankAction> fetch() {		
		return repository.findAll();
	}

	@Override
	public BankAction post(BankAction entity) {
		return repository.save(entity);
	}

	@Override
	public BankAction put(BankAction entity) {
		return repository.save(entity);
	}

	@Override
	public BankAction delete(BankAction entity) {
		repository.delete(entity);
		entity.setId(null);
		return entity;
	}
	

}
