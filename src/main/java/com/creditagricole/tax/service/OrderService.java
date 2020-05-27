package com.creditagricole.tax.service;

import com.creditagricole.tax.beans.OrderBean;
import com.creditagricole.tax.dtos.OrderDTO;

/**
 * The Interface OrderService.
 */
public interface OrderService {

	/**
	 * Submit new order.
	 *
	 * @param orderBean the order bean
	 * @return the order DTO
	 */
	public OrderDTO submitNewOrder(OrderBean orderBean);

}
