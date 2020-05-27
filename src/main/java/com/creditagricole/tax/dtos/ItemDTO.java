package com.creditagricole.tax.dtos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The Class ItemDTO.
 */
@Data
@ApiModel("Item")
public class ItemDTO {

	/** The id. */
	@ApiModelProperty("The id.")
	private String id;

	/** The product. */
	@ApiModelProperty("The product.")
	private ProductDTO product;

	/** The quantity. */
	@ApiModelProperty("The quantity.")
	private int quantity;

	/** The item price without tax. */
	@ApiModelProperty("The item price without tax.")
	private Double itemPriceWithoutTax;

	/** The item value added tax. */
	@ApiModelProperty("The item value added tax")
	private Double itemValueAddedTax;

	/** The full price. */
	@ApiModelProperty("The full price.")
	private Double itemFullPrice;

}
