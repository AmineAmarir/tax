package com.creditagricole.tax.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.creditagricole.tax.beans.OrderBean;
import com.creditagricole.tax.common.Constants;
import com.creditagricole.tax.dtos.OrderDTO;
import com.creditagricole.tax.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class OrderController.
 */
@RestController
@RequestMapping("/api/v1/orders")
@Api(value = "Orders management")
public class OrderController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	/** The order service. */
	@Autowired
	private OrderService orderService;

	/**
	 * Submit new order.
	 *
	 * @param orderBean the order bean
	 * @return the order DTO
	 */
	@ApiOperation(value = "Submit New Order", response = OrderDTO.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Successfully created"),
			@ApiResponse(code = 400, message = "You are sending an invalid request"),
			@ApiResponse(code = 404, message = "A resource is not found") })
	@PostMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public OrderDTO submitNewOrder(@RequestBody OrderBean orderBean) {
		LOGGER.info(Constants.START_CREATE_ORDER);
		final OrderDTO orderDTO = orderService.submitNewOrder(orderBean);
		LOGGER.info(Constants.START_CREATE_ORDER);
		return orderDTO;
	}

}
