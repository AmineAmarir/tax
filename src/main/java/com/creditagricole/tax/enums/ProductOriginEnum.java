package com.creditagricole.tax.enums;

/**
 * The Enum ProductOriginEnum.
 */
public enum ProductOriginEnum {

	/** The local. */
	LOCAL(0.0),
	/** The imported. */
	IMPORTED(0.05);

	/** The tax. */
	private double tax;

	/**
	 * Instantiates a new product origin enum.
	 *
	 * @param tax the tax
	 */
	private ProductOriginEnum(double tax) {
		this.tax = tax;
	}

	/**
	 * Gets the tax.
	 *
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

}
