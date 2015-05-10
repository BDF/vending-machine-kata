package kata.vending;

/**
 * Created by bforeste on 5/9/15.
 *
 * @author bforeste
 */
public class CACoinWeights implements  CoinWeights {


	@Override
	public MeasuredCoin measureCoin(UnknownCoin unknownCoin) {

		if (unknownCoin.getWeight()== 3.95d && unknownCoin.getDiameter()== 21.2d) {
			return new MeasuredCoin(5);
		}

		return NOT_RECOGNIZED;
	}
}
