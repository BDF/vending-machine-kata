package kata.vending;

/**
 * Created by bforeste on 5/9/15.
 *
 * @author bforeste
 */
public class CACoinWeights implements  CoinWeights {


	@Override
	public float measureCoin(double weight, double diameter) {

		if (weight == 3.95d && diameter == 21.2d) {
			return 0.5f;
		}

		return NOT_RECOGNIZED;
	}
}
