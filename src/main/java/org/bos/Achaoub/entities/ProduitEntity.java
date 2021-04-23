package org.bos.Achaoub.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "produits")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduitEntity implements Serializable {

	private static final long serialVersionUID = 587018697823129976L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Produit")
	private int idProduit;
	
	@Column(name = "libelle_Produit")
	private String libelleProduit;
	
	@Column(name = "prix_Produit")
	private Double prixProduit;

	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name = "id_Categorie")
	@JsonBackReference
	private CategorieEntity categorie;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "produit",fetch=FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	private List<ImageEntity> images = new ArrayList<ImageEntity>();
	
	
	public ProduitEntity(String libelle,Double prix,CategorieEntity categorie) {
		this.libelleProduit = libelle;
		this.prixProduit  = prix;
		this.categorie = categorie;		
	}


	
	
	
	@Override
	public String toString() {
		return "ProduitEntity [idProduit=" + idProduit + ", libelleProduit=" + libelleProduit + ", prixProduit="
				+ prixProduit +"]";

	}
	
	
	


	


	
	

	

	
	
	

	
	

}
