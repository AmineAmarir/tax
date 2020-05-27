package com.creditagricole.tax.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.test.context.SpringBootTest;

import com.creditagricole.tax.beans.ItemBean;
import com.creditagricole.tax.beans.OrderBean;
import com.creditagricole.tax.beans.ProductBean;
import com.creditagricole.tax.dtos.OrderDTO;
import com.creditagricole.tax.entities.Item;
import com.creditagricole.tax.entities.Order;
import com.creditagricole.tax.entities.Product;
import com.creditagricole.tax.enums.ProductKindEnum;
import com.creditagricole.tax.enums.ProductOriginEnum;
import com.creditagricole.tax.exceptions.IncorrectParametersException;
import com.creditagricole.tax.repositories.ItemRepository;
import com.creditagricole.tax.repositories.OrderRepository;
import com.creditagricole.tax.repositories.ProductRepository;

/**
 * The Class OrderServiceImplTest.
 */
@SpringBootTest
public class OrderServiceImplTest {

	/** The order service impl. */
	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	/** The item repository. */
	@Mock
	private ItemRepository itemRepository;

	/** The order repository. */
	@Mock
	private OrderRepository orderRepository;

	/** The product repository. */
	@Mock
	private ProductRepository productRepository;

	/** The model mapper. */
	@Mock
	private ModelMapper modelMapper;

	/** The local model mapper. */
	private static ModelMapper localModelMapper;

	/**
	 * Sets the up.
	 */
	@BeforeAll
	public static void setUp() {
		localModelMapper = new ModelMapper();
		localModelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	/**
	 * Test new order incorrect parameter order bean null.
	 */
	@Test
	public void testNewOrderIncorrectParameterOrderBeanNull() {
		final OrderBean orderBean = null;

		assertThrows(IncorrectParametersException.class, () -> {
			orderServiceImpl.submitNewOrder(orderBean);
		});
	}

	/**
	 * Test new order incorrect parameter order bean null items.
	 */
	@Test
	public void testNewOrderIncorrectParameterOrderBeanNullItems() {
		final OrderBean orderBean = new OrderBean();
		orderBean.setItems(null);

		assertThrows(IncorrectParametersException.class, () -> {
			orderServiceImpl.submitNewOrder(orderBean);
		});
	}

	/**
	 * Test new order incorrect parameter order bean empty item.
	 */
	@Test
	public void testNewOrderIncorrectParameterOrderBeanEmptyItem() {
		final OrderBean orderBean = new OrderBean();
		orderBean.setItems(new ArrayList<>());

		assertThrows(IncorrectParametersException.class, () -> {
			orderServiceImpl.submitNewOrder(orderBean);
		});
	}

	/**
	 * Test new order case 1.
	 */
	@Test
	public void testNewOrderCase1() {
		// Preparing input
		final OrderBean orderBean = new OrderBean();
		orderBean.setItems(new ArrayList<>());

		Order order = new Order();
		order.setItems(new ArrayList<>());

		final ProductBean productBean1 = new ProductBean();
		productBean1.setDesignation("Livre");
		productBean1.setPriceWithoutTax(12.49);
		productBean1.setProductKind(ProductKindEnum.BOOK);
		productBean1.setProductOrigin(ProductOriginEnum.LOCAL);

		final Product product1 = Product.newBuilder()
				.designation(productBean1.getDesignation())
				.priceWithoutTax(productBean1.getPriceWithoutTax())
				.productKind(productBean1.getProductKind())
				.productOrigin(productBean1.getProductOrigin())
				.build()
				.toBuilder()
				.build();

		final ItemBean itemBean1 = new ItemBean();
		itemBean1.setProduct(productBean1);
		itemBean1.setQuantity(2);

		final Item item1 = Item.newBuilder()
				.product(product1)
				.quantity(itemBean1.getQuantity())
				.build()
				.toBuilder()
				.build();

		orderBean.getItems().add(itemBean1);

		order.getItems().add(item1);

		final ProductBean productBean2 = new ProductBean();
		productBean2.setDesignation("CD musical");
		productBean2.setPriceWithoutTax(14.99);
		productBean2.setProductKind(ProductKindEnum.OTHER);
		productBean2.setProductOrigin(ProductOriginEnum.LOCAL);

		final Product product2 = Product.newBuilder()
				.designation(productBean2.getDesignation())
				.priceWithoutTax(productBean2.getPriceWithoutTax())
				.productKind(productBean2.getProductKind())
				.productOrigin(productBean2.getProductOrigin())
				.build()
				.toBuilder()
				.build();

		final ItemBean itemBean2 = new ItemBean();
		itemBean2.setProduct(productBean2);
		itemBean2.setQuantity(1);

		final Item item2 = Item.newBuilder()
				.product(product2)
				.quantity(itemBean2.getQuantity())
				.build()
				.toBuilder()
				.build();

		orderBean.getItems().add(itemBean2);

		order.getItems().add(item2);

		final ProductBean productBean3 = new ProductBean();
		productBean3.setDesignation("Barre de chocolat");
		productBean3.setPriceWithoutTax(0.85);
		productBean3.setProductKind(ProductKindEnum.FIRST_NECESSITY);
		productBean3.setProductOrigin(ProductOriginEnum.LOCAL);

		final Product product3 = Product.newBuilder()
				.designation(productBean3.getDesignation())
				.priceWithoutTax(productBean3.getPriceWithoutTax())
				.productKind(productBean3.getProductKind())
				.productOrigin(productBean3.getProductOrigin())
				.build()
				.toBuilder()
				.build();

		final ItemBean itemBean3 = new ItemBean();
		itemBean3.setProduct(productBean3);
		itemBean3.setQuantity(3);

		final Item item3 = Item.newBuilder()
				.product(product3)
				.quantity(itemBean3.getQuantity())
				.build()
				.toBuilder()
				.build();

		orderBean.getItems().add(itemBean3);

		order.getItems().add(item3);

		order = order.toBuilder().build();

		final OrderDTO orderDTO = localModelMapper.map(order, OrderDTO.class);

		when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

		final OrderDTO result = orderServiceImpl.submitNewOrder(orderBean);

		assertNotNull(result);
		assertNotNull(result.getItems());

		assertEquals(48.05, result.getOrderFullPrice());
		assertEquals(5.5, result.getOrderValueAddedTax());

		assertEquals(27.5, result.getItems().get(0).getItemFullPrice());
		assertEquals(18.0, result.getItems().get(1).getItemFullPrice());
		assertEquals(2.55, result.getItems().get(2).getItemFullPrice());
	}

	/**
	 * Test new order case 2.
	 */
	@Test
	public void testNewOrderCase2() {
		// Preparing input
		final OrderBean orderBean = new OrderBean();
		orderBean.setItems(new ArrayList<>());

		Order order = new Order();
		order.setItems(new ArrayList<>());

		final ProductBean productBean1 = new ProductBean();
		productBean1.setDesignation("Boite de chocolat importé");
		productBean1.setPriceWithoutTax(10.0);
		productBean1.setProductKind(ProductKindEnum.FIRST_NECESSITY);
		productBean1.setProductOrigin(ProductOriginEnum.IMPORTED);

		final Product product1 = Product.newBuilder()
				.designation(productBean1.getDesignation())
				.priceWithoutTax(productBean1.getPriceWithoutTax())
				.productKind(productBean1.getProductKind())
				.productOrigin(productBean1.getProductOrigin())
				.build()
				.toBuilder()
				.build();

		final ItemBean itemBean1 = new ItemBean();
		itemBean1.setProduct(productBean1);
		itemBean1.setQuantity(2);

		final Item item1 = Item.newBuilder()
				.product(product1)
				.quantity(itemBean1.getQuantity())
				.build()
				.toBuilder()
				.build();

		orderBean.getItems().add(itemBean1);

		order.getItems().add(item1);

		final ProductBean productBean2 = new ProductBean();
		productBean2.setDesignation("Flacon de parfum importé");
		productBean2.setPriceWithoutTax(47.5);
		productBean2.setProductKind(ProductKindEnum.OTHER);
		productBean2.setProductOrigin(ProductOriginEnum.IMPORTED);

		final Product product2 = Product.newBuilder()
				.designation(productBean2.getDesignation())
				.priceWithoutTax(productBean2.getPriceWithoutTax())
				.productKind(productBean2.getProductKind())
				.productOrigin(productBean2.getProductOrigin())
				.build()
				.toBuilder()
				.build();

		final ItemBean itemBean2 = new ItemBean();
		itemBean2.setProduct(productBean2);
		itemBean2.setQuantity(3);

		final Item item2 = Item.newBuilder()
				.product(product2)
				.quantity(itemBean2.getQuantity())
				.build()
				.toBuilder()
				.build();

		orderBean.getItems().add(itemBean2);

		order.getItems().add(item2);

		order = order.toBuilder().build();

		final OrderDTO orderDTO = localModelMapper.map(order, OrderDTO.class);

		when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

		final OrderDTO result = orderServiceImpl.submitNewOrder(orderBean);

		assertNotNull(result);
		assertNotNull(result.getItems());

		assertEquals(199.15, result.getOrderFullPrice());
		assertEquals(36.65, result.getOrderValueAddedTax());

		assertEquals(21.0, result.getItems().get(0).getItemFullPrice());
		assertEquals(178.15, result.getItems().get(1).getItemFullPrice());
	}

	/**
	 * Test new order case 3.
	 */
	@Test
	public void testNewOrderCase3() {
		// Preparing input
		final OrderBean orderBean = new OrderBean();
		orderBean.setItems(new ArrayList<>());

		Order order = new Order();
		order.setItems(new ArrayList<>());

		final ProductBean productBean1 = new ProductBean();
		productBean1.setDesignation("Flacon de parfum importé");
		productBean1.setPriceWithoutTax(27.99);
		productBean1.setProductKind(ProductKindEnum.OTHER);
		productBean1.setProductOrigin(ProductOriginEnum.IMPORTED);

		final Product product1 = Product.newBuilder()
				.designation(productBean1.getDesignation())
				.priceWithoutTax(productBean1.getPriceWithoutTax())
				.productKind(productBean1.getProductKind())
				.productOrigin(productBean1.getProductOrigin())
				.build()
				.toBuilder()
				.build();

		final ItemBean itemBean1 = new ItemBean();
		itemBean1.setProduct(productBean1);
		itemBean1.setQuantity(2);

		final Item item1 = Item.newBuilder()
				.product(product1)
				.quantity(itemBean1.getQuantity())
				.build()
				.toBuilder()
				.build();

		orderBean.getItems().add(itemBean1);

		order.getItems().add(item1);

		final ProductBean productBean2 = new ProductBean();
		productBean2.setDesignation("Flacon de parfum");
		productBean2.setPriceWithoutTax(18.99);
		productBean2.setProductKind(ProductKindEnum.OTHER);
		productBean2.setProductOrigin(ProductOriginEnum.LOCAL);

		final Product product2 = Product.newBuilder()
				.designation(productBean2.getDesignation())
				.priceWithoutTax(productBean2.getPriceWithoutTax())
				.productKind(productBean2.getProductKind())
				.productOrigin(productBean2.getProductOrigin())
				.build()
				.toBuilder()
				.build();

		final ItemBean itemBean2 = new ItemBean();
		itemBean2.setProduct(productBean2);
		itemBean2.setQuantity(1);

		final Item item2 = Item.newBuilder()
				.product(product2)
				.quantity(itemBean2.getQuantity())
				.build()
				.toBuilder()
				.build();

		orderBean.getItems().add(itemBean2);

		order.getItems().add(item2);

		final ProductBean productBean3 = new ProductBean();
		productBean3.setDesignation("Boîte de pilules contre la migraine");
		productBean3.setPriceWithoutTax(9.75);
		productBean3.setProductKind(ProductKindEnum.FIRST_NECESSITY);
		productBean3.setProductOrigin(ProductOriginEnum.LOCAL);

		final Product product3 = Product.newBuilder()
				.designation(productBean3.getDesignation())
				.priceWithoutTax(productBean3.getPriceWithoutTax())
				.productKind(productBean3.getProductKind())
				.productOrigin(productBean3.getProductOrigin())
				.build()
				.toBuilder()
				.build();

		final ItemBean itemBean3 = new ItemBean();
		itemBean3.setProduct(productBean3);
		itemBean3.setQuantity(3);

		final Item item3 = Item.newBuilder()
				.product(product3)
				.quantity(itemBean3.getQuantity())
				.build()
				.toBuilder()
				.build();

		orderBean.getItems().add(itemBean3);

		order.getItems().add(item3);

		final ProductBean productBean4 = new ProductBean();
		productBean4.setDesignation("Boîte de chocolats importés");
		productBean4.setPriceWithoutTax(11.25);
		productBean4.setProductKind(ProductKindEnum.FIRST_NECESSITY);
		productBean4.setProductOrigin(ProductOriginEnum.IMPORTED);

		final Product product4 = Product.newBuilder()
				.designation(productBean4.getDesignation())
				.priceWithoutTax(productBean4.getPriceWithoutTax())
				.productKind(productBean4.getProductKind())
				.productOrigin(productBean4.getProductOrigin())
				.build()
				.toBuilder()
				.build();

		final ItemBean itemBean4 = new ItemBean();
		itemBean4.setProduct(productBean4);
		itemBean4.setQuantity(2);

		final Item item4 = Item.newBuilder()
				.product(product4)
				.quantity(itemBean4.getQuantity())
				.build()
				.toBuilder()
				.build();

		orderBean.getItems().add(itemBean4);

		order.getItems().add(item4);

		order = order.toBuilder().build();

		final OrderDTO orderDTO = localModelMapper.map(order, OrderDTO.class);

		when(modelMapper.map(order, OrderDTO.class)).thenReturn(orderDTO);

		final OrderDTO result = orderServiceImpl.submitNewOrder(orderBean);

		assertNotNull(result);
		assertNotNull(result.getItems());

		assertEquals(145.7, result.getOrderFullPrice());
		assertEquals(18.95, result.getOrderValueAddedTax());

		assertEquals(70.0, result.getItems().get(0).getItemFullPrice());
		assertEquals(22.80, result.getItems().get(1).getItemFullPrice());
		assertEquals(29.25, result.getItems().get(2).getItemFullPrice());
		assertEquals(23.65, result.getItems().get(3).getItemFullPrice());
	}

}
