package kata.vending;

import org.junit.Test;

import static org.junit.Assert.*;

public class CoinsAccumulatedTest {

	@Test
	public void shouldStartWithNoCoins() {
		CoinsAccumulated accumulated = new CoinsAccumulated();
		assertEquals(0, accumulated.getCoinCount());
	}

}