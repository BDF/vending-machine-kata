package kata.vending;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bforeste on 5/10/15.
 *
 * @author bforeste
 */
public class ProductSelection {
	private final Map<String, Product> products = new HashMap<>();

	public ProductSelection() {
		products.put("cola", new Product("cola", 100));
		products.put("chips", new Product("chips", 50));
		products.put("candy", new Product("candy", 65));
	}

	public Product getProduct(String name) {
		Product product = products.get(name);
		Product retProduct;
		if (product == null) {
			retProduct = Product.NOT_FOUND;
		} else {
			retProduct = product;
		}
		return retProduct;
	}


}
