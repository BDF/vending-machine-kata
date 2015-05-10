package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class VendingMachine {
	private final CoinWeights coinWeights;
	private final CoinExchanger coinExchanger;

	public VendingMachine(CoinWeights coinWeights, CoinExchanger coinExchanger) {
		this.coinWeights = coinWeights;
		this.coinExchanger = coinExchanger;
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
		Product product = vendingMachineStatus.getVendingButton().getAssociatedProduct();

		if ("THANK YOU".equals(vendingMachineStatus.getMachineDisplay().getDisplay())) {
			newMachineStatus = new VendingMachineStatus();
		} else if (Product.NO_PRODUCT_SELECTED.equals(product)) {
			CoinDisplay coinDisplay = coinWeights.getCoinDisplay(vendingMachineStatus.getCoinsAccumulated().total());
			MachineDisplay machineDisplay = new MachineDisplay(coinDisplay.getDisplay());
			newMachineStatus = new VendingMachineStatus(coinsAccumulated, machineDisplay, vendingMachineStatus.getVendingButton());
		} else  if (total >= product.getCost()) {
			MachineDisplay machineDisplay = new MachineDisplay("THANK YOU");
			int changeReturned = total - product.getCost();
			CoinsAccumulated coinsReturned = coinExchanger.getChange(new CoinsAccumulated(), changeReturned );
			newMachineStatus = new VendingMachineStatus(new CoinsAccumulated(), machineDisplay, coinsReturned);
		} else if (total > 0) {
			CoinDisplay coinDisplay = coinWeights.getCoinDisplay(product.getCost());
			MachineDisplay machineDisplay = new MachineDisplay("PRICE " + coinDisplay.getDisplay());
			newMachineStatus = new VendingMachineStatus(coinsAccumulated, machineDisplay, vendingMachineStatus.getVendingButton());
		} else {
			MachineDisplay machineDisplay  = vendingMachineStatus.getMachineDisplay();
			newCoinsAccumulated = vendingMachineStatus.getCoinsAccumulated();
			newMachineStatus = new VendingMachineStatus(newCoinsAccumulated, machineDisplay);
		}

		return newMachineStatus;
	}

	public VendingMachineStatus addCoin(VendingMachineStatus vendingMachineStatus, MeasuredCoin coin) {
		CoinsAccumulated allCoins = vendingMachineStatus.getCoinsAccumulated().addCoin(coin);
		return new VendingMachineStatus(allCoins, vendingMachineStatus.getMachineDisplay(), vendingMachineStatus.getVendingButton());
	}

	public CoinDisplay getCoinDisplay(VendingMachineStatus vendingMachineStatus) {
		CoinDisplay coinDisplay = coinWeights.getCoinDisplay(vendingMachineStatus.getCoinsAccumulated().total());
		return coinDisplay;
	}

	private VendingMachineStatus returnCoins(VendingMachineStatus vendingMachineStatus) {
		CoinsAccumulated coinsAccumulated = vendingMachineStatus.getCoinsAccumulated();
		CoinsAccumulated coinsReturned = coinsAccumulated;
		return new VendingMachineStatus(new CoinsAccumulated(), new MachineDisplay("INSERT COIN"), coinsReturned);
	}


	public VendingMachineStatus selectButton(VendingMachineStatus vendingMachineStatus) {
		VendingAction vendingAction = vendingMachineStatus.getVendingButton().getVendingAction();
		VendingMachineStatus newVmsState;
		switch (vendingAction) {
			case VEND:
				newVmsState = selectProduct(vendingMachineStatus);
			break;
			case COIN_RETURN:
				newVmsState = returnCoins(vendingMachineStatus);
				break;
			case NONE:
				newVmsState = vendingMachineStatus;
				break;
			default:
				throw new RuntimeException("Machine temporarily out of order.");
		}

		return newVmsState;
	}
}
