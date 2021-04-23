package org.bos.Achaoub.services;

import java.util.ArrayList;

import org.bos.Achaoub.entities.ProduitEntity;


public interface ProduitService {

	public ProduitEntity addProduit(ProduitEntity produit);
	ProduitEntity updateProduit(int id, ProduitEntity produit);
	ProduitEntity AddImageProduit(int id, ProduitEntity produit,int idImage);
	void deleteProduit(int id);
	ProduitEntity getOneProduit(int id);
	ArrayList<ProduitEntity> listProduits();

}
