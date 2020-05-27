package com.creditagricole.tax.utils;

import com.creditagricole.tax.entities.Product;
import com.creditagricole.tax.exceptions.IncorrectParametersException;

/**
 * The Class TaxCalculationUtils.
 */
public class TaxCalculationUtils {

	/**
	 * Instantiates a new tax calculation utils.
	 */
	private TaxCalculationUtils() {
	}

	/**
	 * Calculate tax.
	 *
	 * @param product the product
	 * @return the double
	 */
	public static Double calculateTax(final Product product) {
		if (product == null || product.getProductKind() == null || product.getProductOrigin() == null) {
			throw new IncorrectParametersException();
		}
		return (product.getPriceWithoutTax()
				* (product.getProductKind().getTax() + product.getProductOrigin().getTax()));
	}

	/**
	 * Rounding to highest two decimal fifth.
	 *
	 * @param valueToRound the value to round
	 * @return the double
	 */
	public static double roundingToHighestTwoDecimalFifth(final double valueToRound) {
		return (Math.ceil(valueToRound * 20) / 20.0);
	}

}
