package com.sku.entites.promotion;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;

import com.sku.entites.base.ItemOrder;
import com.sku.entites.base.SKUId;

/**
 * Promotion offer for combo items.
 * <p>
 * E.g:
 * <li>B & C for 30</li>
 *
 * @author ashish
 *
 */
public class ComboItemPromotion implements MultiItemPromotion {

    private List<SKUId> comboOn = new ArrayList<SKUId>();
    private double fixedPrice = 0;

    public void setComboOn(final List<SKUId> comboOn) {
	this.comboOn = comboOn;
    }

    public void setFixedPrice(final double fixedPrice) {
	this.fixedPrice = fixedPrice;
    }

    @Override
    public double getTotalDiscount(final List<ItemOrder> itemOrders) {
	final List<ItemOrder> filteredList = itemOrders.stream()
		.filter(itemOrder -> comboOn.contains(itemOrder.getItem().getId())).collect(Collectors.toList());
	if (filteredList.size() != comboOn.size()) {
	    return 0;
	}
	final OptionalInt minQuantity = filteredList.stream().mapToInt(ItemOrder::getQuantity).min();
	final double totalPrice = filteredList.stream().mapToDouble(itemOrder -> itemOrder.getItem().getPrice()).sum();
	if (minQuantity.isPresent()) {
	    return (totalPrice - fixedPrice) * minQuantity.getAsInt();
	}
	return 0;
    }

    @Override
    public PromotionType getPromotionType() {
	return PromotionType.COMBO_OFFER;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((comboOn == null) ? 0 : comboOn.hashCode());
	long temp;
	temp = Double.doubleToLongBits(fixedPrice);
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
	final ComboItemPromotion other = (ComboItemPromotion) obj;
	if (comboOn == null) {
	    if (other.comboOn != null) {
		return false;
	    }
	} else if (!comboOn.equals(other.comboOn))
	    return false;
	return Double.doubleToLongBits(fixedPrice) == Double.doubleToLongBits(other.fixedPrice);
    }

    @Override
    public String toString() {
	return "ComboItemPromotion [comboOn=" + comboOn + ", fixedPrice=" + fixedPrice + "]";
    }

}
