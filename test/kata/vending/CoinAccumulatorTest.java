package kata.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoinAccumulatorTest {
	private CoinAccumulator coinAccumulator;

	@Before
	public void before() {
		coinAccumulator = new CoinAccumulator(new CACoinWeights());
	}

	@Test
	public void whenNoCoinsInsertedDisplayIsINSERTCOIN() {
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		CoinDisplay coinDisplay =  coinAccumulator.checkCoinStatus(coinsAccumulated);
		assertEquals("INSERT COIN", coinDisplay.getDisplay());
	}

	@Test
	public void whenInvalidCoinIsInsertedItIsReturned() {
		UnknownCoin unknownCoin = new UnknownCoin(0.0d, 0.0d);
		CoinsAccumulated accumulated = coinAccumulator.insertCoin(unknownCoin);
		assertEquals(0, accumulated.getCoinCount());

	}


}