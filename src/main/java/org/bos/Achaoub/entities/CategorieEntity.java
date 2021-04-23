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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CategorieEntity implements Serializable {

	private static final long serialVersionUID = -6208520624489252235L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Categorie")
	private int id;

	@Column(name = "libelle_Categorie")
	private String libelleCategorie;

	@OneToOne
	@JoinColumn(name = "id_Image")
	private ImageEntity image;

	@OneToMany(mappedBy = "categorie", cascade = { CascadeType.PERSIST}, fetch = FetchType.LAZY)
	@JsonManagedReference
	private List<ProduitEntity> produits = new ArrayList<ProduitEntity>();

	public CategorieEntity(int id, String libelleCategorie, ImageEntity image) {
		super();
		this.id = id;
		this.libelleCategorie = libelleCategorie;
		this.image = image;
	}

	@Override
	public String toString() {
		return "CategorieEntity [id=" + id + ", libelleCategorie=" + libelleCategorie + ", image=" + image
				+ "]";
	}
	
	

}
