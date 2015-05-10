package kata.vending;

/**
 * Created by bforeste on 5/9/15.
 *
 * @author bforeste
 */
public interface CoinWeights {
	public static float NOT_RECOGNIZED = Float.MIN_VALUE;

	float measureCoin(double weight, double diameter);
}
