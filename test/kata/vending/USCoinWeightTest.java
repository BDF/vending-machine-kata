package kata.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class USCoinWeightTest {

	private CoinWeights coinWeights;

	@Before
	public void before() {
		CoinWeightFactory cwf = new CoinWeightFactory();
		coinWeights = cwf.buildUsCoinSystem();
	}

	@Test
	public void shouldBeValidUSNickle() {
		double weight = 5.0d;
		double diameter = 21.21d;
		UnknownCoin uc = new UnknownCoin(weight, diameter);
		MeasuredCoin value = coinWeights.measureCoin(uc);
		assertEquals("Should have a value of 5", 5, value.getValue());
	}

	@Test
	public void shouldNotBeValidUSNickel() {
		double weight = 3.8d;
		double diameter = 21.2d;
		UnknownCoin uc = new UnknownCoin(weight, diameter);
		MeasuredCoin value = coinWeights.measureCoin(uc);
		assertEquals(CoinWeights.NOT_RECOGNIZED, value);
	}

	@Test
	public void shouldBeValidUSDime() {
		double weight = 2.268d;
		double diameter = 17.92d;
		UnknownCoin uc = new UnknownCoin(weight, diameter);
		MeasuredCoin value = coinWeights.measureCoin(uc);
		assertEquals("Should have a value of 10", 10, value.getValue());
	}

	@Test
	public void shouldBeValidUSQuarter() {
		double weight = 5.670d;
		double diameter = 24.26d;
		UnknownCoin uc = new UnknownCoin(weight, diameter);
		MeasuredCoin value = coinWeights.measureCoin(uc);
		assertEquals("Should have a value of 25", 25, value.getValue());
	}
}
