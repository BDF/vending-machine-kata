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
	 * amount will be set to $0.00. If there is not enough money inserted then the machine displays PRICE and the
	 * price of the item and subsequent checks of the display will display either INSERT COINS or the current amount
	 * as appropriate.
	 */
	public VendingMachineStatus selectProduct(Product product, CoinsAccumulated coinsAccumulated) {
		int total = coinsAccumulated.total();
		VendingMachineStatus vendingMachineStatus;
		MachineDisplay machineDisplay;
		if (total >= product.getCost()) {
			machineDisplay = new MachineDisplay("THANK YOU");
			vendingMachineStatus = new VendingMachineStatus(new CoinsAccumulated(), machineDisplay);
		} else {
			machineDisplay = new MachineDisplay("PRICE");
			vendingMachineStatus = new VendingMachineStatus(coinsAccumulated, machineDisplay);
		}

		return vendingMachineStatus;
	}

	public  VendingMachineStatus checkDisplay(VendingMachineStatus vendingMachineStatus) {
		MachineDisplay newMachineDisplay;
		CoinsAccumulated coinsAccumulated;
		if ("THANK YOU".equals(vendingMachineStatus.getMachineDisplay().getDisplay())) {
			newMachineDisplay = new MachineDisplay("INSERT COINS");
			coinsAccumulated = new CoinsAccumulated();
		} else {
			newMachineDisplay = vendingMachineStatus.getMachineDisplay();
			coinsAccumulated = vendingMachineStatus.getCoinsAccumulated();
		}

		return new VendingMachineStatus(coinsAccumulated, newMachineDisplay);
	}

	public CoinDisplay getCoinDisplay(VendingMachineStatus vendingMachineStatus) {
		CoinDisplay coinDisplay = coinWeights.getCoinDisplay(vendingMachineStatus.getCoinsAccumulated().total());
		return coinDisplay;
	}
}
