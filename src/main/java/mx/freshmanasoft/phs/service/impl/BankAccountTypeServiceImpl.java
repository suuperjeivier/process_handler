package mx.freshmanasoft.phs.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.freshmanasoft.phs.entity.BankAccountType;
import mx.freshmanasoft.phs.repository.BankAccountTypeRepository;
import mx.freshmanasoft.phs.service.BankAccountTypeService;

@Service("bankAccountTypeService")
public class BankAccountTypeServiceImpl implements BankAccountTypeService{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountTypeServiceImpl.class);

	@Autowired
	private BankAccountTypeRepository repository;


	@Override
	public BankAccountType post(BankAccountType entity) {
		LOGGER.debug("busacando tipos de cuenta");
		return repository.save(entity);
	}

	@Override
	public BankAccountType put(BankAccountType entity) {		
		return repository.save(entity);
	}

	@Override
	public BankAccountType delete(BankAccountType entity) {
		repository.delete(entity);
		entity.setId(null);
		return entity;
	}

	@Override
	public Iterable<BankAccountType> fetch() {
		
		return repository.findAll();
	}

}
