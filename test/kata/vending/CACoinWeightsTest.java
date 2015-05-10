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

}