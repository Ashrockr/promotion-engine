package com.sku.entites.promotion;

import java.util.List;

import com.sku.entites.base.ItemOrder;

/**
 * Promotion for multiple items
 *
 * @author ashish
 *
 */
public interface MultiItemPromotion extends Promotion {
    /**
     * calculate the total discount for a given item
     *
     */
    public double getTotalDiscount(List<ItemOrder> itemOrder);
}
