package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class VendingMachine {
	private final CoinWeights coinWeights;

	public VendingMachine(CoinWeights coinWeights) {
		this.coinWeights = coinWeights;
	}


	/**
	 * When the respective button is pressed and enough money has been inserted, the product is dispensed and the
	 * machine displays THANK YOU. If the display is checked again, it will display INSERT COINS and the current
	 * amount will be set to $0.00.
	 *
	 * If there is not enough money inserted then the machine displays PRICE and the
	 * price of the item and subsequent checks of the display will display either INSERT COINS or the current amount
	 * as appropriate.
	 */
	public VendingMachineStatus selectProduct(VendingMachineStatus vendingMachineStatus) {
		CoinsAccumulated coinsAccumulated = vendingMachineStatus.getCoinsAccumulated();
		int total = coinsAccumulated.total();
		VendingMachineStatus newMachineStatus;
		CoinsAccumulated newCoinsAccumulated;
		Product product = vendingMachineStatus.getProductSelected();

		if ("THANK YOU".equals(vendingMachineStatus.getMachineDisplay().getDisplay())) {
			newMachineStatus = new VendingMachineStatus();
		} else if (Product.NO_PRODUCT_SELECTED.equals(product)) {
			CoinDisplay coinDisplay = coinWeights.getCoinDisplay(vendingMachineStatus.getCoinsAccumulated().total());
			MachineDisplay machineDisplay = new MachineDisplay(coinDisplay.getDisplay());
			newMachineStatus = new VendingMachineStatus(coinsAccumulated, machineDisplay, vendingMachineStatus.getProductSelected());
		} else  if (total >= product.getCost()) {
			MachineDisplay machineDisplay = new MachineDisplay("THANK YOU");
			int coinReturn = total - product.getCost();
			CoinsAccumulated coinsReturned = new CoinsAccumulated();
			coinsReturned = coinsReturned.addCoin(new MeasuredCoin(coinReturn));
			newMachineStatus = new VendingMachineStatus(new CoinsAccumulated(), machineDisplay, coinsReturned);
		} else if (total > 0) {
			CoinDisplay coinDisplay = coinWeights.getCoinDisplay(product.getCost());
			MachineDisplay machineDisplay = new MachineDisplay("PRICE " + coinDisplay.getDisplay());
			newMachineStatus = new VendingMachineStatus(coinsAccumulated, machineDisplay, vendingMachineStatus.getProductSelected());
		} else {
			MachineDisplay machineDisplay  = vendingMachineStatus.getMachineDisplay();
			newCoinsAccumulated = vendingMachineStatus.getCoinsAccumulated();
			newMachineStatus = new VendingMachineStatus(newCoinsAccumulated, machineDisplay);
		}

		return newMachineStatus;
	}

	public VendingMachineStatus addCoin(VendingMachineStatus vendingMachineStatus, MeasuredCoin coin) {
		CoinsAccumulated allCoins = vendingMachineStatus.getCoinsAccumulated().addCoin(coin);
		return new VendingMachineStatus(allCoins, vendingMachineStatus.getMachineDisplay(), vendingMachineStatus.getProductSelected());
	}

	public CoinDisplay getCoinDisplay(VendingMachineStatus vendingMachineStatus) {
		CoinDisplay coinDisplay = coinWeights.getCoinDisplay(vendingMachineStatus.getCoinsAccumulated().total());
		return coinDisplay;
	}
}
