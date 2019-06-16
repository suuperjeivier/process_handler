package mx.freshmanasoft.phs.repository;

import org.springframework.data.repository.CrudRepository;

import mx.freshmanasoft.phs.entity.Bank;

public interface BankRepository extends CrudRepository<Bank, Long>{
	
	Iterable<Bank> findByStatusOrderByNameAsc(Integer status);
	
}
