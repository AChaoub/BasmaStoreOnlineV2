package org.bos.Achaoub.entities;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "images")
@Data
@NoArgsConstructor
public class ImageEntity implements Serializable{
	
	private static final long serialVersionUID = -4378916419108389365L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Image")
	private int id;
	
	@Column(name = "corps_Image",unique = true)
	private String corpsImage;
	
	@Column(name = "extension_Image",nullable = false)
	private String extention;
	
	@ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
	@JoinColumn(name = "id_Produit")
	@JsonBackReference
	private ProduitEntity produit;
	
	public ImageEntity(String corpsImage,String extensionImage,ProduitEntity produit) {
		this.corpsImage = corpsImage;
		this.extention = extensionImage;
		this.produit = produit;
	}
	@Override
	public String toString() {
		return "ImageEntity [id=" + id + ", corpsImage=" + corpsImage + ", extention=" + extention + "]";
	}
	
	



	
	
	
	



	

	
	
	

}
