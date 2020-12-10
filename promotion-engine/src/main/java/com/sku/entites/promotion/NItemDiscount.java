package com.sku.entites.promotion;

import java.util.Objects;

import com.sku.entites.base.Item;
import com.sku.entites.base.ItemOrder;
import com.sku.entites.base.SKUId;

/**
 * Discount promotion for: buy 'n' items at fixed price
 *
 * @author ashish
 *
 */
public class NItemDiscount implements SingleItemPromotion {

    private SKUId sKUId;

    private int quantity;

    private double fixedPrice;

    @Override
    public double getDiscountPrice(final ItemOrder itemOrder) {
	final Item item = itemOrder.getItem();
	if (Objects.equals(item.getId(), sKUId) && quantity > 0) {
	    final int discountedItem = itemOrder.getQuantity() / quantity;
	    if (discountedItem > 0) {
		return (item.getPrice() * quantity) - (discountedItem * fixedPrice);
	    }
	}
	return 0;
    }

    @Override
    public PromotionType getPromotionType() {
	return PromotionType.N_ITEM_FIXED_PRICE;
    }

    public void setsKUId(final SKUId sKUId) {
	this.sKUId = sKUId;
    }

    public void setQuantity(final int quantity) {
	this.quantity = quantity;
    }

    public void setFixedPrice(final double fixedPrice) {
	this.fixedPrice = fixedPrice;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	long temp;
	temp = Double.doubleToLongBits(fixedPrice);
	result = prime * result + (int) (temp ^ (temp >>> 32));
	result = prime * result + quantity;
	result = prime * result + ((sKUId == null) ? 0 : sKUId.hashCode());
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
	final NItemDiscount other = (NItemDiscount) obj;
	if (Double.doubleToLongBits(fixedPrice) != Double.doubleToLongBits(other.fixedPrice))
	    return false;
	if (quantity != other.quantity)
	    return false;
	if (sKUId == null) {
	    if (other.sKUId != null)
		return false;
	} else if (!sKUId.equals(other.sKUId))
	    return false;
	return true;
    }

}
