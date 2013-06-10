/**
 * This service class will process all the items
 * in a ShoppingCart and will cash out each item.
 * Cash out will calculate final cost of each item 
 * which includes basic tax if not exempted and 
 * additional tax if the item is imported.
 * This will also calculate the final sales tax and 
 * the total cost of the {@link ShoppingCart} Items.
 * Finally the calculated data are formatted using 
 * {@link Utilities}. Then receipt is generated for 
 * the final items.
 * @author prasenjit das
 */
package com.prasenjit.shoppingApplication.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.prasenjit.shoppingApplication.module.shoppingcart.ShoppingCart;
import com.prasenjit.shoppingApplication.module.shoppingitems.Item;
import com.prasenjit.shoppingApplication.types.TaxCategories;
import com.prasenjit.shoppingApplication.utils.Utilities;

public class CashCounterServiceImpl implements ICashCounterService {

	List<String> cashCounterItems = null;

	public CashCounterServiceImpl() {
		cashCounterItems = new ArrayList<String>();
	}

	/**
	 * Retrieves all the items from {@link ShoppingCart}.
	 * Iterates all the items and calls cashOutItem on 
	 * each item. Then it aggregates total sales tax and 
	 * total cost and generates receipt using {@link Utilities}.
	 * @param shoppingCart
	 */
	@Override
	public void processShoppingcartAndGenerateReciept(ShoppingCart shoppingCart)
			throws IOException {
		cashCounterItems.clear();
		List<Item> items = shoppingCart.getItems();
		for (Item item : items) {
			cashOutItem(item);
		}

		cashCounterItems.add("Sales Taxes: "
				+ Utilities.formatNumber(getTotalSalesTax(items)));
		cashCounterItems.add("Total: "
				+ Utilities.formatNumber(getTotalCost(items)));
		Utilities.generateReciept(cashCounterItems);
	}

/**
 * This aggregates total cost and total sales of an {@link Item}.
 * Adds receipt related data in cashCounterItems.
 */
	@Override
	public void cashOutItem(Item item) {
		BigDecimal totalPrice = new BigDecimal(getTotalPrice(item));
		BigDecimal totalSalesTax = new BigDecimal(getTotalSalesTax(item));
		BigDecimal bigDecimal = totalPrice.add(totalSalesTax);
		float finalCost = bigDecimal.floatValue();
		cashCounterItems.add(item.getQuantity() + " " + item.getDescription()
				+ " : " + Utilities.formatNumber(finalCost));
	}

	/**
	 * 
	 * @param item
	 * @return
	 */
	private float getTotalPrice(Item item) {
		return item.getQuantity() * item.getPrice();
	}
	/**
	 * Retrieves the {@link TaxCategories} list and
	 * iterates through each . Then calculates tax
	 * for each category. It then formats the result 
	 * value with {@link Utilities}. 
	 * @param item
	 * @return
	 */
	private float getTotalSalesTax(Item item) {
		float totalSalesTax = 0.0f;
		float totalPrice = getTotalPrice(item);
		for (TaxCategories taxCategory : item.getTaxCategories()) {
			totalSalesTax += totalPrice
					* taxCategory.getRate(item.getCategory());
		}
		totalSalesTax = Utilities.format(totalSalesTax);
		return totalSalesTax;
	}
	
	/**
	 * 
	 * @param items
	 * @return
	 */
	private float getTotalSalesTax(List<Item> items) {
		float totalSalesTax = 0.00f;
		for (Item item : items) {
			totalSalesTax += getTotalSalesTax(item);
		}
		return totalSalesTax;
	}

	/**
	 * 
	 * @param items
	 * @return
	 */
	private float getTotalCost(List<Item> items) {
		float totalCost = 0.0f;
		BigDecimal totalCostBD = new BigDecimal(totalCost);
		for (Item item : items) {
			BigDecimal totalPrice = new BigDecimal(getTotalPrice(item));
			BigDecimal totalSalesTax = new BigDecimal(getTotalSalesTax(item));
			BigDecimal bigDecimal = totalPrice.add(totalSalesTax);
			totalCostBD = totalCostBD.add(bigDecimal);
		}
		totalCost = totalCostBD.floatValue();
		return totalCost;
	}

	public List<String> getCashCounterItems() {
		return cashCounterItems;
	}
}
