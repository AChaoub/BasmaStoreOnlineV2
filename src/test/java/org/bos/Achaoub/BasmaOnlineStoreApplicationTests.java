package org.bos.Achaoub;

import java.util.ArrayList;
import java.util.List;

import org.bos.Achaoub.entities.CategorieEntity;
import org.bos.Achaoub.entities.ProduitEntity;
import org.bos.Achaoub.entities.UserEntity;
import org.bos.Achaoub.services.CategorieService;
import org.bos.Achaoub.services.ProduitService;
import org.bos.Achaoub.services.UserService;
import org.bos.Achaoub.shared.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

//import java.util.ArrayList;
//import java.util.List;
//
//import org.bos.Achaoub.entities.CategorieEntity;
//import org.bos.Achaoub.entities.ProduitEntity;
//import org.bos.Achaoub.entities.UserEntity;
//import org.bos.Achaoub.services.CategorieService;
//import org.bos.Achaoub.services.ProduitService;
//import org.bos.Achaoub.services.UserService;
//import org.bos.Achaoub.shared.dto.UserDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BasmaOnlineStoreApplicationTests {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CategorieService categorieService;
	
	@Autowired
	private ProduitService produitService;
	
	@Test
	void contextLoads() {
	}
	
	@Test
	void listUsers() {
		
		ArrayList<UserEntity> list = userService.listUsers();
		
		for(UserEntity user : list) {
			System.out.println("User  HA :"+user);
		}
	}
	
	@Test
	void getcategorie() {
		CategorieEntity categorie = categorieService.getOneCategorie(2);
		System.out.println(categorie.toString());
	}
	
	@Test
	void updatedcategorie() {
		CategorieEntity categorie = categorieService.getOneCategorie(2);
		int id = categorie.getId();
		categorie.setLibelleCategorie("Achraf");
		//categorieService.updateCategorie(id, categorie);
		System.out.println("okey");
	}
	
	
	
	@Test
	void listsCategories() {
		List<CategorieEntity> list = categorieService.listCategories();
		list.stream().forEach(category -> {
			System.out.println(category.toString());
		});
	}

	
	@Test
	void testgetOneCategory() {
		CategorieEntity one = categorieService.getOneCategorie(2);
		if (one == null) {
			System.out.println("404");
			
		}else {
			System.out.println("200 : " +one.toString());
				
		}
		
	}
	
	
	@Test
	void Update() {
		UserDto	user=userService.updateEmailVerifUser("zaLQYRFWUs");
		System.out.println(user);
	}
	
	
	@Test
	void listsProducts() {
		List<ProduitEntity> list = produitService.listProduits();
		list.stream().forEach(p -> {
			System.out.println(p.toString());
		});
	}
	
	@Test
	public void testAddNewProduct() {
		CategorieEntity category = categorieService.getOneCategorie(1);
		if (category != null) {
			ProduitEntity p = new ProduitEntity();
			p.setLibelleProduit("Test Libelle 1");
			p.setPrixProduit(102.87);
			p.setCategorie(category);
			ProduitEntity produit = produitService.addProduit(p);
			if (produit != null) {
				System.out.println("Product addedd successfully :) ");
			}else {
				System.out.println("adding Product has occured with some errors :( ");
			}
		}else {
			System.out.println("category not found based on the id : " + 1);
		}
	}
	
	
	
	@Test
	void ajouterImage() {
		
		CategorieEntity categorie  = categorieService.AddImageCategorie(2,categorieService.getOneCategorie(2));
		
	}
	


}
