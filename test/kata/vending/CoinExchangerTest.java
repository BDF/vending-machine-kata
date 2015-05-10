package kata.vending;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CoinExchangerTest {
	private CoinExchanger coinExchanger;
	private ChangeInCoins coinStatus;

	@Before
	public void before() {
		Map<MeasuredCoin, Integer> inCoinsToCount = new HashMap<>();
		inCoinsToCount.put(new MeasuredCoin(25), 5);
		inCoinsToCount.put(new MeasuredCoin(10), 5);
		inCoinsToCount.put(new MeasuredCoin(5), 5);
		coinExchanger = new CoinExchanger(inCoinsToCount);

	}

	@Test
	public void shouldReturn25() {
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();;
		coinStatus = coinExchanger.getChange(coinsAccumulated, 25);
		assertEquals(1, coinStatus.getChangeInCoins().getCoinCount());
		assertEquals(25, coinStatus.getChangeInCoins().total());
	}

	@Test
	public void shouldReturn10() {
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();;
		coinStatus = coinExchanger.getChange(coinsAccumulated, 10);
		assertEquals(1, coinStatus.getChangeInCoins().getCoinCount());
		assertEquals(10, coinStatus.getChangeInCoins().total());
	}

	@Test
	public void shouldReturn5() {
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();;
		coinStatus = coinExchanger.getChange(coinsAccumulated, 5);
		assertEquals(1, coinStatus.getChangeInCoins().getCoinCount());
		assertEquals(5, coinStatus.getChangeInCoins().total());
	}

	@Test
	public void shouldReturnNickeAndDime() {
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
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();;
		coinStatus = coinExchanger.getChange(coinsAccumulated, 90);
		assertEquals(5, coinStatus.getChangeInCoins().getCoinCount());
		assertEquals(90, coinStatus.getChangeInCoins().total());
		List<MeasuredCoin> coins = coinsAccumulated.getRawCoins();
		CoinCount coinCounts = coinExchanger.getCoinCounts(coinStatus.getChangeInCoins());

		assertEquals("Should be 3 quarters", 3, coinCounts.getQuarterCount());
		assertEquals("Should be 1 dime", 1, coinCounts.getDimeCount());
		assertEquals("Should be 1 nickle", 1, coinCounts.getNickleCount());
	}


}