package com.sku.factory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import com.sku.calculators.PromotionDiscountCalculator;
import com.sku.calculators.SinglePromotionOfferCalculator;
import com.sku.engine.PromotionEngine;
import com.sku.engine.PromotionEngineImpl;
import com.sku.entites.base.Item;
import com.sku.entites.base.SKUId;
import com.sku.entites.promotion.ComboItemPromotion;
import com.sku.entites.promotion.NItemDiscount;
import com.sku.entites.promotion.Promotion;
import com.sku.entites.promotion.PromotionType;
import com.sku.exception.ValidationException;
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
     * Unmodifiable Map containing all available {@link Promotion}
     */
    private static final Map<PromotionType, Supplier<Promotion>> availablePromotions;

    static {
	promotionDiscountCalculator = Collections.unmodifiableList(Arrays.asList(new SinglePromotionOfferCalculator()));
	availablePromotions = Collections.unmodifiableMap(new HashMap<>());
    }

    /**
     * get the sigleton instance of {@link PromotionEngine}
     *
     * @return
     * @throws ValidationException if all promotions could not be initialized
     */
    public static PromotionEngine getPromotionEngine() throws ValidationException {
	final PromotionEngine promotionEngine = PromotionEngineImpl.getInstance();
	initPromotionEngine(promotionEngine);
	return promotionEngine;
    }

    /**
     * initialize the Promotion engine with the necessary fields
     *
     * @param promotionEngine to be initilized
     * @throws ValidationException
     */
    private static void initPromotionEngine(final PromotionEngine promotionEngine) throws ValidationException {
	promotionDiscountCalculator.forEach(promotionEngine::addPromotionDiscountCalculator);
	for (final Promotion promotion : getAllActivePromotions()) {
	    promotionEngine.addPromotion(promotion);
	}
    }

    private static List<Promotion> getAllActivePromotions() throws ValidationException {
	// creates the available promotions
	// TODO read form external file?
	final NItemDiscount aOf130For3 = new NItemDiscount();
	aOf130For3.setsKUId(SKUId.of('A'));
	aOf130For3.setQuantity(3);
	aOf130For3.setFixedPrice(130);

	final NItemDiscount bOf45For2 = new NItemDiscount();
	bOf45For2.setsKUId(SKUId.of('B'));
	bOf45For2.setQuantity(2);
	bOf45For2.setFixedPrice(45);

	final ComboItemPromotion comboItemPromotion = new ComboItemPromotion();
	comboItemPromotion.setComboOn(Arrays.asList(SKUId.of('C'), SKUId.of('D')));
	comboItemPromotion.setFixedPrice(30);

	return Arrays.asList(aOf130For3, bOf45For2, comboItemPromotion);
    }

    /**
     * Get singleton instance of {@link PriceInventory}
     *
     */
    public static PriceInventory getPriceInventory() {
	return InMemoryPriceInventory.getInstance();
    }

    /**
     * returns the Item for the provided {@link SKUId}
     */
    public static Item getItemOf(final SKUId skuId) {
	final PriceInventory priceInventory = getPriceInventory();
	final double price = priceInventory.getPrice(skuId);
	return new Item(skuId, price);
    }

}
