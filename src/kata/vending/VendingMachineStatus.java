package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class VendingMachineStatus {
	private final CoinsAccumulated coinsAccumulated;
	private final MachineDisplay machineDisplay;
	private final VendingButton vendingButton;
	private final CoinsAccumulated coinsReturned;

	public VendingMachineStatus(CoinsAccumulated coinsAccumulated,
	                            MachineDisplay machineDisplay,
	                            CoinsAccumulated coinsReturned) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
		this.vendingButton = new VendingButton(VendingAction.NONE, Product.NO_PRODUCT_SELECTED);;
		this.coinsReturned = coinsReturned;
	}

	public VendingMachineStatus(CoinsAccumulated coinsAccumulated,
	                            MachineDisplay machineDisplay) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
		this.vendingButton = new VendingButton(VendingAction.NONE, Product.NO_PRODUCT_SELECTED);;
		this.coinsReturned = new CoinsAccumulated();
	}

	public VendingMachineStatus() {
		this.coinsAccumulated = new CoinsAccumulated();
		this.machineDisplay = new MachineDisplay("INSERT COINS");
		this.vendingButton = new VendingButton(VendingAction.NONE, Product.NO_PRODUCT_SELECTED);;
		this.coinsReturned = new CoinsAccumulated();
	}

	public VendingMachineStatus(
		CoinsAccumulated coinsAccumulated,
		MachineDisplay machineDisplay,
		Product productSelected) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
		this.vendingButton = new VendingButton(VendingAction.NONE, productSelected);
		this.coinsReturned = new CoinsAccumulated();
	}

	public VendingMachineStatus(
		CoinsAccumulated coinsAccumulated,
		MachineDisplay machineDisplay,
		VendingButton vendingButton) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
		this.vendingButton = vendingButton;
		this.coinsReturned = new CoinsAccumulated();
	}

	public CoinsAccumulated getCoinsAccumulated() {
		return coinsAccumulated;
	}

	public MachineDisplay getMachineDisplay() {
		return machineDisplay;
	}

	public VendingButton getVendingButton() {
		return vendingButton;
	}

	public CoinsAccumulated getCoinsReturned() {
		return coinsReturned;
	}
}
