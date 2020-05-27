package com.creditagricole.tax.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The Class Order.
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public class Order {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/** The items. */
	@OneToMany(cascade = CascadeType.ALL)
	@Builder.Default
	private List<Item> items = new ArrayList<>();

	/** The order price without tax. */
	@Transient
	@Builder.ObtainVia(method = "calculateOrderPriceWithoutTax")
	private Double orderPriceWithoutTax;

	/** The order value added tax. */
	@Transient
	@Builder.ObtainVia(method = "calculateOrderValueAddedTax")
	private Double orderValueAddedTax;

	/** The order full price. */
	@Transient
	@Builder.ObtainVia(method = "calculateOrderFullPrice")
	private Double orderFullPrice;

	/**
	 * Calculate order price without tax.
	 *
	 * @return the double
	 */
	private Double calculateOrderPriceWithoutTax() {
		return this.items.stream().mapToDouble(item -> item.getItemPriceWithoutTax()).sum();
	}

	/**
	 * Calculate order value added tax.
	 *
	 * @return the double
	 */
	private Double calculateOrderValueAddedTax() {
		return this.items.stream().mapToDouble(item -> item.getItemValueAddedTax()).sum();
	}

	/**
	 * Calculate order full price.
	 *
	 * @return the double
	 */
	private Double calculateOrderFullPrice() {
		return this.items.stream().mapToDouble(item -> item.getItemFullPrice()).sum();
	}

}
