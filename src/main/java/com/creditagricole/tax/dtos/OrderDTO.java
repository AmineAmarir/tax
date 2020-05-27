package com.creditagricole.tax.dtos;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * The Class OrderDTO.
 */
@Data
@ApiModel("Order")
public class OrderDTO {

	/** The id. */
	@ApiModelProperty("The id.")
	private String id;

	/** The items. */
	@ApiModelProperty("Account Number.")
	private List<ItemDTO> items;

	/** The order price without tax. */
	@ApiModelProperty("Account Number.")
	private Double orderPriceWithoutTax;

	/** The order value added tax. */
	@ApiModelProperty("The order value added tax.")
	private Double orderValueAddedTax;

	/** The order full price. */
	@ApiModelProperty("The order full price.")
	private Double orderFullPrice;

}
