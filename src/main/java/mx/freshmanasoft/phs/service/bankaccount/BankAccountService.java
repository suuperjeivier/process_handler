package mx.freshmanasoft.phs.service.bankaccount;



import java.util.Date;

import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

public interface BankAccountService {
	Iterable<BankAccount> fetch();
	Iterable<BankAccount> fetch(Long companyId);
	Iterable<BankAccount> fetchByBetweenDates(Integer status, Date startDate, Date endDate);
	BankAccount post(BankAccount entity);
	BankAccount put(BankAccount entity);
	BankAccount delete(BankAccount entity);
	
}
