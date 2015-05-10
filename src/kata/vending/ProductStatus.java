package kata.vending;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class ProductStatus {
	private final Map<Product, Integer> productToCounts;

	public ProductStatus(Map<Product, Integer> inProductToCounts) {
		this.productToCounts = new ConcurrentHashMap<>(inProductToCounts.size());
		this.productToCounts.putAll(inProductToCounts);
	}

	public boolean retrieveProduct(Product product) {
		Integer count = productToCounts.get(product);
		final boolean isAvailable;
		if (count != null && count.intValue() > 0) {
			productToCounts.put(product, count.intValue() -1 );
			isAvailable = true;
		} else {
			isAvailable = false;
		}
		return isAvailable;
	}

}
