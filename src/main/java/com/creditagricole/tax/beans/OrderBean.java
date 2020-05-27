package com.creditagricole.tax.beans;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class OrderBean.
 */
@Getter
@Setter
public class OrderBean {

	/** The id. */
	private String id;

	/** The items. */
	private List<ItemBean> items;

}
