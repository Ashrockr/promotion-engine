package com.sku.calculators;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.sku.entites.base.ItemOrder;
import com.sku.entites.promotion.MultiItemPromotion;
import com.sku.entites.promotion.Promotion;
import com.sku.entites.promotion.PromotionType;

public class ComboPromotionOfferCalculator implements PromotionDiscountCalculator {

    private final Set<MultiItemPromotion> promotions = new HashSet<>();

    @Override
    public boolean acceptsPromotion(final Promotion promotion) {
	return promotion instanceof MultiItemPromotion && promotion.getPromotionType() == PromotionType.COMBO_OFFER;
    }

    @Override
    public void addPromotion(final Promotion promotion) {
	if (!acceptsPromotion(promotion)) {
	    throw new IllegalArgumentException("Promotion is not suuported by current calculator");
	}
	promotions.add((MultiItemPromotion) promotion);
    }

    @Override
    public double getDiscountPrice(final List<ItemOrder> itemOrderList) {
	double discount = 0;
	for (final MultiItemPromotion multiItemPromotion : promotions) {
	    discount += multiItemPromotion.getTotalDiscount(itemOrderList);
	}
	return discount;
    }

}
