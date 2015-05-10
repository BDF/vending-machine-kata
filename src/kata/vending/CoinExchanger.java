package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class CoinExchanger {

	public CoinsAccumulated getChange(CoinsAccumulated coinsAccumulated, int total) {

		if (total > 0) {
			MeasuredCoin coin;
			if (total >= 25) {
				coin = new MeasuredCoin(25);
			} else if (total >= 10) {
				coin = new MeasuredCoin(10);
			} else if (total >= 5) {
				coin = new MeasuredCoin(5);
			} else {
				// degenerate case that should be prevented
				coin = new MeasuredCoin(total);
			}
			total = total - coin.getValue();
			return getChange(coinsAccumulated.addCoin(coin), total);
		}
		return coinsAccumulated;
	}
}
