package kata.vending;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class CoinsAccumulated {
	private final List<MeasuredCoin> coins;

	public CoinsAccumulated() {
		coins = new ArrayList<>();
	}

	private CoinsAccumulated(List<MeasuredCoin> newCoinList) {
		this.coins = newCoinList;
	}

	public int getCoinCount() {
		return coins.size();
	}

	public int total() {
		return coins.stream().mapToInt(MeasuredCoin::getValue).sum();
	}

	public CoinsAccumulated addCoin(MeasuredCoin measuredCoin) {
		List<MeasuredCoin> newCoinList = new ArrayList<>(coins.size() + 1);
		newCoinList.addAll(coins);
		newCoinList.add(measuredCoin);
		return new CoinsAccumulated(newCoinList);
	}
}
