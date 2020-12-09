package com.sku.engine;

import com.sku.entites.base.Cart;
import com.sku.entites.inventory.PriceInventory;
import com.sku.entites.promotion.Promotion;

/**
 * Interface to provide the basic methods provided by Promotion engine
 * 
 * @author ashish
 *
 */
public interface PromotionEngine {

    /**
     * adds a Promotion into the {@link PromotionEngine}, which is to be applied
     * during amount calculation.
     * 
     * @param promotion {@link Promotion} to be applied
     */
    public void addPromotion(Promotion promotion);

    /**
     * sets the {@link PriceInventory} which shall provide the price of an item
     * 
     * @param priceInventory
     */
    public void addPriceData(PriceInventory priceInventory);

    /**
     * calculates the total amount of the cart after applying the available
     * {@link Promotion}
     * 
     * @param cart {@link Cart} who's total amount needs to be calculated
     * @return total amount of cart
     */
    public double calculateValue(Cart cart);
}
