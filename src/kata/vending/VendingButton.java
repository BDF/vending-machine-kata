package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class VendingButton {
	private final VendingAction action;
	private final Product associatedProduct;

	public VendingButton(VendingAction action, Product associatedProduct) {
		this.action = action;
		this.associatedProduct = associatedProduct;
	}

	public VendingAction getVendingAction() {
		return action;
	}

	public Product getAssociatedProduct() {
		return associatedProduct;
	}

}
