package com.xenoamess.commons.collections.list;

import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

/**
 * <p>Abstract PrimitiveArrayList class.</p>
 *
 * @author XenoAmess
 * @version 0.6.0
 */
public abstract class PrimitiveArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable {

    /**
     * Default initial capacity.
     */
    public static final int DEFAULT_CAPACITY = 10;

    /**
     * <p>checkIndex.</p>
     *
     * @param index a int.
     * @param length a int.
     * @return a int.
     */
    public static int checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        return index;
    }

    /** {@inheritDoc} */
    public static void subListRangeCheck(int fromIndex, int toIndex, int size) {
        if (fromIndex < 0) {
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        }
        if (toIndex > size) {
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        }
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
        }
    }

}
