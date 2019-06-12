package mx.freshmanasoft.phs.repository;

import org.springframework.data.repository.CrudRepository;

import mx.freshmanasoft.phs.entity.BankAction;

public interface BankActionRepository extends CrudRepository<BankAction, Long>{
	Iterable<BankAction> findByStatus(int status);

	Iterable<BankAction> findByAccountId(Long accountId);
}
