package org.bos.Achaoub.repositories;

import org.bos.Achaoub.entities.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {
	
	UserEntity findByEmail(String email);
	UserEntity findByUserId(String userId);

}
