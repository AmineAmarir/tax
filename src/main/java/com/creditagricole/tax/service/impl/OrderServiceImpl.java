package com.creditagricole.tax.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.creditagricole.tax.beans.OrderBean;
import com.creditagricole.tax.common.Constants;
import com.creditagricole.tax.dtos.OrderDTO;
import com.creditagricole.tax.entities.Item;
import com.creditagricole.tax.entities.Order;
import com.creditagricole.tax.entities.Product;
import com.creditagricole.tax.exceptions.IncorrectParametersException;
import com.creditagricole.tax.repositories.ItemRepository;
import com.creditagricole.tax.repositories.OrderRepository;
import com.creditagricole.tax.repositories.ProductRepository;
import com.creditagricole.tax.service.OrderService;

/**
 * The Class OrderServiceImpl.
 */
@Service
public class OrderServiceImpl implements OrderService {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	/** The product repository. */
	@Autowired
	private ProductRepository productRepository;

	/** The item repository. */
	@Autowired
	private ItemRepository itemRepository;

	/** The order repository. */
	@Autowired
	private OrderRepository orderRepository;

	/** The model mapper. */
	@Autowired
	private ModelMapper modelMapper;

	/**
	 * Submit new order.
	 *
	 * @param orderBean the order bean
	 * @return the order DTO
	 */
	@Override
	public OrderDTO submitNewOrder(final OrderBean orderBean) {
		LOGGER.info(Constants.START_CREATE_ORDER_SERVICE);
		if (orderBean != null && orderBean.getItems() != null && !orderBean.getItems().isEmpty()) {
			final List<Item> items = new ArrayList<>();
			final List<Product> products = new ArrayList<>();
			orderBean.getItems().stream().forEach(itemBean -> {
				if (itemBean == null || itemBean.getProduct() == null) {
					throw new IncorrectParametersException();
				}
				final Product product = Product.newBuilder()
						.designation(itemBean.getProduct().getDesignation())
						.priceWithoutTax(itemBean.getProduct().getPriceWithoutTax())
						.productKind(itemBean.getProduct().getProductKind())
						.productOrigin(itemBean.getProduct().getProductOrigin())
						.build()
						.toBuilder()
						.build();
				products.add(product);
				final Item item = Item.newBuilder()
						.product(product)
						.quantity(itemBean.getQuantity())
						.build()
						.toBuilder()
						.build();
				items.add(item);
			});
			productRepository.saveAll(products);
			itemRepository.saveAll(items);
			Order order = Order.newBuilder().items(items).build().toBuilder().build();
			orderRepository.save(order);
			LOGGER.info(Constants.END_CREATE_ORDER_SERVICE);
			return modelMapper.map(order, OrderDTO.class);
		} else {
			throw new IncorrectParametersException();
		}
	}

}
