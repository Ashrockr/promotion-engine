package com.sku.entites.inventory;

import java.util.HashMap;
import java.util.Map;

import com.sku.entites.base.SKUId;

/**
 * In memory {@link PriceInventory} impl
 * 
 * @author ashish
 *
 */
public class InMemoryPriceInventory implements PriceInventory {

    private final Map<SKUId, Double> data = new HashMap<>();

    @Override
    public void setPrice(SKUId id, double price) {
	data.put(id, price);
    }

    @Override
    public double getPrice(SKUId id) {
	Double price = data.get(id);
	if (price == null) {
	    throw new IllegalArgumentException("Price not available for SKUId : " + id);
	}
	return price.doubleValue();
    }

}
