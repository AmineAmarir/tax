package com.creditagricole.tax.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.creditagricole.tax.enums.ProductKindEnum;
import com.creditagricole.tax.enums.ProductOriginEnum;
import com.creditagricole.tax.utils.TaxCalculationUtils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

/**
 * The Class Product.
 */
@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(builderMethodName = "newBuilder", toBuilder = true)
public class Product {

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	/** The description. */
	private String designation;

	/** The price without tax. */
	@NonNull
	@Column(nullable = false)
	private Double priceWithoutTax;

	/** The product kind. */
	@Column(nullable = false)
	private ProductKindEnum productKind;

	/** The product origin. */
	@Column(nullable = false)
	private ProductOriginEnum productOrigin;

	/** The value added tax. */
	@Transient
	@Builder.ObtainVia(method = "calculateValueAddedTax")
	private Double valueAddedTax;

	/** The full price. */
	@Transient
	@Builder.ObtainVia(method = "calculateFullPrice")
	private Double fullPrice;

	/**
	 * Calculate value added tax.
	 *
	 * @return the double
	 */
	private Double calculateValueAddedTax() {
		return (TaxCalculationUtils.calculateTax(this));
	}

	/**
	 * Calculate full price.
	 *
	 * @return the double
	 */
	private Double calculateFullPrice() {
		return (this.priceWithoutTax + TaxCalculationUtils.calculateTax(this));
	}

}
