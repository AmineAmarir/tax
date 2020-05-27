package com.creditagricole.tax.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.creditagricole.tax.entities.Product;
import com.creditagricole.tax.enums.ProductKindEnum;
import com.creditagricole.tax.enums.ProductOriginEnum;
import com.creditagricole.tax.exceptions.IncorrectParametersException;

/**
 * The Class TaxCalculationUtilsTest.
 */
public class TaxCalculationUtilsTest {

	/**
	 * Test calcul tax incorrect parameter product.
	 */
	@DisplayName("Test calcul tax incorrect parameter product.")
	@Test
	public void testCalculTaxIncorrectParameterProduct() {
		final Product product = null;

		assertThrows(IncorrectParametersException.class, () -> {
			TaxCalculationUtils.calculateTax(product);
		});

	}

	/**
	 * Test calcul tax incorrect parameter product kind.
	 */
	@DisplayName("Test calcul tax incorrect parameter product kind.")
	@Test
	public void testCalculTaxIncorrectParameterProductKind() {
		final Product product = Product.newBuilder()
				.designation("My desgination")
				.priceWithoutTax(12.49)
				.productOrigin(ProductOriginEnum.LOCAL)
				.build();

		assertThrows(IncorrectParametersException.class, () -> {
			TaxCalculationUtils.calculateTax(product);
		});
	}

	/**
	 * Test calcul tax incorrect parameter product origin.
	 */
	@DisplayName("Test calcul tax incorrect parameter product origin.")
	@Test
	public void testCalculTaxIncorrectParameterProductOrigin() {
		final Product product = Product.newBuilder()
				.designation("My desgination")
				.priceWithoutTax(12.49)
				.productKind(ProductKindEnum.FIRST_NECESSITY)
				.build();

		assertThrows(IncorrectParametersException.class, () -> {
			TaxCalculationUtils.calculateTax(product);
		});
	}

	/**
	 * Test calcul tax local first necessity product.
	 */
	@DisplayName("Test calcul tax local first necessity product.")
	@Test
	public void testCalculTaxLocalFirstNecessityProduct() {
		final Product product = Product.newBuilder()
				.designation("My desgination")
				.priceWithoutTax(12.49)
				.productKind(ProductKindEnum.FIRST_NECESSITY)
				.productOrigin(ProductOriginEnum.LOCAL)
				.build();

		final Double tax = TaxCalculationUtils.calculateTax(product);

		assertEquals(0.0, tax);
	}

	/**
	 * Test calcul tax imported first necessity product.
	 */
	@DisplayName("Test calcul tax imported first necessity product.")
	@Test
	public void testCalculTaxImportedFirstNecessityProduct() {
		final Product product = Product.newBuilder()
				.designation("My desgination")
				.priceWithoutTax(12.49)
				.productKind(ProductKindEnum.FIRST_NECESSITY)
				.productOrigin(ProductOriginEnum.IMPORTED)
				.build();

		final Double tax = TaxCalculationUtils.calculateTax(product);

		assertEquals(0.6245, tax);
	}

	/**
	 * Test calcul tax local book product.
	 */
	@DisplayName("Test calcul tax local book product.")
	@Test
	public void testCalculTaxLocalBookProduct() {
		final Product product = Product.newBuilder()
				.designation("My desgination")
				.priceWithoutTax(12.49)
				.productKind(ProductKindEnum.BOOK)
				.productOrigin(ProductOriginEnum.LOCAL)
				.build();

		final Double tax = TaxCalculationUtils.calculateTax(product);

		assertEquals(1.249, tax);
	}

	/**
	 * Test calcul tax imported book product.
	 */
	@DisplayName("Test calcul tax imported book product.")
	@Test
	public void testCalculTaxImportedBookProduct() {
		final Product product = Product.newBuilder()
				.designation("My desgination")
				.priceWithoutTax(12.49)
				.productKind(ProductKindEnum.BOOK)
				.productOrigin(ProductOriginEnum.IMPORTED)
				.build();

		final Double tax = TaxCalculationUtils.calculateTax(product);

		assertEquals(1.8735000000000004, tax);
	}

	/**
	 * Test calcul tax local other product.
	 */
	@DisplayName("Test calcul tax local other product.")
	@Test
	public void testCalculTaxLocalOtherProduct() {
		final Product product = Product.newBuilder()
				.designation("My desgination")
				.priceWithoutTax(12.49)
				.productKind(ProductKindEnum.OTHER)
				.productOrigin(ProductOriginEnum.LOCAL)
				.build();

		final Double tax = TaxCalculationUtils.calculateTax(product);

		assertEquals(2.498, tax);
	}

	/**
	 * Test calcul tax imported other product.
	 */
	@DisplayName("Test calcul tax imported other product.")
	@Test
	public void testCalculTaxImportedOtherProduct() {
		final Product product = Product.newBuilder()
				.designation("My desgination")
				.priceWithoutTax(12.49)
				.productKind(ProductKindEnum.OTHER)
				.productOrigin(ProductOriginEnum.IMPORTED)
				.build();

		final Double tax = TaxCalculationUtils.calculateTax(product);

		assertEquals(3.1225, tax);
	}

	/**
	 * Test rounding to highest two decimal fifth case 1.
	 */
	@DisplayName("Test rounding to highest two decimal fifth case 1.")
	@Test
	public void testRoundingToHighestTwoDecimalFifthCase1() {
		final double roundedValue = TaxCalculationUtils.roundingToHighestTwoDecimalFifth(0.99);

		assertEquals(1.0, roundedValue);
	}

	/**
	 * Test rounding to highest two decimal fifth case 2.
	 */
	@DisplayName("Test rounding to highest two decimal fifth case 2.")
	@Test
	public void testRoundingToHighestTwoDecimalFifthCase2() {
		final double roundedValue = TaxCalculationUtils.roundingToHighestTwoDecimalFifth(1.0);

		assertEquals(1.0, roundedValue);
	}

	/**
	 * Test rounding to highest two decimal fifth case 3.
	 */
	@DisplayName("Test rounding to highest two decimal fifth case 3.")
	@Test
	public void testRoundingToHighestTwoDecimalFifthCase3() {
		final double roundedValue = TaxCalculationUtils.roundingToHighestTwoDecimalFifth(1.01);

		assertEquals(1.05, roundedValue);
	}

	/**
	 * Test rounding to highest two decimal fifth case 4.
	 */
	@DisplayName("Test rounding to highest two decimal fifth case 4.")
	@Test
	public void testRoundingToHighestTwoDecimalFifthCase4() {
		final double roundedValue = TaxCalculationUtils.roundingToHighestTwoDecimalFifth(1.02);

		assertEquals(1.05, roundedValue);
	}

}
