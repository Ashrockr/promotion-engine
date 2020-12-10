package com.sku.entites.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Entity holding the all {@link ItemOrder}s.
 *
 * @author ashish
 */
public class Cart {

    private final List<ItemOrder> itemOrders = new ArrayList<>();

    private double totalPrice = 0;

    private double discountPrice = 0;

    /**
     * add {@link ItemOrder} into the cart
     *
     * @param itemOrder to be added to cart (require non null)
     */
    public void addItemOrder(final ItemOrder itemOrder) {
	Objects.requireNonNull(itemOrder, "null item entered");
	itemOrders.add(itemOrder);

	totalPrice += itemOrder.getItem().getPrice() * itemOrder.getQuantity();
    }

    /**
     * provides all the items added in the cart
     *
     * @return unmodifiable {@link List} of items (never null)
     */
    public List<ItemOrder> getAllItemOrders() {
	return Collections.unmodifiableList(itemOrders);
    }

    public double getTotalPrice() {
	return totalPrice;
    }

    public double getDiscountedPrice() {
	return totalPrice - discountPrice;
    }

    public void setDiscountPrice(final double discountPrice) {
	this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
	return itemOrders.toString();
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + itemOrders.hashCode();
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
	final Cart other = (Cart) obj;
	return itemOrders.equals(other.itemOrders);
    }
}
