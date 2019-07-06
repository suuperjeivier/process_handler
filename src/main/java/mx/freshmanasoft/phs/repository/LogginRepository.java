package mx.freshmanasoft.phs.repository;

import org.springframework.data.repository.CrudRepository;

import mx.freshmanasoft.phs.entity.Loggin;

public interface LogginRepository extends CrudRepository<Loggin, Long>{
	Iterable<Loggin> findAllByOrderByDateDesc();
}
