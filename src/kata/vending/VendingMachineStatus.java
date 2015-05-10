package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class VendingMachineStatus {
	private final CoinsAccumulated coinsAccumulated;
	private final MachineDisplay machineDisplay;
	private final Product productSelected;
	private final CoinsAccumulated coinsReturned;

	public VendingMachineStatus(CoinsAccumulated coinsAccumulated,
	                            MachineDisplay machineDisplay,
	                            CoinsAccumulated coinsReturned) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
		this.productSelected = Product.NO_PRODUCT_SELECTED;
		this.coinsReturned = coinsReturned;
	}

	public VendingMachineStatus(CoinsAccumulated coinsAccumulated,
	                            MachineDisplay machineDisplay) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
		this.productSelected = Product.NO_PRODUCT_SELECTED;
		this.coinsReturned = new CoinsAccumulated();
	}

	public VendingMachineStatus() {
		this.coinsAccumulated = new CoinsAccumulated();
		this.machineDisplay = new MachineDisplay("INSERT COINS");
		this.productSelected = Product.NO_PRODUCT_SELECTED;
		this.coinsReturned = new CoinsAccumulated();
	}

	public VendingMachineStatus(
		CoinsAccumulated coinsAccumulated,
		MachineDisplay machineDisplay,
		Product productSelected) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
		this.productSelected = productSelected;
		this.coinsReturned = new CoinsAccumulated();
	}

	public CoinsAccumulated getCoinsAccumulated() {
		return coinsAccumulated;
	}

	public MachineDisplay getMachineDisplay() {
		return machineDisplay;
	}

	public Product getProductSelected() {
		return productSelected;
	}

	public CoinsAccumulated getCoinsReturned() {
		return coinsReturned;
	}
}
