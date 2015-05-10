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

	public VendingMachineStatus(CoinsAccumulated coinsAccumulated,
	                            MachineDisplay machineDisplay) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
		this.productSelected = Product.NO_PRODUCT_SELECTED;
	}

	public VendingMachineStatus() {
		coinsAccumulated = new CoinsAccumulated();
		machineDisplay = new MachineDisplay("INSERT COINS");
		productSelected = Product.NO_PRODUCT_SELECTED;
	}

	public VendingMachineStatus(
		CoinsAccumulated coinsAccumulated,
		MachineDisplay machineDisplay,
		Product productSelected) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
		this.productSelected = productSelected;
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
}
