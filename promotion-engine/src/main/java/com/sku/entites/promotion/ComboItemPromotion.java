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
	final OptionalInt minQuantity = filteredList.stream().mapToInt(ItemOrder::getQuantity).min();
	if (minQuantity.isPresent()) {
	    return fixedPrice * minQuantity.getAsInt();
	}
	return 0;
    }

    @Override
    public PromotionType getPromotionType() {
	return PromotionType.COMBO_OFFER;
    }

}
