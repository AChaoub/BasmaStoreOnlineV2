package org.bos.Achaoub.shared.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public @Data class ImageDto implements Serializable {

	
	private static final long serialVersionUID = -7149665972613628137L;
	private String corpsImage;
	private String extention;
	private int idProduit;
	
}
