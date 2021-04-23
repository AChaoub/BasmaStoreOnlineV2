package org.bos.Achaoub.controllers;

import java.util.List;

import org.bos.Achaoub.entities.CategorieEntity;
import org.bos.Achaoub.entities.ImageEntity;
import org.bos.Achaoub.services.CategorieService;
import org.bos.Achaoub.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users/categories")
public class CategorieController {

	@Autowired
	CategorieService categorieService;

	@Autowired
	ImageService imageService;

	@GetMapping(path = "/one/{idString}")
	public CategorieEntity getOneCategorie(@PathVariable("idString") Integer idString) {

		CategorieEntity categorie = categorieService.getOneCategorie(idString);
		if (categorie == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Category not found", null);
		}
		return categorie;
//			
	}

	@GetMapping(value = "/list")
	public ResponseEntity<List<CategorieEntity>> getListOfCategories() {
		List<CategorieEntity> lists = categorieService.listCategories();
		return new ResponseEntity<List<CategorieEntity>>(lists, HttpStatus.OK);
	}

	@PostMapping
	public CategorieEntity createCategorie(@RequestBody CategorieEntity categorie) {

		CategorieEntity createCategorie =new CategorieEntity();
		boolean isfound =false;
		for(CategorieEntity cat :categorieService.listCategories()) {
			if(categorie.getLibelleCategorie().equals(cat.getLibelleCategorie())) {
				isfound=true;
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Category already exists", null);
			}
		}
		if(isfound==false) {
			createCategorie = categorieService.addCategorie(categorie);
		}
		return createCategorie;
	}

	@PostMapping(path = "/{id}")
	public CategorieEntity updateCategorie(@PathVariable String id, @RequestBody CategorieEntity categorie) {

		int cpt = 0;

		List<CategorieEntity> lists = categorieService.listCategories();

		for (CategorieEntity cat : lists) {
			if (cat.getId() == Integer.parseInt(id)) {
				cpt++;
			}
		}

		// le cas id introuvable il va declancher l'exception
		if (cpt == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Category not found", null);
		}
		CategorieEntity updatedCatagorie = categorieService.updateCategorie(Integer.parseInt(id), categorie);
		return updatedCatagorie;
	}

	@DeleteMapping(path = "/{id}")
	public String deleteCategorie(@PathVariable String id) {

		CategorieEntity categorie = categorieService.getOneCategorie(Integer.parseInt(id));

		if (categorie == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Category not found", null);
		}

		categorieService.deleteCategorie(Integer.parseInt(id));

		return "Categorie was deleted";
	}

	@PostMapping(path = "/AjoutImg/{idS}")
	public CategorieEntity AddImageCategorieTable(@PathVariable String idS, @RequestBody ImageEntity image) {

		boolean isFound = false;

		CategorieEntity categorieByID = null;
		CategorieEntity updatedCatagorie = null;
		ImageEntity addedImage = null;

		List<CategorieEntity> lists = categorieService.listCategories();
		int id = Integer.parseInt(idS);

		for (CategorieEntity cat : lists) {
			if ((cat.getId() == id)) {
				isFound = true;
				categorieByID = categorieService.getOneCategorie(id);
				addedImage = imageService.addImage(image);
				if ((cat.getImage() == null)) {
					categorieByID.setImage(addedImage);
					updatedCatagorie = categorieService.AddImageCategorie(id, categorieByID);
				} else
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(),
							"Categorie a deja sa propre image", null);
			}
		}
		// le cas id introuvable il va declancher l'exception
		if (isFound == false) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Category not found", null);
		}
		System.out.println(updatedCatagorie);
		return updatedCatagorie;
	}
}
