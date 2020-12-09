package com.sku.engine;

import com.sku.calculators.PromotionDiscountCalculator;
import com.sku.entites.base.Cart;
import com.sku.entites.promotion.Promotion;

/**
 * Interface to provide the basic methods provided by Promotion engine
 * 
 * @author ashish
 *
 */
public interface PromotionEngine {

    /**
     * adds a PromotionDiscountCalculator into the {@link PromotionEngine}, which is
     * to be applies and calculates the discount.
     * 
     * @param promotionDiscountCalculator {@link PromotionDiscountCalculator} to be
     *                                    applied
     */
    public void addPromotionDiscountCalculator(PromotionDiscountCalculator promotionDiscountCalculator);

    /**
     * calculates the total discount amount of the cart after applying the available
     * {@link Promotion}
     * 
     * @param cart {@link Cart} who's total discount amount needs to be calculated
     * @return total amount of cart
     */
    public double calculateDiscount(Cart cart);
}
