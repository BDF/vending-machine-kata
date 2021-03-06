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

	public MachineDisplay checkCoinStatus(CoinsAccumulated coinsAccumalated) {
		MachineDisplay machineDisplay;
		if (coinsAccumalated.getCoinCount() == 0) {
			machineDisplay = new MachineDisplay("INSERT COIN");
		} else {
			machineDisplay = null;
		}

		return machineDisplay;
	}


	public CoinsAccumulated insertCoin(UnknownCoin unknownCoin) {
		MeasuredCoin result = coinWeights.measureCoin(unknownCoin);
		CoinsAccumulated coinsAccumulated;
		if (CoinWeights.NOT_RECOGNIZED != result) {
			coinsAccumulated = null;
		} else {
			coinsAccumulated = new CoinsAccumulated();
		}

		return coinsAccumulated;
	}
}
