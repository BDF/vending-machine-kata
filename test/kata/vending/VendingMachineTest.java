package kata.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {
	private  VendingMachine vendingMachine;
	private  ProductSelection productSelection;
	@Before
	public void before() {
		vendingMachine = new VendingMachine();
		productSelection = new ProductSelection();
	}

	@Test
	public void whereGivenEnoughMoneyForProductSaysThankYou() {
		Product product = productSelection.getProduct("cola");
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);

		CoinDisplay display = vendingMachine.selectProduct(product, coinsAccumulated);
		assertEquals("THANK YOU", display.getDisplay());
	}

	@Test
	public void whereGivenInsufficientFundsForProductSaysPrice() {
		Product product = productSelection.getProduct("candy");
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		CoinDisplay display = vendingMachine.selectProduct(product, coinsAccumulated);
		assertEquals("PRICE", display.getDisplay());
	}

}