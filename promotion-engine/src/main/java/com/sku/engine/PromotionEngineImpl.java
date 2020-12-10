package com.sku.engine;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.sku.calculators.PromotionDiscountCalculator;
import com.sku.entites.base.Cart;
import com.sku.entites.base.ItemOrder;
import com.sku.entites.promotion.Promotion;

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
    public void addPromotionDiscountCalculator(final PromotionDiscountCalculator promotionDiscountCalculator) {
	Objects.requireNonNull(promotionDiscountCalculators);
	promotionDiscountCalculators.add(promotionDiscountCalculator);
    }

    @Override
    public double calculateDiscount(final Cart cart) {
	Objects.requireNonNull(cart, "cart should not be null");
	final List<ItemOrder> itemOrders = new ArrayList<>(cart.getAllItemOrders());// create a copy of item orders
	double discountTotal = 0;
	for (final PromotionDiscountCalculator promotionDiscountCalculator : promotionDiscountCalculators) {
	    discountTotal += promotionDiscountCalculator.getDiscountPrice(itemOrders);
	}
	return discountTotal;
    }

    @Override
    public void addPromotion(final Promotion promotion) {
	for (final PromotionDiscountCalculator promotionDiscountCalculator : promotionDiscountCalculators) {
	    if (promotionDiscountCalculator.acceptsPromotion(promotion)) {
		promotionDiscountCalculator.addPromotion(promotion);
	    }
	}
    }

}
