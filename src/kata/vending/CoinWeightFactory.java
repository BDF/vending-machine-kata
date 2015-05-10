package kata.vending;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class CoinWeightFactory {

	Function<UnknownCoin, MeasuredCoin> buildPredicate(final double inWeight, final double inDiameter, final int inValue) {
		Function<UnknownCoin, MeasuredCoin> coinFunc = new Function<UnknownCoin, MeasuredCoin>() {
			final double weight = inWeight;
			final double diameter = inDiameter;
			final int value = inValue;
			@Override
			public MeasuredCoin apply(UnknownCoin unknownCoin) {
				final MeasuredCoin measuredCoin;
				if (unknownCoin.getWeight()== weight && unknownCoin.getDiameter()== diameter) {
					measuredCoin = new MeasuredCoin(value);
				} else {
					measuredCoin = CoinWeights.NOT_RECOGNIZED;
				}
				return measuredCoin;
			}
		};
		return coinFunc;
	}



	public CoinWeightsImpl buildUsCoinSystem() {
		List<Function<UnknownCoin, MeasuredCoin>> listOfFun = new ArrayList<>();
		// Why am I not making this a map of UnknownCoin to MeasuredCoin?   We'll see if I'm wrong.
		listOfFun = new ArrayList<Function<UnknownCoin, MeasuredCoin>>();
		listOfFun.add(buildPredicate(5.0d, 21.21d, 5));
		listOfFun.add(buildPredicate(2.268d, 17.92d, 10));
		listOfFun.add(buildPredicate(5.670d, 24.26d, 25));
		return new CoinWeightsImpl(listOfFun, Locale.US);
	}

	public CoinWeightsImpl buildCaCoinSystem() {
		List<Function<UnknownCoin, MeasuredCoin>> listOfFun = new ArrayList<>();
		// Why am I not making this a map of UnknownCoin to MeasuredCoin?   We'll see if I'm wrong.
		listOfFun = new ArrayList<Function<UnknownCoin, MeasuredCoin>>();
		listOfFun.add(buildPredicate(3.95d, 21.2d, 5));
		listOfFun.add(buildPredicate(1.75d, 18.03d, 10));
		listOfFun.add(buildPredicate(4.4d, 23.88d, 25));
		return new CoinWeightsImpl(listOfFun, Locale.CANADA);
	}


}
