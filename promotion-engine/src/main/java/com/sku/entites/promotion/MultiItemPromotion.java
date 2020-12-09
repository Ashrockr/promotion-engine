package com.sku.entites.promotion;

import com.sku.entites.base.ItemOrder;

/**
 * Promotion for multiple items
 * 
 * @author aspaliwa
 *
 */
public interface MultiItemPromotion {
    /**
     * calculate the total discount for a given item
     * 
     */
    public double getTotalDiscount(Iterable<ItemOrder> itemOrder);
}
