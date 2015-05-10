package kata.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CACoinWeightsTest {

	private CoinWeights coinWeights;

	@Before
	public void before() {
		coinWeights = new CACoinWeights();

	}

	@Test
	public void shouldBeValidCA2000Nickle() {
		double weight = 3.95d;
		double diameter = 21.2d;
		UnknownCoin uc = new UnknownCoin(weight, diameter);
		MeasuredCoin value = coinWeights.measureCoin(uc);
		assertEquals("Should have a value of 5", 5, value.getValue());
	}

	@Test
	public void shouldNotBeValidCANickel() {
		double weight = 3.8d;
		double diameter = 21.2d;
		UnknownCoin uc = new UnknownCoin(weight, diameter);
		MeasuredCoin value = coinWeights.measureCoin(uc);
		assertEquals(CoinWeights.NOT_RECOGNIZED, value);
	}

	@Test
	public void shouldBeValid2000CADime() {
		double weight = 1.75d;
		double diameter = 18.03d;
		UnknownCoin uc = new UnknownCoin(weight, diameter);
		MeasuredCoin value = coinWeights.measureCoin(uc);
		assertEquals("Should have a value of 10", 10, value.getValue());
	}

	@Test
	public void shouldBeValid2001CAQuarter() {
		double weight = 4.4d;
		double diameter = 23.88d;
		UnknownCoin uc = new UnknownCoin(weight, diameter);
		MeasuredCoin value = coinWeights.measureCoin(uc);
		assertEquals("Should have a value of 25", 25, value.getValue());
	}

}