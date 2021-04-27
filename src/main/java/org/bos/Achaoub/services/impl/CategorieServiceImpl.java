package org.bos.Achaoub.services.impl;


import java.util.List;

import javax.transaction.Transactional;

import org.bos.Achaoub.entities.CategorieEntity;
import org.bos.Achaoub.repositories.CategorieRepository;
import org.bos.Achaoub.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategorieServiceImpl implements CategorieService {

	@Autowired
	CategorieRepository categorieRepo;
	

	@Override
	public CategorieEntity addCategorie(CategorieEntity categorie) {

		return categorieRepo.save(categorie);
	}

	@Override
	public CategorieEntity updateCategorie(int id, CategorieEntity categorie) {

		CategorieEntity categorieById = getOneCategorie(id);
		//ImageEntity imageById = imageService.getOneImage(image.getId());
		categorieById.setLibelleCategorie(categorie.getLibelleCategorie());
		//categorieById.setImage(imageById);

		CategorieEntity categorieUpdated = categorieRepo.save(categorieById);
		//System.out.println();
		return categorieUpdated;
	}

	@Override
	public void deleteCategorie(int id) {

		CategorieEntity categorie = getOneCategorie(id);
		// just pour l'exception
		String idToString = "";

		if (categorie == null) {
			idToString = "" + id;
			throw new UsernameNotFoundException(idToString);
		}

		categorieRepo.deleteById(id);
	}

	@Override
	public List<CategorieEntity> listCategories() {
//		ArrayList<CategorieEntity> listCategories = (ArrayList<CategorieEntity>) categorieRepo.findAll();
//		return listCategories;
		return (List<CategorieEntity>) categorieRepo.findAll();
	}

	@Override
	public CategorieEntity getOneCategorie(int id) {

		return categorieRepo.findCategoryById(id);
	}

	@Override
	public CategorieEntity AddImageCategorie(int id, CategorieEntity categorie) {
		CategorieEntity categorieById = getOneCategorie(id);
		if (categorieById.getImage()==null) {
			categorieById.setImage(categorie.getImage());
			categorieRepo.save(categorieById);
		}
		return categorieById;
	}

}
