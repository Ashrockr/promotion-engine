package com.sku.calculators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sku.entites.base.ItemOrder;
import com.sku.entites.promotion.Promotion;
import com.sku.entites.promotion.PromotionType;
import com.sku.entites.promotion.SingleItemPromotion;

public class SinglePromotionOfferCalculator implements PromotionDiscountCalculator {

    private final Set<SingleItemPromotion> promotions = new HashSet<SingleItemPromotion>();

    @Override
    public boolean acceptsPromotion(final Promotion promotion) {
	return promotion instanceof SingleItemPromotion
		&& promotion.getPromotionType() == PromotionType.N_ITEM_FIXED_PRICE;
    }

    @Override
    public void addPromotion(final Promotion promotion) {
	if (!acceptsPromotion(promotion)) {
	    throw new IllegalArgumentException("Promotion is not suuported by current calculator");
	}
	promotions.add((SingleItemPromotion) promotion);
    }

    @Override
    public double getDiscountPrice(final List<ItemOrder> itemOrderList) {
	double discount = 0;
	for (final SingleItemPromotion singleItemPromotion : promotions) {
	    for (final ItemOrder itemOrder : itemOrderList) {
		discount += singleItemPromotion.getDiscountPrice(itemOrder);
	    }
	}
	return discount;
    }

}
