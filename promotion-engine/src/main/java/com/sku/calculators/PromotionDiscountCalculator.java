package com.sku.calculators;

import java.util.List;

import com.sku.entites.base.ItemOrder;
import com.sku.entites.promotion.Promotion;

public interface PromotionDiscountCalculator {

    /**
     * checks whether the provided {@link Promotion} is relavant for the current
     * calculator
     *
     * @param promotion
     * @return true is provided Promotion can be applied with the calculator
     */
    public boolean acceptsPromotion(Promotion promotion);

    /**
     * add the provided {@link Promotion} to calculator
     *
     * @param promotion to be added
     */
    public void addPromotion(Promotion promotion);

    /**
     * calculate the total discount of the provided items from the available
     * promotion.
     */
    public double getDiscountPrice(List<ItemOrder> itemIterator);
}
