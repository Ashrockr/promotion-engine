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
	    return (item.getPrice() * discountedItem) - (discountedItem * fixedPrice);
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

}
