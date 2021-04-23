package org.bos.Achaoub.controllers;





import java.util.ArrayList;

import org.bos.Achaoub.entities.CategorieEntity;
import org.bos.Achaoub.entities.ImageEntity;
import org.bos.Achaoub.entities.ProduitEntity;
import org.bos.Achaoub.services.CategorieService;
import org.bos.Achaoub.services.ImageService;
import org.bos.Achaoub.services.ProduitService;
import org.bos.Achaoub.shared.dto.ProduitDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/users/categories/produits")
public class ProduitController {

	@Autowired
	ProduitService produitService;
	@Autowired
	CategorieService categorieService;
	@Autowired
	ImageService imageService;

//	@Autowired
//	ImageService imageService;

	@GetMapping(path = "/{id}")
	public ProduitEntity getProduit(@PathVariable String id) {

		ProduitEntity produit = produitService.getOneProduit(Integer.parseInt(id));
		if (produit == null) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Product not found", null);

		}

		return produit;
	}

	@PostMapping
	public ProduitEntity createproduit(@RequestBody ProduitDto produit) {

		CategorieEntity categorieAff = categorieService.getOneCategorie(produit.getIdCategorie());
		ProduitEntity produitSav = new ProduitEntity(produit.getLibelleProduit(), produit.getPrixProduit(), categorieAff);
		if((produit.getImages().size()>=4)&&(produit.getImages().size()<=8)){
			for(int img:produit.getImages()) {
				ImageEntity image = imageService.getOneImage(img);
				image.setProduit(produitSav);
				//imageService.addImage(image);
			}
			ProduitEntity createProduit = produitService.addProduit(produitSav);
			return createProduit;
		}
		else {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Inserez 4 images minimuin", null);
		}
	}

	@PostMapping(path = "/{id}")
	public ProduitEntity updateProduit(@PathVariable String id, @RequestBody ProduitDto produitDto) {

		int cpt = 0;
		ProduitEntity produit = produitService.getOneProduit(Integer.parseInt(id));
		CategorieEntity categorie = categorieService.getOneCategorie(produitDto.getIdCategorie());

		ArrayList<ProduitEntity> listProduits = produitService.listProduits(); 

		for (ProduitEntity pro : listProduits) {
			if (pro.getIdProduit() == Integer.parseInt(id)) {
				cpt++;
			}
		}

		// le cas id introuvable il va declancher l'exception
		if (cpt == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Product not found", null);
		}
		
		produit.setLibelleProduit(produitDto.getLibelleProduit());
		produit.setPrixProduit(produitDto.getPrixProduit());
		produit.setCategorie(categorie);
		
		ProduitEntity updatedProduct = produitService.updateProduit(Integer.parseInt(id),produit);
		return updatedProduct;
	}

	@DeleteMapping(path = "/{id}")
	public String deleteProduit(@PathVariable String id) {

		ProduitEntity produit = produitService.getOneProduit(Integer.parseInt(id));

		if (produit == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Product not found", null);
		}
		produitService.deleteProduit(Integer.parseInt(id));
		return "Product was deleted";
	}
}
