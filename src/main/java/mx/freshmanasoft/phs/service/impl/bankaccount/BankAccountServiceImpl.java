package mx.freshmanasoft.phs.service.impl.bankaccount;


import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;
import mx.freshmanasoft.phs.repository.bankaccount.BankAccountRepository;
import mx.freshmanasoft.phs.service.bankaccount.BankAccountService;

@Service("bankAccountService")
public class BankAccountServiceImpl implements BankAccountService{
	private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountServiceImpl.class);
	
	@Autowired
	private BankAccountRepository repository;
	@Override
	public Iterable<BankAccount> fetch() {
		LOGGER.info("---- CONSULTANDO CUENTAS BANCARIAS ----");
		return repository.findByStatus(1);
	}
	
	@Override
	public Iterable<BankAccount> fetchByBetweenDates(Integer status, Date startDate, Date endDate) {
		LOGGER.info("---- CONSULTANDO CUENTAS BANCARIAS POR FECHAS ["+startDate.toString()+","+endDate.toString()+"]----");
		Calendar date = Calendar.getInstance();
		date.setTime(endDate);
		date.add(Calendar.DAY_OF_YEAR, 1);
		return repository.findByStatusAndDateBetweenOrderByDateDesc(status, startDate, date.getTime());
	}


	@Override
	public BankAccount post(BankAccount entity) {
		LOGGER.info("---- REGISTRANDO CUENTA BANCARIA ----");
		return repository.save(entity);
	}

	@Override
	public BankAccount put(BankAccount entity) {
		LOGGER.info("---- ACTUALIZANDO CUENTA BANCARIA ----");
		return repository.save(entity);
	}

	@Override
	public BankAccount delete(BankAccount entity) {
		LOGGER.info("---- ELIMINANDO CUENTA BANCARIA ----");
		return repository.save(entity);
	}

	@Override
	public Iterable<BankAccount> fetch(Long companyId) {
		
		return repository.findByCompanyId(companyId);
	}
}
