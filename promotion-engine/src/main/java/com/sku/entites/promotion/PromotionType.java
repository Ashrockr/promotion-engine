package com.sku.entites.promotion;

public enum PromotionType {

    N_ITEM_FIXED_PRICE("Buy 'n' items of SKU for a fixed price"), //
    COMBO_OFFER("buy SKU 1 & 2 at fixed price");

    private String description;

    private PromotionType(final String desc) {
	description = desc;
    }

    public String getDescription() {
	return description;
    }
}
