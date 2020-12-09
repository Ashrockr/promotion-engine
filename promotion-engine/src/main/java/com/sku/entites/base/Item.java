package com.sku.entites.base;

/**
 * Entity representing a real world Item entity.
 * 
 * @author ashish
 *
 */
public class Item {

    private final SKUId id;

    private final double price;

    public Item(SKUId id, double price) {
	this.id = id;
	this.price = price;
    }

    /**
     * get the {@link SKUId} of the item
     */
    public SKUId getId() {
	return id;
    }

    /**
     * get the price of the item
     */
    public double getPrice() {
	return price;
    }



}
