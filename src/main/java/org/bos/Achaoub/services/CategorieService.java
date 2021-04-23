package org.bos.Achaoub.services;

import java.util.List;

import org.bos.Achaoub.entities.CategorieEntity;


public interface CategorieService {

	public CategorieEntity addCategorie(CategorieEntity categorie);
	CategorieEntity updateCategorie(int id, CategorieEntity categorie);
	CategorieEntity AddImageCategorie(int id, CategorieEntity categorie);
	void deleteCategorie(int id);
	CategorieEntity getOneCategorie(int id);
	List<CategorieEntity> listCategories();

}
