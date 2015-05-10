package kata.vending;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoinsAccumulatedTest {

	@Test
	public void shouldStartWithNoCoins() {
		CoinsAccumulated accumulated = new CoinsAccumulated();
		assertEquals(0, accumulated.getCoinCount());
	}

	@Test
	public void shouldGiveCorrectTotal() {
		CoinsAccumulated accumulated = new CoinsAccumulated();
		accumulated = accumulated.addCoin(new MeasuredCoin(10));
		accumulated = accumulated.addCoin(new MeasuredCoin(25));
		assertEquals(35, accumulated.total());

	}

}