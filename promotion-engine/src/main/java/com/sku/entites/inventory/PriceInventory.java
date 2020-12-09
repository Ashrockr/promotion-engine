package com.sku.entites.inventory;

import com.sku.entites.base.SKUId;

/**
 * Inventory for the price of item
 * 
 * @author ashish
 *
 */
public interface PriceInventory {

    /**
     * sets the price for the provided {@link SKUId} in the inventory
     * 
     * @param id    {@link SKUId} of the item who's price is to be set
     * @param price price of the {@link SKUId}
     */
    public void setPrice(SKUId id, double price);

    /**
     * fetches the price of the provided {@link SKUId}
     * 
     */
    public double getPrice(SKUId id);
}
