package com.creditagricole.tax.beans;

import com.creditagricole.tax.enums.ProductKindEnum;
import com.creditagricole.tax.enums.ProductOriginEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class ProductBean.
 */
@Getter
@Setter
public class ProductBean {

	/** The id. */
	private String id;

	/** The description. */
	private String designation;

	/** The price without tax. */
	private Double priceWithoutTax;

	/** The product kind. */
	private ProductKindEnum productKind;

	/** The product origin. */
	private ProductOriginEnum productOrigin;

}
