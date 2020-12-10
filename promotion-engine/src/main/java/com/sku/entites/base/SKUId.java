package com.sku.entites.base;

/**
 *
 * Unique Id assigned to an {@link Item}.
 *
 * @author ashish
 *
 */
public class SKUId {

    private final char id;

    public SKUId(final char id) {
	this.id = id;
    }

    /**
     * Returns the SKUId representation of the {@code char} argument. (static helper
     * method to create new SKUId)
     *
     * @param id a {@code char}.
     * @return a SKUId of the provided ID
     */
    public static SKUId of(final char id) {
	return new SKUId(id);
    }

    @Override
    public String toString() {
	return String.valueOf(id);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + id;
	return result;
    }

    @Override
    public boolean equals(final Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final SKUId other = (SKUId) obj;
	return id == other.id;
    }

}
