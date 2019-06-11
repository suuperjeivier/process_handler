package mx.freshmanasoft.phs.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.freshmanasoft.phs.entity.BankAction;
import mx.freshmanasoft.phs.repository.BankActionRepository;
import mx.freshmanasoft.phs.service.BankActionService;

@Service("bankActionService")
public class BankActionServiceImpl implements BankActionService{
	private static final Logger LOGGER = LoggerFactory.getLogger(BankActionServiceImpl.class);
	
	@Autowired
	private BankActionRepository repository;

	@Override
	public Iterable<BankAction> fetch() {
		LOGGER.debug("getting actions!");
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
