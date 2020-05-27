package com.creditagricole.tax.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.creditagricole.tax.utils.TaxCalculationUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * The Class Item.
 */
@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public class Item {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/** The product. */
	@NonNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id", nullable = false)
	private Product product;

	/** The quantity. */
	private int quantity;

	/** The item price without tax. */
	@Transient
	@Builder.ObtainVia(method = "calculateItemPriceWithoutTax")
	private Double itemPriceWithoutTax;

	/** The item value added tax. */
	@Transient
	@Builder.ObtainVia(method = "calculateItemValueAddedTax")
	private Double itemValueAddedTax;

	/** The full price. */
	@Transient
	@Builder.ObtainVia(method = "calculateItemFullPrice")
	private Double itemFullPrice;

	/**
	 * Calculate item price without tax.
	 *
	 * @return the double
	 */
	private Double calculateItemPriceWithoutTax() {
		return this.quantity * this.product.getPriceWithoutTax();
	}

	/**
	 * Calculate item value added tax.
	 *
	 * @return the double
	 */
	private Double calculateItemValueAddedTax() {
		return TaxCalculationUtils.roundingToHighestTwoDecimalFifth(this.quantity * this.product.getValueAddedTax());
	}

	/**
	 * Calculate item full price.
	 *
	 * @return the double
	 */
	private Double calculateItemFullPrice() {
		return TaxCalculationUtils.roundingToHighestTwoDecimalFifth(this.quantity * this.product.getFullPrice());
	}

}
