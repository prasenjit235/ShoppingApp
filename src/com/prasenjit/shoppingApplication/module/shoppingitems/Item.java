/**
 * This class contains all the data necessary
 * for billing and calculating taxes.  
 * @author prasenjit das
 */
package com.prasenjit.shoppingApplication.module.shoppingitems;

import java.util.ArrayList;
import java.util.List;

import com.prasenjit.shoppingApplication.types.ItemCategories;
import com.prasenjit.shoppingApplication.types.TaxCategories;

public class Item {

	
	private int quantity;
	private float price;
	private String description;
	private ItemCategories category;
	private List<TaxCategories> taxCategories;
	public Item() {
		taxCategories=new ArrayList<TaxCategories>();
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<TaxCategories> getTaxCategories() {
		return taxCategories;
	}
	public void setTaxCategories(List<TaxCategories> taxCategories) {
		this.taxCategories = taxCategories;
	}
	public ItemCategories getCategory() {
		return category;
	}
	public void setCategory(ItemCategories category) {
		this.category = category;
	}
	
	
}
