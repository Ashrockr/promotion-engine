package com.sku;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.sku.calculators.PromotionDiscountCalculator;
import com.sku.engine.PromotionEngine;
import com.sku.engine.PromotionEngineImpl;
import com.sku.entites.base.Item;
import com.sku.entites.base.SKUId;
import com.sku.entites.promotion.Promotion;
import com.sku.inventory.InMemoryPriceInventory;
import com.sku.inventory.PriceInventory;

/**
 * Factory class
 */
public class ApplicationFactory {

    private ApplicationFactory() {
	throw new IllegalStateException("static factory class");
    }

    /**
     * Unmodifiable List containing all available
     * {@link PromotionDiscountCalculator}
     */
    private static final List<PromotionDiscountCalculator> promotionDiscountCalculator;

    /**
     * Unmodifiable List containing all available {@link Promotion}
     */
    private static final List<Promotion> availablePromotions;

    static {
	promotionDiscountCalculator = Collections.unmodifiableList(new ArrayList<>());
	availablePromotions = Collections.unmodifiableList(new ArrayList<>());
    }

    /**
     * get the sigleton instance of {@link PromotionEngine}
     *
     * @return
     */
    public static PromotionEngine getPromotionEngine() {
	final PromotionEngine promotionEngine = PromotionEngineImpl.getInstance();
	initPromotionEngine(promotionEngine);
	return promotionEngine;
    }

    /**
     * initialize the Promotion engine with the necessary fields
     *
     * @param promotionEngine to be initilized
     */
    private static void initPromotionEngine(final PromotionEngine promotionEngine) {
	promotionDiscountCalculator.forEach(promotionEngine::addPromotionDiscountCalculator);
    }

    /**
     * Get singleton instance of {@link PriceInventory}
     *
     */
    public static PriceInventory getPriceInventory() {
	return InMemoryPriceInventory.getInstance();
    }

    public static Item getItemOf(final SKUId skuId) {
	final PriceInventory priceInventory = getPriceInventory();
	final double price = priceInventory.getPrice(skuId);
	return new Item(skuId, price);
    }
}
