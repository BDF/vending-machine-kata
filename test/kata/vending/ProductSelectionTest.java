package kata.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductSelectionTest {
	private  ProductSelection productSelection;

	@Before
	public void before() {
		productSelection = new ProductSelection();
	}

	@Test
	public void shouldFindAllConfiguredProducts() {
		Product cola = productSelection.getProduct("cola");
		assertNotNull(cola);
		assertEquals(new Product("cola", 100), cola);
		Product candy= productSelection.getProduct("candy");
		assertEquals(new Product("candy", 65), candy);
		Product chips = productSelection.getProduct("chips");
		assertEquals(new Product("chips", 50), chips);
	}

	@Test
	public void shouldReturnNotFoundForProductNotConfigured() {
		Product cricketBars = productSelection.getProduct("CricketBars");
		assertNotNull(cricketBars);
		assertEquals(Product.NOT_FOUND, cricketBars);
	}

}