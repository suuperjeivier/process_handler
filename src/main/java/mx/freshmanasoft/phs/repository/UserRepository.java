package mx.freshmanasoft.phs.repository;

import org.springframework.data.repository.CrudRepository;

import mx.freshmanasoft.phs.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	User findByUsername(String username);
}
