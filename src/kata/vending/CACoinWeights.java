package kata.vending;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * Created by bforeste on 5/9/15.
 *
 * @author bforeste
 */
public class CACoinWeights implements  CoinWeights {
	private List<Function<UnknownCoin, MeasuredCoin>> listOfFun;

	// I would prefer to give the values to this rather than hard-coding; not part of the kata.
	public CACoinWeights() {
		// Why am I not making this a map of UnknownCoin to MeasuredCoin?   We'll see if I'm wrong.
		listOfFun = new ArrayList<Function<UnknownCoin, MeasuredCoin>>();
		listOfFun.add(buildPredicate(3.95d, 21.2d, 5));
		listOfFun.add(buildPredicate(1.75d, 18.03d, 10));
	}

	private Function<UnknownCoin, MeasuredCoin> buildPredicate(final double inWeight, final double inDiameter, final int inValue) {
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
					measuredCoin = NOT_RECOGNIZED;
				}
				return measuredCoin;
			}
		};
		return coinFunc;
	}


	@Override
	public MeasuredCoin measureCoin(final UnknownCoin unknownCoin) {
		Optional<Function<UnknownCoin, MeasuredCoin>> results = listOfFun.stream().filter(p -> p.apply(unknownCoin) != NOT_RECOGNIZED).findFirst();
		// hrm; wanted the transformed value not the original list value; solve later.
		MeasuredCoin measuredCoin;
		if (results.isPresent()) {
			measuredCoin = results.get().apply(unknownCoin);
		} else {
			measuredCoin = NOT_RECOGNIZED;
		}

		return measuredCoin;
	}
}
