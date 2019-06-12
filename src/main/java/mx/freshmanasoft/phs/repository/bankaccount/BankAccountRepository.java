package mx.freshmanasoft.phs.repository.bankaccount;

import org.springframework.data.repository.CrudRepository;

import mx.freshmanasoft.phs.entity.Company;
import mx.freshmanasoft.phs.entity.bankaccount.BankAccount;

public interface BankAccountRepository extends CrudRepository<BankAccount, Integer>{
	Iterable<BankAccount> findByStatus(int status);

	Iterable<BankAccount> findByCompany(Company company);

	Iterable<BankAccount> findByCompanyId(Long companyId);
}
