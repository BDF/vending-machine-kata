package kata.vending;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class VendingMachineTest {
	private  ProductSelection productSelection;
	private Map<Product, Integer> productCounts;

	@Before
	public void before() {
		productSelection = new ProductSelection();
	}

	public VendingMachine buildVendingMaching(int productCounts) {
		CoinWeightFactory cwf = new CoinWeightFactory();
		Map<Product, Integer> productToCounts = getProductCounts(productSelection, productCounts);
		ProductStatus products = new ProductStatus(productToCounts);
		Map<MeasuredCoin, Integer> inCoinsToCount = new HashMap<>();
		inCoinsToCount.put(new MeasuredCoin(25), 5);
		inCoinsToCount.put(new MeasuredCoin(10), 5);
		inCoinsToCount.put(new MeasuredCoin(5), 5);

		VendingMachine vendingMachine = new VendingMachine(cwf.buildUsCoinSystem(), new CoinExchanger(inCoinsToCount), products);
		return vendingMachine;
	}

	@Test
	public void whereGivenEnoughMoneyForProductSaysThankYou() {
		VendingMachine vendingMachine = buildVendingMaching(2);
		Product product = productSelection.getProduct("cola");
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		VendingButton vendingButton = new VendingButton(VendingAction.VEND, product);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), vendingButton);
		vms = vendingMachine.selectButton(vms);
		assertEquals("THANK YOU", vms.getMachineDisplay().getDisplay());

		VendingMachineStatus vms2 = vendingMachine.selectButton(vms);
		assertEquals("INSERT COINS", vms2.getMachineDisplay().getDisplay());
		assertEquals(0, vms.getCoinsAccumulated().total());
		assertEquals("$0.00", vendingMachine.getCoinDisplay(vms2).getDisplay());
	}

	@Test
	public void whereGivenInsufficientFundsForProductSaysPrice() {
		VendingMachine vendingMachine = buildVendingMaching(2);
		Product product = productSelection.getProduct("candy");
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		VendingButton vendingButton = new VendingButton(VendingAction.VEND, product);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), vendingButton);
		vms= vendingMachine.selectButton(vms);
		assertEquals("PRICE " + "$0.65" , vms.getMachineDisplay().getDisplay());
	}

	@Test
	public void whereIterativeAdditionsOfCoinsEventuallyGivenINSERTCOINS() {
		VendingMachine vendingMachine = buildVendingMaching(3);
		Product product = productSelection.getProduct("candy");
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		VendingButton vendingButton = new VendingButton(VendingAction.VEND, product);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), vendingButton);
		vms= vendingMachine.selectButton(vms);
		assertEquals("PRICE " + "$0.65" , vms.getMachineDisplay().getDisplay());

		vms = vendingMachine.addCoin(vms, measuredCoin);
		vms= vendingMachine.selectButton(vms);
		assertEquals("PRICE " + "$0.65" , vms.getMachineDisplay().getDisplay());

		vms = vendingMachine.addCoin(vms, measuredCoin);
		assertTrue(vms.getCoinsAccumulated().total() > 65);
		vms= vendingMachine.selectButton(vms);
		assertEquals("THANK YOU" , vms.getMachineDisplay().getDisplay());

		vms= vendingMachine.selectButton(vms);
		assertEquals("INSERT COINS" , vms.getMachineDisplay().getDisplay());
	}

	@Test
	public void whereNoProductSelectedGivesCurrentTotal() {
		VendingMachine vendingMachine = buildVendingMaching(2);
		Product product = Product.NO_PRODUCT_SELECTED;
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(5);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		VendingButton vendingButton = new VendingButton(VendingAction.VEND, product);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), vendingButton);
		vms= vendingMachine.selectButton(vms);
		assertEquals("$0.05" , vms.getMachineDisplay().getDisplay());

		vms = vendingMachine.addCoin(vms, measuredCoin);
		vms= vendingMachine.selectButton(vms);
		assertEquals("$0.10" , vms.getMachineDisplay().getDisplay());

		VendingButton doVend = new VendingButton(VendingAction.VEND, productSelection.getProduct("chips"));
		vms = new VendingMachineStatus(vms.getCoinsAccumulated(), vms.getMachineDisplay(), doVend);
		vms = vendingMachine.selectButton(vms);
		assertEquals("PRICE $0.50" , vms.getMachineDisplay().getDisplay());
	}


	@Test
	public void whenCoinsAccumulatedExceedsProductCostReturnExtras() {
		VendingMachine vendingMachine = buildVendingMaching(2);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		assertEquals(75, coinsAccumulated.total());
		Product product = productSelection.getProduct("candy");
		VendingButton vendingButton = new VendingButton(VendingAction.VEND, product);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), vendingButton);
		vms = vendingMachine.selectButton(vms);

		assertEquals(0, vms.getCoinsAccumulated().total());
		assertEquals("THANK YOU" , vms.getMachineDisplay().getDisplay());
		assertEquals(Product.NO_PRODUCT_SELECTED , vms.getVendingButton().getAssociatedProduct());
		assertEquals(10, vms.getCoinsReturned().total());
	}

	@Test
	public void whenCoinsAccumulatedExceedsByProductCostMultipleCoinsReturnExtras() {
		VendingMachine vendingMachine = buildVendingMaching(2);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(new MeasuredCoin(5));

		assertEquals(105, coinsAccumulated.total());
		Product product = productSelection.getProduct("candy");
		VendingButton vendingButton = new VendingButton(VendingAction.VEND, product);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), vendingButton);
		vms = vendingMachine.selectButton(vms);

		assertEquals(0, vms.getCoinsAccumulated().total());
		assertEquals("THANK YOU" , vms.getMachineDisplay().getDisplay());
		assertEquals(Product.NO_PRODUCT_SELECTED , vms.getVendingButton().getAssociatedProduct());
		assertEquals(40, vms.getCoinsReturned().total());
		assertEquals(3, vms.getCoinsReturned().getCoinCount());
	}

	@Test
	public void whenReturnCoinsSelectedDisplayShouldBeINSERTCOINSAndCoinsAreReturned() {
		VendingMachine vendingMachine = buildVendingMaching(2);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		VendingButton vendingButton = new VendingButton(VendingAction.COIN_RETURN, Product.NO_PRODUCT_SELECTED);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("INSERT COINS"), vendingButton);

		VendingMachineStatus newState = vendingMachine.selectButton(vms);

		assertEquals(50, newState.getCoinsReturned().total());
		assertEquals(2, newState.getCoinsReturned().getCoinCount());

		assertEquals(0, newState.getCoinsAccumulated().total());
		assertEquals(0, newState.getCoinsAccumulated().getCoinCount());
		assertEquals("INSERT COIN", newState.getMachineDisplay().getDisplay());
	}

	@Test
	public void whenProductIsOutOfStockDisplaySoldOut() {
		VendingMachine vendingMachine = buildVendingMaching(1);
		CoinsAccumulated coinsAccumulated = new CoinsAccumulated();
		MeasuredCoin measuredCoin = new MeasuredCoin(25);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		coinsAccumulated = coinsAccumulated.addCoin(measuredCoin);
		Product chips = productSelection.getProduct("chips");
		VendingButton vendingButton = new VendingButton(VendingAction.VEND, chips);
		VendingMachineStatus vms = new VendingMachineStatus(coinsAccumulated, new MachineDisplay("$0.50"), vendingButton);
		vendingMachine.selectButton(vms);

		// Select same product a second time;
		VendingMachineStatus outOfProduct = vendingMachine.selectButton(vms);


		/**
		As a customer
		I want to be told when the item I have selected is not available
		So that I can select another item

		 When the item selected by the customer is out of stock, the machine displays SOLD OUT. If the display is
		 checked again, it will display the amount of money remaining in the machine or INSERT COIN if there is no
		 money in the machine.
		*/
		assertEquals("SOLD OUT", outOfProduct.getMachineDisplay().getDisplay());
		assertEquals(50, outOfProduct.getCoinsAccumulated().total());
		assertEquals(2, outOfProduct.getCoinsAccumulated().getCoinCount());
		assertEquals(Product.NO_PRODUCT_SELECTED, outOfProduct.getVendingButton().getAssociatedProduct());
		assertEquals(VendingAction.NONE, outOfProduct.getVendingButton().getVendingAction());
		assertEquals(0, outOfProduct.getCoinsReturned().total());
		assertEquals(0, outOfProduct.getCoinsReturned().getCoinCount());


		VendingMachineStatus checkCurrentState = vendingMachine.selectButton(outOfProduct);
		assertEquals("$0.50", checkCurrentState.getMachineDisplay().getDisplay());

		VendingMachineStatus returnCoins = new VendingMachineStatus(
			checkCurrentState.getCoinsAccumulated(),
			checkCurrentState.getMachineDisplay(),
			new VendingButton(VendingAction.COIN_RETURN, Product.NO_PRODUCT_SELECTED));

		VendingMachineStatus  finalState = vendingMachine.selectButton(returnCoins);
		assertEquals("INSERT COIN", finalState.getMachineDisplay().getDisplay());
	}


	public Map<Product,Integer> getProductCounts(ProductSelection productSelection, int counts) {
		Map<Product,Integer> productCounts = new HashMap<>();
		productCounts.put(productSelection.getProduct("chips"), counts);
		productCounts.put(productSelection.getProduct("cola"), counts);
		productCounts.put(productSelection.getProduct("candy"), counts);
		return productCounts;
	}
}