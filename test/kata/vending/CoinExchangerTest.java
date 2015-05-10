package kata.vending;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CoinExchangerTest {
	private CoinExchanger coinExchanger;
	private CoinsAccumulated coinsAccumulated;

	@Before
	public void before() {
		coinExchanger = new CoinExchanger();
		coinsAccumulated = new CoinsAccumulated();
	}

	@Test
	public void shouldReturn25() {
		coinsAccumulated = coinExchanger.getChange(coinsAccumulated, 25);
		assertEquals(1, coinsAccumulated.getCoinCount());
		assertEquals(25, coinsAccumulated.total());
	}

	@Test
	public void shouldReturn10() {
		coinsAccumulated = coinExchanger.getChange(coinsAccumulated, 10);
		assertEquals(1, coinsAccumulated.getCoinCount());
		assertEquals(10, coinsAccumulated.total());
	}

	@Test
	public void shouldReturn5() {
		coinsAccumulated = coinExchanger.getChange(coinsAccumulated, 5);
		assertEquals(1, coinsAccumulated.getCoinCount());
		assertEquals(5, coinsAccumulated.total());
	}

	@Test
	public void shouldReturnNickeAndDime() {
		coinsAccumulated = coinExchanger.getChange(coinsAccumulated, 15);
		assertEquals(2, coinsAccumulated.getCoinCount());
		assertEquals(15, coinsAccumulated.total());
		List<MeasuredCoin> coins = coinsAccumulated.getRawCoins();

		assertTrue("Should contain a nickle", coins.contains(new MeasuredCoin(5)));
		assertTrue("Should contain a dime", coins.contains(new MeasuredCoin(10)));
	}

	@Test
	public void shouldReturnQuartersOneDimeOneNickle() {
		coinsAccumulated = coinExchanger.getChange(coinsAccumulated, 90);
		assertEquals(5, coinsAccumulated.getCoinCount());
		assertEquals(90, coinsAccumulated.total());
		List<MeasuredCoin> coins = coinsAccumulated.getRawCoins();
		int quarterCount = 0;
		int dimeCount = 0;
		int nickleCount = 0;

		for (MeasuredCoin measuredCoin: coins) {
			switch (measuredCoin.getValue()) {
				case 5:
					nickleCount++;
					break;
				case 10:
					dimeCount++;
					break;
				case 25:
					quarterCount++;
					break;
				default:
					fail("Unknown coin");
			}
		}
		assertEquals("Should be 3 quarters", 3, quarterCount);
		assertEquals("Should be 1 dime", 1, dimeCount);
		assertEquals("Should be 1 nickle", 1, nickleCount);
	}


}