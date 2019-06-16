package mx.freshmanasoft.phs.service;

import mx.freshmanasoft.phs.entity.Bank;

public interface BankService {
	
	Iterable<Bank> fetch();
	Bank post(Bank entity);
	Bank put(Bank entity);
	Bank delete(Bank entity);
}
