package com.sku.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.sku.calculators.PromotionDiscountCalculator;
import com.sku.entites.base.Cart;
import com.sku.entites.base.ItemOrder;

public class PromotionEngineImpl implements PromotionEngine {

    private static PromotionEngine instance;

    private final Set<PromotionDiscountCalculator> promotionDiscountCalculators = new HashSet<>();

    /**
     * 
     * @return {@link PromotionEngineImpl} instance
     */
    public static PromotionEngine getInstance() {
	if (instance == null) {
	    instance = new PromotionEngineImpl();
	}
	return instance;
    }

    @Override
    public void addPromotionDiscountCalculator(PromotionDiscountCalculator promotionDiscountCalculator) {
	Objects.requireNonNull(promotionDiscountCalculators);
	promotionDiscountCalculators.add(promotionDiscountCalculator);
    }

    @Override
    public double calculateDiscount(Cart cart) {
	final List<ItemOrder> itemOrders = new ArrayList<>(cart.getAllItemOrders());// create a copy of item orders
	final Iterator<ItemOrder> itemOrderIterator = itemOrders.iterator();
	double discountTotal = 0;
	for (PromotionDiscountCalculator promotionDiscountCalculator : promotionDiscountCalculators) {
	    discountTotal += promotionDiscountCalculator.getDiscountPrice(itemOrderIterator);
	}
	return discountTotal;
    }

}
