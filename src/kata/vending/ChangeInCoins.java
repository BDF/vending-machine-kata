package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class ChangeInCoins {
	private final CoinsAccumulated changeInCoins;
	private final boolean successCoinReturn;

	public ChangeInCoins(CoinsAccumulated changeInCoins, boolean successCoinReturn) {
		this.changeInCoins = changeInCoins;
		this.successCoinReturn = successCoinReturn;
	}

	public CoinsAccumulated getChangeInCoins() {
		return changeInCoins;
	}

	public boolean isSuccessCoinReturn() {
		return successCoinReturn;
	}
}
