package kata.vending;

import org.junit.Before;
import org.junit.Test;

import kata.vending.CACoinWeights;
import kata.vending.CoinWeights;

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
		float value = coinWeights.measureCoin(weight, diameter);
		assertEquals(0.5, value, 0.001);
	}


}