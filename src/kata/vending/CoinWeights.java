package kata.vending;

/**
 * Created by bforeste on 5/9/15.
 *
 * @author bforeste
 */
public interface CoinWeights {
	public static MeasuredCoin NOT_RECOGNIZED = new MeasuredCoin(Integer.MIN_VALUE);

	MeasuredCoin measureCoin(UnknownCoin unknownCoin);

	CoinDisplay getCoinDisplay(int total);

}
