package com.sku.entites.promotion;

import com.sku.entites.base.ItemOrder;

/**
 * Promotion for a single item
 * 
 * @author ashish
 *
 */
public interface SingleItemPromotion extends Promotion {

    /**
     * calculate the total discount for a given item
     * 
     */
    public double getDiscountPrice(ItemOrder itemOrder);

}
