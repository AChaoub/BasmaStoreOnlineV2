package org.bos.Achaoub.services.impl;

import java.util.ArrayList;
import javax.transaction.Transactional;
import org.bos.Achaoub.entities.ImageEntity;
import org.bos.Achaoub.entities.ProduitEntity;
import org.bos.Achaoub.repositories.ImageRepository;
import org.bos.Achaoub.services.ImageService;
import org.bos.Achaoub.services.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImageServiceImpl implements ImageService {

	@Autowired
	ImageRepository imageRepo;

	@Autowired
	ProduitService produitService;
	
	@Override
	public ImageEntity addImage(ImageEntity image) {
	  ImageEntity	imagesaved = imageRepo.save(image);
	  return imagesaved;
	}

	@Override
	public ImageEntity updateImage(int id, ImageEntity image,int idProduit) {

		ImageEntity imageById = getOneImage(id);
		ProduitEntity produitByid = produitService.getOneProduit(idProduit);
		imageById.setCorpsImage(image.getCorpsImage());
		imageById.setExtention(image.getExtention());
		imageById.setProduit(produitByid);

		ImageEntity imageUpdated = imageRepo.save(imageById);

		return imageUpdated;
	}

	@Override
	public void deleteImage(int id) {

		ImageEntity image = getOneImage(id);
		// just pour l'exception
		String idToString = "";

		if (image == null) {
			idToString = "" + id;
			throw new UsernameNotFoundException(idToString);
		}

		imageRepo.deleteById(id);
	}

	@Override
	public ArrayList<ImageEntity> listImages() {
		ArrayList<ImageEntity> listimages = (ArrayList<ImageEntity>) imageRepo.findAll();
		return listimages;
	}

	@Override
	public ImageEntity getOneImage(int id) {
		return imageRepo.findImageById(id);
	}

//	@Override
//	public ImageEntity AddImageimage(int id, ImageEntity image) {
//		ImageEntity imageById = getOneimage(id);
//		ImageEntity imageUpdated = null;
//
//		if (imageById.getImage().equals("")) {
//			imageById.setImage(image.getImage());
//			imageUpdated = imageRepo.save(imageById);
//		} else
//			System.out.println("image a deja une image");
//
//		return imageUpdated;
//	}

}
