package mx.freshmanasoft.phs.service.bankaccount;



import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

public interface BankAccountService {
	Iterable<BankAccount> fetch();
	Iterable<BankAccount> fetch(Long companyId);
	BankAccount post(BankAccount entity);
	BankAccount put(BankAccount entity);
	BankAccount delete(BankAccount entity);
	
}
