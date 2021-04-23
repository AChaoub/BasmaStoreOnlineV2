package org.bos.Achaoub.repositories;

import org.bos.Achaoub.entities.ProduitEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends CrudRepository<ProduitEntity, Integer> {
	
	@Query("FROM produits where id_Produit =?1")
	ProduitEntity findProduitById(int id);
	
}
