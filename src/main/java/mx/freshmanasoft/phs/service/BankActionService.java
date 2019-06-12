package mx.freshmanasoft.phs.service;

import mx.freshmanasoft.phs.entity.BankAction;

public interface BankActionService {
	Iterable<BankAction> fetch();
	BankAction post(BankAction entity);
	BankAction put(BankAction entity);
	BankAction delete(BankAction entity);
	Iterable<BankAction> fetch(Long accountId);
	
}
