package org.bos.Achaoub.shared.dto;

import java.io.Serializable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public @Data class ListImageDto implements Serializable {


	private static final long serialVersionUID = -4956585446391913831L;
	private int id;
	private String corpsImage;
	private String extention;

}
