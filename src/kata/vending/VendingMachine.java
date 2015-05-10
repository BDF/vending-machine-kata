package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class VendingMachine {

	/**
	 * When the respective button is pressed and enough money has been inserted, the product is dispensed and the
	 * machine displays THANK YOU. If the display is checked again, it will display INSERT COINS and the current
	 * amount will be set to $0.00. If there is not enough money inserted then the machine displays PRICE and the
	 * price of the item and subsequent checks of the display will display either INSERT COINS or the current amount
	 * as appropriate.
	 */
	public CoinDisplay selectProduct(Product product, CoinsAccumulated coinsAccumulated) {
		int total = coinsAccumulated.total();

		CoinDisplay coinDisplay;
		if (total >= product.getCost()) {
			coinDisplay = new CoinDisplay("THANK YOU");
		} else {
			coinDisplay = new CoinDisplay("PRICE");
		}

		return coinDisplay;
	}

}
