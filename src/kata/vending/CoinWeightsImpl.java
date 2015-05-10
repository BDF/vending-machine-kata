package kata.vending;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.function.Function;

/**
 * Created by bforeste on 5/9/15.
 *
 * @author bforeste
 */
public class CoinWeightsImpl implements  CoinWeights {
	private final List<Function<UnknownCoin, MeasuredCoin>> listOfFun;
	private final Locale locale;

	// I would prefer to give the values to this rather than hard-coding; not part of the kata.
	public CoinWeightsImpl(List<Function<UnknownCoin, MeasuredCoin>> unknownCoinToKnownList, Locale locale) {
		this.listOfFun = unknownCoinToKnownList;
		this.locale = locale;
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

	@Override
	public CoinDisplay getCoinDisplay(int total) {
		NumberFormat n = NumberFormat.getCurrencyInstance(locale);
		String strValue = n.format(total / 100.0);
		return new CoinDisplay(strValue);
	}
}
