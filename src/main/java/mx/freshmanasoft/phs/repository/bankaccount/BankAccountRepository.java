package mx.freshmanasoft.phs.repository.bankaccount;

import java.util.Date;

import org.springframework.data.repository.CrudRepository;

import mx.freshmanasoft.phs.entity.Company;
import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer>{
	Iterable<BankAccount> findByStatus(int status);

	Iterable<BankAccount> findByCompany(Company company);

	Iterable<BankAccount> findByCompanyId(Long companyId);
	
	Iterable<BankAccount> findByBankId(Integer bankId);
	
	Iterable<BankAccount> findByStatusAndDateBetweenOrderByDateDesc(Integer status, Date startDate, Date endDate);
}