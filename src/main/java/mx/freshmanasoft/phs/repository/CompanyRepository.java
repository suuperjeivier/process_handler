package mx.freshmanasoft.phs.repository;

import org.springframework.data.repository.CrudRepository;

import mx.freshmanasoft.phs.entity.Company;

public interface CompanyRepository extends CrudRepository<Company, Long>{
	Iterable<Company> findByStatus(int status);
}
