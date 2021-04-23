package org.bos.Achaoub.controllers;

import java.util.ArrayList;


import org.bos.Achaoub.entities.ImageEntity;
import org.bos.Achaoub.entities.UserEntity;
import org.bos.Achaoub.models.responces.UserResponse;
import org.bos.Achaoub.services.ImageService;
import org.bos.Achaoub.shared.dto.ImageDto;
import org.bos.Achaoub.shared.dto.ListImageDto;
import org.springframework.beans.BeanUtils;
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
@RequestMapping("/users/images")
public class ImageController {

	@Autowired
	ImageService imageService;

	@GetMapping(path = "/{idString}")
	public ImageEntity getImage(@PathVariable String idString) {

		ImageEntity image = imageService.getOneImage(Integer.parseInt(idString));
		if (image == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Picture not found", null);
		}
		return image;
	}

	@PostMapping
	public ImageEntity createImage(@RequestBody ImageEntity image) {
		ImageEntity createPicture = imageService.addImage(image);
		return createPicture;
	}

	@PostMapping(path = "/{id}")
	public ImageEntity updateImage(@PathVariable String id, @RequestBody ImageDto imageDto) {

		int cpt = 0;
		ArrayList<ImageEntity> lists = imageService.listImages();
		ImageEntity image = imageService.getOneImage(Integer.parseInt(id));

		for (ImageEntity img : lists) {
			if (img.getId() == Integer.parseInt(id)) {
				cpt++;
			}
		}

		// le cas id introuvable il va declancher l'exception
		if (cpt == 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Picture not found", null);
		}
		ImageEntity updatedCatagorie = imageService.updateImage(Integer.parseInt(id), image,imageDto.getIdProduit());
		return updatedCatagorie;
	}

	@DeleteMapping(path = "/{id}")
	public String deleteImage(@PathVariable String id) {

		ImageEntity categorie = imageService.getOneImage(Integer.parseInt(id));

		if (categorie == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST.value(), "Category not found", null);
		}

		imageService.deleteImage(Integer.parseInt(id));

		return "Categorie was deleted";
	}
//	@GetMapping(path="/list")
//	public ArrayList<ListImageDto>getAllImages() {
//		
//		
//		
//		ArrayList<ImageEntity> list = imageService.listImages();
//		ArrayList<ListImageDto> listImageDto =new ArrayList<ListImageDto>();
//		
//		for(ImageEntity image : list) {
//			ListImageDto listDto = new ListImageDto();
//			listDto.setId(image.getId());
//			listDto.setCorpsImage(image.getCorpsImage());
//			listDto.setExtention(image.getCorpsImage());
//			listImageDto.add(listDto);	
//		}
//
//			
//		return listImageDto;
//	}
	
	@GetMapping(path="/list")
	public ArrayList<ImageEntity>getAllImages() {
		return imageService.listImages();
	}
	

}
