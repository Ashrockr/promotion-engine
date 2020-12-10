package com.sku.test.engine;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sku.engine.PromotionEngine;
import com.sku.engine.PromotionEngineImpl;
import com.sku.entites.base.Cart;
import com.sku.entites.base.Item;
import com.sku.entites.base.ItemOrder;
import com.sku.entites.base.SKUId;
import com.sku.exception.InvalidSKUIdException;
import com.sku.factory.ApplicationFactory;
import com.sku.inventory.PriceInventory;

public class PromotionEngineTest {

    @BeforeClass
    public static void setUp() {
	final PriceInventory priceInventory = ApplicationFactory.getPriceInventory();
	priceInventory.setPrice(SKUId.of('A'), 50);
	priceInventory.setPrice(SKUId.of('B'), 30);
	priceInventory.setPrice(SKUId.of('C'), 20);
	priceInventory.setPrice(SKUId.of('D'), 15);
    }

    /**
     * Test case: No Promotion calculator is added.
     */
    @Test
    public void testPromotionEngineNoPromotionCalculator() {
	final PromotionEngine promotionEngine = new PromotionEngineImpl();
	final Cart cart = getCartOf(SKUId.of('A'), 3);
	final double discount = promotionEngine.calculateDiscount(cart);
	Assert.assertEquals(0, discount, 0);

    }

    private Cart getCartOf(final SKUId skuId, final int quantity) {
	final ItemOrder itemOrder = getItemOrder(skuId, quantity);
	final Cart cart = new Cart();
	cart.addItemOrder(itemOrder);
	return cart;
    }

    private ItemOrder getItemOrder(final SKUId skuId, final int quantity) {
	final Item item = ApplicationFactory.getItemOf(skuId);
	final ItemOrder itemOrder = new ItemOrder(item);
	itemOrder.setQuantity(quantity);
	return itemOrder;
    }

    /**
     * Test case: Item price not available
     */
    @Test(expected = InvalidSKUIdException.class)
    public void testInvalidSKUId() {
	ApplicationFactory.getItemOf(SKUId.of('Z'));
    }

    /**
     * Test case: Single item Promotion calculator is added.
     */
    @Test
    public void testPromotionNItemDiscount() {
	final PromotionEngine promotionEngine = ApplicationFactory.getPromotionEngine();
	final Cart cart = getCartOf(SKUId.of('A'), 3);
	final double discount = promotionEngine.calculateDiscount(cart);
	Assert.assertEquals(20, discount, 0);
	cart.setDiscountPrice(discount);
	Assert.assertEquals(130, cart.getDiscountedPrice(), 0);
    }

    /**
     * Test case: Multi item Promotion calculator is added.
     */
    @Test
    public void testPromotionMultiItemDiscount() {
	final PromotionEngine promotionEngine = ApplicationFactory.getPromotionEngine();
	final Cart cart = getCartOf(SKUId.of('C'), 3);
	cart.addItemOrder(getItemOrder(SKUId.of('D'), 2));
	final double discount = promotionEngine.calculateDiscount(cart);
	Assert.assertEquals(10, discount, 0);
	cart.setDiscountPrice(discount);
	Assert.assertEquals(80, cart.getDiscountedPrice(), 0);
    }

    /**
     * Test case: All item Promotion calculator is added.<br>
     * Scenario:
     *
     * <pre>
     * 1 * A
     * 1 * B
     * 1 * C
     * </pre>
     */
    @Test
    public void testPromotionAllItemDiscountScenario1() {
	final PromotionEngine promotionEngine = ApplicationFactory.getPromotionEngine();
	final Cart cart = getCartOf(SKUId.of('A'), 1);
	cart.addItemOrder(getItemOrder(SKUId.of('B'), 1));
	cart.addItemOrder(getItemOrder(SKUId.of('C'), 1));
	final double discount = promotionEngine.calculateDiscount(cart);
	Assert.assertEquals(0, discount, 0);
	cart.setDiscountPrice(discount);
	Assert.assertEquals(100, cart.getDiscountedPrice(), 0);
    }

    /**
     * Test case: All item Promotion calculator is added.<br>
     * Scenario:
     *
     * <pre>
     * 5 * A
     * 5 * B
     * 1 * C
     * </pre>
     */
    @Test
    public void testPromotionAllItemDiscountScenario2() {
	final PromotionEngine promotionEngine = ApplicationFactory.getPromotionEngine();
	final Cart cart = getCartOf(SKUId.of('A'), 5);
	cart.addItemOrder(getItemOrder(SKUId.of('B'), 5));
	cart.addItemOrder(getItemOrder(SKUId.of('C'), 1));
	final double discount = promotionEngine.calculateDiscount(cart);
	Assert.assertEquals(50, discount, 0);
	cart.setDiscountPrice(discount);
	Assert.assertEquals(370, cart.getDiscountedPrice(), 0);
    }

}
