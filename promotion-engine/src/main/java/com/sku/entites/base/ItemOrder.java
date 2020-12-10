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

    public ItemOrder(final Item item) {
	this.item = item;
    }

    public int getQuantity() {
	return quantity;
    }

    public void setQuantity(final int quantity) {
	this.quantity = quantity;
    }

    public Item getItem() {
	return item;
    }

    @Override
    public String toString() {
	return "ItemOrder [item=" + item + ", quantity=" + quantity + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((item == null) ? 0 : item.hashCode());
	result = prime * result + quantity;
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final ItemOrder other = (ItemOrder) obj;
	if (item == null) {
	    if (other.item != null) {
		return false;
	    }
	} else if (!item.equals(other.item))
	    return false;
	return quantity == other.quantity;
    }

}
