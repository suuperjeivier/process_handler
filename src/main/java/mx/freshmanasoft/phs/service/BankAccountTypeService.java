package mx.freshmanasoft.phs.service;

import mx.freshmanasoft.phs.entity.BankAccountType;

public interface BankAccountTypeService {
	
	Iterable<BankAccountType> fetch();
	BankAccountType post(BankAccountType entity);
	BankAccountType put(BankAccountType entity);
	BankAccountType delete(BankAccountType entity);
}
