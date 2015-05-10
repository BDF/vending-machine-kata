package kata.vending;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class MeasuredCoin {
	private final int value;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		MeasuredCoin that = (MeasuredCoin) o;

		if (value != that.value) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return value;
	}

	public MeasuredCoin(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}


}
