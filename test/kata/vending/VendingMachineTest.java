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
		vendingMachine = new VendingMachine(cwf.buildUsCoinSystem(), new CoinExchanger());
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
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), product);
		vms = vendingMachine.selectProduct(vms);
		assertEquals("THANK YOU", vms.getMachineDisplay().getDisplay());

		VendingMachineStatus vms2 = vendingMachine.selectProduct(vms);
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
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), product);
		vms= vendingMachine.selectProduct(vms);
		assertEquals("PRICE " + "$0.65" , vms.getMachineDisplay().getDisplay());
	}

	@Test
	public void whereIterativeAdditionsOfCoinsEventuallyGivenINSERTCOINS() {
		Product product = productSelection.getProduct("candy");
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), product);
		vms= vendingMachine.selectProduct(vms);
		assertEquals("PRICE " + "$0.65" , vms.getMachineDisplay().getDisplay());

		vms = vendingMachine.addCoin(vms, measuredCoin);
		vms= vendingMachine.selectProduct(vms);
		assertEquals("PRICE " + "$0.65" , vms.getMachineDisplay().getDisplay());

		vms = vendingMachine.addCoin(vms, measuredCoin);
		assertTrue(vms.getCoinsAccumulated().total() > 65);
		vms = vendingMachine.selectProduct(vms);
		assertEquals("THANK YOU" , vms.getMachineDisplay().getDisplay());

		vms = vendingMachine.selectProduct(vms);
		assertEquals("INSERT COINS" , vms.getMachineDisplay().getDisplay());
	}

	@Test
	public void whereNoProductSelectedGivesCurrentTotal() {
		Product product = Product.NO_PRODUCT_SELECTED;
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(5);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), product);
		vms= vendingMachine.selectProduct(vms);
		assertEquals("$0.05" , vms.getMachineDisplay().getDisplay());

		vms = vendingMachine.addCoin(vms, measuredCoin);
		vms= vendingMachine.selectProduct(vms);
		assertEquals("$0.10" , vms.getMachineDisplay().getDisplay());

		vms = new VendingMachineStatus(vms.getCoinsAccumulated(), vms.getMachineDisplay(), productSelection.getProduct("chips"));
		vms = vendingMachine.selectProduct(vms);
		assertEquals("PRICE $0.50" , vms.getMachineDisplay().getDisplay());
	}


	@Test
	public void whenCoinsAccumulatedExceedsProductCostReturnExtras() {
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		assertEquals(75, coinsAccumulated.total());
		Product product = productSelection.getProduct("candy");
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), product);
		vms = vendingMachine.selectProduct(vms);

		assertEquals(0, vms.getCoinsAccumulated().total());
		assertEquals("THANK YOU" , vms.getMachineDisplay().getDisplay());
		assertEquals(Product.NO_PRODUCT_SELECTED , vms.getProductSelected());
		assertEquals(10, vms.getCoinsReturned().total());
	}

	@Test
	public void whenCoinsAccumulatedExceedsByProductCostMultipleCoinsReturnExtras() {
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(new MeasuredCoin(5));

		assertEquals(105, coinsAccumulated.total());
		Product product = productSelection.getProduct("candy");
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), product);
		vms = vendingMachine.selectProduct(vms);

		assertEquals(0, vms.getCoinsAccumulated().total());
		assertEquals("THANK YOU" , vms.getMachineDisplay().getDisplay());
		assertEquals(Product.NO_PRODUCT_SELECTED , vms.getProductSelected());
		assertEquals(40, vms.getCoinsReturned().total());
		assertEquals(3, vms.getCoinsReturned().getCoinCount());
	}


}