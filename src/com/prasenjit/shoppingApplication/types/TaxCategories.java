/**
 * @author prasenjit das
 */
package com.prasenjit.shoppingApplication.types;

public enum TaxCategories {

	BASIC(){

		@Override
		public float getRate(ItemCategories itemCategories) {
			if (itemCategories.name().equals(ItemCategories.BOOKS.name())) {
				return 0.0f;
			}else if (itemCategories.name().equals(ItemCategories.FOOD.name())) {
				return 0.0f;
			}else if (itemCategories.name().equals(ItemCategories.MEDICAL.name())) {
				return 0.0f;
			}else {
				return 0.10f;
			}
		}
		
	},
	ADDITIONAL(){

		@Override
		public float getRate(ItemCategories itemCategories) {
			return 0.05f;
		}
		
	};
	
	public abstract float getRate(ItemCategories itemCategories);
	
}
