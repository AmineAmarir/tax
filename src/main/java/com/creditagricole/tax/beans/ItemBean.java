package com.creditagricole.tax.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class ItemBean.
 */
@Getter
@Setter
public class ItemBean {

	/** The id. */
	private String id;

	/** The product. */
	@JsonProperty(required = true)
	private ProductBean product;

	/** The quantity. */
	private int quantity;

}
