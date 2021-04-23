package org.bos.Achaoub.services.impl;

import java.util.ArrayList;

//import java.util.ArrayList;

import javax.transaction.Transactional;

import org.bos.Achaoub.entities.ImageEntity;
import org.bos.Achaoub.entities.ProduitEntity;
import org.bos.Achaoub.repositories.ProduitRepository;
import org.bos.Achaoub.services.ImageService;
import org.bos.Achaoub.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class ProduitServiceImpl implements ProduitService {

	@Autowired
	ProduitRepository produitRepo;
	
	@Autowired
	ImageService imageService;

	@Override
	public ProduitEntity addProduit(ProduitEntity produit) {

		return produitRepo.save(produit);
	}

	@Override
	public ProduitEntity updateProduit(int id, ProduitEntity produit) {

		ProduitEntity produitById = getOneProduit(id);
		produitById.setLibelleProduit(produit.getLibelleProduit());
		produitById.setPrixProduit(produit.getPrixProduit());
		produitById.setCategorie(produit.getCategorie());
		ProduitEntity produitUpdated = produitRepo.save(produitById);

		return produitUpdated;
	}

	@Override
	public void deleteProduit(int id) {

		ProduitEntity produit = getOneProduit(id);
		if (produit == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND.value(), "Product not found", null);
		}
		produitRepo.deleteById(id);
	}

//
	@Override
	public ArrayList<ProduitEntity> listProduits() {
		ArrayList<ProduitEntity> listCategories = (ArrayList<ProduitEntity>) produitRepo.findAll();
		return listCategories;
	}

	@Override
	public ProduitEntity getOneProduit(int id) {

		return produitRepo.findProduitById(id);
	}

	@Override
	public ProduitEntity AddImageProduit(int id, ProduitEntity produit,int idImage) {
		
		ProduitEntity produitById = getOneProduit(id);
		ImageEntity imageById = imageService.getOneImage(idImage);
		imageById.setProduit(produitById);
		produitRepo.save(produitById);
		return produitById;
		
	}


}
