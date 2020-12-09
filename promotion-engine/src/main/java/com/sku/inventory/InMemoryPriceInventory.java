package com.sku.inventory;

import java.util.HashMap;
import java.util.Map;

import com.sku.entites.base.SKUId;
import com.sku.exception.InvalidSKUIdException;

/**
 * In memory {@link PriceInventory} impl
 *
 * @author ashish
 *
 */
public class InMemoryPriceInventory implements PriceInventory {

    private static PriceInventory instance = null;

    private final Map<SKUId, Double> data = new HashMap<>();

    private InMemoryPriceInventory() {
	// singleton pattern
    }

    @Override
    public void setPrice(final SKUId id, final double price) {
	data.put(id, price);
    }

    @Override
    public double getPrice(final SKUId id) {
	final Double price = data.get(id);
	if (price == null) {
	    throw new InvalidSKUIdException("Price not available for SKUId : " + id);
	}
	return price.doubleValue();
    }

    /**
     *
     * @return {@link InMemoryPriceInventory} instance
     */
    public static PriceInventory getInstance() {
	if (instance == null) {
	    instance = new InMemoryPriceInventory();
	}
	return instance;
    }

}
