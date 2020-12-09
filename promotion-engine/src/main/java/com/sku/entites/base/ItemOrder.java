package com.sku.entites.base;

/**
 * Entity responsible for holding the item and it's quantity
 * 
 * @author ashish
 *
 */
public class ItemOrder {

    private final Item item;

    private int quantity = 1;

    public ItemOrder(Item item) {
	this.item = item;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(int quantity) {
	this.quantity = quantity;
    }

    public Item getItem() {
	return item;
    }

}
