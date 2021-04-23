package org.bos.Achaoub.repositories;


import org.bos.Achaoub.entities.ImageEntity;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageEntity, Integer> {
	
	@Query("FROM images where id_Image =?1")
	ImageEntity findImageById(int id);
	
}
