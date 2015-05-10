package kata.vending;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class CoinsAccumulated {
	private final List<MeasuredCoin> coins = new ArrayList<>();

	public int getCoinCount() {
		return coins.size();
	}

}
