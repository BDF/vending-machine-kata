package kata.vending;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VendingMachineTest {
	private  VendingMachine vendingMachine;
	private  ProductSelection productSelection;
	@Before
	public void before() {
		CoinWeightFactory cwf = new CoinWeightFactory();
		vendingMachine = new VendingMachine(cwf.buildUsCoinSystem());
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

		VendingMachineStatus vms = vendingMachine.selectProduct(product, coinsAccumulated);
		assertEquals("THANK YOU", vms.getMachineDisplay().getDisplay());

		VendingMachineStatus vms2 = vendingMachine.checkDisplay(vms);
		assertEquals("INSERT COINS", vms2.getMachineDisplay().getDisplay());
		assertEquals(0, vms.getCoinsAccumulated().total());
		assertEquals("$0.00", vendingMachine.getCoinDisplay(vms2).getDisplay());
	}

	@Test
	public void whereGivenInsufficientFundsForProductSaysPrice() {
		Product product = productSelection.getProduct("candy");
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		VendingMachineStatus vms= vendingMachine.selectProduct(product, coinsAccumulated);
		assertEquals("PRICE", vms.getMachineDisplay().getDisplay());
	}



}