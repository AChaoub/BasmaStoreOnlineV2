package org.bos.Achaoub.shared.dto;

import java.io.Serializable;
import java.util.ArrayList;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public @Data class ProduitDto implements Serializable {

	
	private static final long serialVersionUID = -4920057206318570098L;
	private String libelleProduit;
	private Double prixProduit;
	ArrayList<Integer> images;
	private int idCategorie;
	
}
