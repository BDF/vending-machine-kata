package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class VendingMachineStatus {
	private final CoinsAccumulated coinsAccumulated;
	private final MachineDisplay machineDisplay;

	public VendingMachineStatus() {
		coinsAccumulated = new CoinsAccumulated();
		machineDisplay = new MachineDisplay("INSERT COINS");
	}

	public VendingMachineStatus(CoinsAccumulated coinsAccumulated, MachineDisplay machineDisplay) {
		this.coinsAccumulated = coinsAccumulated;
		this.machineDisplay = machineDisplay;
	}

	public CoinsAccumulated getCoinsAccumulated() {
		return coinsAccumulated;
	}

	public MachineDisplay getMachineDisplay() {
		return machineDisplay;
	}
}
