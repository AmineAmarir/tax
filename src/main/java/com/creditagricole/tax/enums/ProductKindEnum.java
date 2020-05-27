package com.creditagricole.tax.enums;

/**
 * The Enum ProductKindEnum.
 */
public enum ProductKindEnum {

	/** The first necessity. */
	FIRST_NECESSITY(0.0),
	/** The book. */
	BOOK(0.1),
	/** The default. */
	OTHER(0.2);

	/** The tax. */
	private double tax;

	/**
	 * Instantiates a new product kind enum.
	 *
	 * @param tax the tax
	 */
	private ProductKindEnum(double tax) {
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
