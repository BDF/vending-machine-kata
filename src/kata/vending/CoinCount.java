package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class CoinCount {
	private final int quarterCount;
	private final int dimeCount;
	private final int nickleCount;

	public CoinCount(int quarterCount, int dimeCount, int nickleCount) {
		this.quarterCount = quarterCount;
		this.dimeCount = dimeCount;
		this.nickleCount = nickleCount;
	}

	public int getQuarterCount() {
		return quarterCount;
	}

	public int getDimeCount() {
		return dimeCount;
	}

	public int getNickleCount() {
		return nickleCount;
	}
}
