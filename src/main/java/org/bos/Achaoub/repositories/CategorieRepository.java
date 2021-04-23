package org.bos.Achaoub.repositories;

import org.bos.Achaoub.entities.CategorieEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends CrudRepository<CategorieEntity, Integer> {
	
	@Query("FROM categories where id_categorie =?1")
	CategorieEntity findCategoryById(int id);
	
}
