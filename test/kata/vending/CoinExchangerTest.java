package kata.vending;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CoinExchangerTest {
	private ChangeInCoins coinStatus;

	private CoinExchanger buildCoinExchanger(int coinCount) {
		Map<MeasuredCoin, Integer> inCoinsToCount = new HashMap<>();
		inCoinsToCount.put(new MeasuredCoin(25), coinCount);
		inCoinsToCount.put(new MeasuredCoin(10), coinCount);
		inCoinsToCount.put(new MeasuredCoin(5), coinCount);
		return new CoinExchanger(inCoinsToCount);
	}

	@Test
	public void shouldReturn25() {
		CoinExchanger coinExchanger = buildCoinExchanger(5);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();;
		coinStatus = coinExchanger.getChange(coinsAccumulated, 25);
		assertEquals(1, coinStatus.getChangeInCoins().getCoinCount());
		assertEquals(25, coinStatus.getChangeInCoins().total());
	}

	@Test
	public void shouldReturn10() {
		CoinExchanger coinExchanger = buildCoinExchanger(5);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();;
		coinStatus = coinExchanger.getChange(coinsAccumulated, 10);
		assertEquals(1, coinStatus.getChangeInCoins().getCoinCount());
		assertEquals(10, coinStatus.getChangeInCoins().total());
	}

	@Test
	public void shouldReturn5() {
		CoinExchanger coinExchanger = buildCoinExchanger(5);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();;
		coinStatus = coinExchanger.getChange(coinsAccumulated, 5);
		assertEquals(1, coinStatus.getChangeInCoins().getCoinCount());
		assertEquals(5, coinStatus.getChangeInCoins().total());
	}

	@Test
	public void shouldReturnNickeAndDime() {
		CoinExchanger coinExchanger = buildCoinExchanger(5);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();;
		coinStatus = coinExchanger.getChange(coinsAccumulated, 15);
		assertEquals(2, coinStatus.getChangeInCoins().getCoinCount());
		assertEquals(15, coinStatus.getChangeInCoins().total());
		List<MeasuredCoin> coins = coinStatus.getChangeInCoins().getRawCoins();

		assertTrue("Should contain a nickle", coins.contains(new MeasuredCoin(5)));
		assertTrue("Should contain a dime", coins.contains(new MeasuredCoin(10)));
	}

	@Test
	public void shouldReturnQuartersOneDimeOneNickle() {
		CoinExchanger coinExchanger = buildCoinExchanger(5);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		coinStatus = coinExchanger.getChange(coinsAccumulated, 90);
		assertEquals(5, coinStatus.getChangeInCoins().getCoinCount());
		assertEquals(90, coinStatus.getChangeInCoins().total());
		List<MeasuredCoin> coins = coinsAccumulated.getRawCoins();
		CoinCount coinCounts = coinExchanger.getCoinCounts(coinStatus.getChangeInCoins());

		assertEquals("Should be 3 quarters", 3, coinCounts.getQuarterCount());
		assertEquals("Should be 1 dime", 1, coinCounts.getDimeCount());
		assertEquals("Should be 1 nickle", 1, coinCounts.getNickleCount());
	}

	@Test
	public void shouldReturnSuccessFalseAndOriginalCoins() {
		CoinExchanger coinExchanger = buildCoinExchanger(1);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();;
		coinsAccumulated = coinsAccumulated.addCoin(new MeasuredCoin(25));
		coinsAccumulated = coinsAccumulated.addCoin(new MeasuredCoin(25));
		coinsAccumulated = coinsAccumulated.addCoin(new MeasuredCoin(25));
		coinsAccumulated = coinsAccumulated.addCoin(new MeasuredCoin(5));
		coinsAccumulated = coinsAccumulated.addCoin(new MeasuredCoin(10));
		assertEquals(90, coinsAccumulated.total());
		coinStatus = coinExchanger.getChange(coinsAccumulated, 90);
		assertFalse(coinStatus.isSuccessCoinReturn());
		assertSame(coinsAccumulated, coinStatus.getChangeInCoins());
	}


}