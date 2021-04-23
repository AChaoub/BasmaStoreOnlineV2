package org.bos.Achaoub.services;

import java.util.ArrayList;
import org.bos.Achaoub.entities.ImageEntity;

public interface ImageService {

	public ImageEntity addImage(ImageEntity image);
	ImageEntity updateImage(int id, ImageEntity categorie,int idProduit);
	//ImageEntity AddImageCategorie(int id, ImageEntity categorie);
	void deleteImage(int id);
	ImageEntity getOneImage(int id);
	ArrayList<ImageEntity> listImages();

}
