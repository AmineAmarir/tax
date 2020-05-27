package com.creditagricole.tax.dtos;

import com.creditagricole.tax.enums.ProductKindEnum;
import com.creditagricole.tax.enums.ProductOriginEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The Class ProductDTO.
 */
@Data
@ApiModel("Product")
public class ProductDTO {

	/** The id. */
	@ApiModelProperty("The id.")
	private String id;

	/** The description. */
	@ApiModelProperty("The description.")
	private String designation;

	/** The price without tax. */
	@ApiModelProperty("The price without tax.")
	private Double priceWithoutTax;

	/** The product kind. */
	@ApiModelProperty("The product kind.")
	private ProductKindEnum productKind;

	/** The product origin. */
	@ApiModelProperty("The product origin.")
	private ProductOriginEnum productOrigin;

	/** The value added tax. */
	@ApiModelProperty("The value added tax.")
	private Double valueAddedTax;

	/** The full price. */
	@ApiModelProperty("The full price.")
	private Double fullPrice;

}
