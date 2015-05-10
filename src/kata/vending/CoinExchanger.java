package kata.vending;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class CoinExchanger {
	private final Map<MeasuredCoin, Integer> coinsToCount;

	public CoinExchanger(Map<MeasuredCoin, Integer> inCoinsToCount) {
		coinsToCount = new ConcurrentHashMap<>(inCoinsToCount.size());
		coinsToCount.putAll(inCoinsToCount);
	}

	public ChangeInCoins getChange(CoinsAccumulated original, int total) {
		CoinsAccumulated changeInCoins = recurChange(new CoinsAccumulated(), total);
		CoinCount coinCount = getCoinCounts(changeInCoins);

		synchronized (coinsToCount) {
			ChangeInCoins coinsReturned;
			int availableQuarters = coinsToCount.get(new MeasuredCoin(25)).intValue();
			int availableDimes = coinsToCount.get(new MeasuredCoin(10)).intValue();
			int availableNickles = coinsToCount.get(new MeasuredCoin(5)).intValue();
			boolean enoughChange =
				availableQuarters >= coinCount.getQuarterCount() &&
					availableDimes >= coinCount.getDimeCount() &&
					availableNickles >= coinCount.getNickleCount();

			if (enoughChange) {
				coinsToCount.put(new MeasuredCoin(25), availableQuarters - coinCount.getQuarterCount());
				coinsToCount.put(new MeasuredCoin(10), availableDimes - coinCount.getDimeCount());
				coinsToCount.put(new MeasuredCoin(5), availableNickles - coinCount.getNickleCount());
				coinsReturned = new ChangeInCoins(changeInCoins, true);
			} else {
				coinsReturned = new ChangeInCoins(original, false);
			}
			return coinsReturned;
		}
	}

	private CoinsAccumulated recurChange(CoinsAccumulated coinsAccumulated, int total) {
		if (total > 0) {
			MeasuredCoin coin;
			if (total >= 25) {
				coin = new MeasuredCoin(25);
			} else if (total >= 10) {
				coin = new MeasuredCoin(10);
			} else if (total >= 5) {
				coin = new MeasuredCoin(5);
			} else {
				// degenerate case that should be prevented
				coin = new MeasuredCoin(total);
			}
			total = total - coin.getValue();
			return recurChange(coinsAccumulated.addCoin(coin), total);
		}
		return coinsAccumulated;

	}

	public CoinCount getCoinCounts(CoinsAccumulated coins) {
		int quarterCount = 0;
		int dimeCount = 0;
		int nickleCount = 0;

		for (MeasuredCoin measuredCoin : coins.getRawCoins()) {
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
			}
		}
		return new CoinCount(quarterCount, dimeCount, nickleCount);
	}


}
