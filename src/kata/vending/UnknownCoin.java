package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class UnknownCoin {
	private final double weight;
	private final double diameter;

	public UnknownCoin(double weight, double diameter) {
		this.weight = weight;
		this.diameter = diameter;
	}

	public double getWeight() {
		return weight;
	}

	public double getDiameter() {
		return diameter;
	}
}
