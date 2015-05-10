package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class CoinAccumulator {
	private final CoinWeights coinWeights;

	public CoinAccumulator(CoinWeights coinWeights) {
		this.coinWeights = coinWeights;
	}

	public CoinDisplay checkCoinStatus(CoinsAccumulated coinsAccumalated) {
		CoinDisplay coinDisplay;
		if (coinsAccumalated.getCoinCount() == 0) {
			coinDisplay = new CoinDisplay("INSERT COIN");
		} else {
			coinDisplay = null;
		}

		return coinDisplay;
	}




}
