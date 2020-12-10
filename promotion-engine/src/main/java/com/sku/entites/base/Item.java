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

    public Item(final SKUId id, final double price) {
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

    @Override
    public String toString() {
	return "Item [id=" + id + ", price=" + price + "]";
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	long temp;
	temp = Double.doubleToLongBits(price);
	result = prime * result + (int) (temp ^ (temp >>> 32));
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
	final Item other = (Item) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
	    return false;
	return true;
    }

}
