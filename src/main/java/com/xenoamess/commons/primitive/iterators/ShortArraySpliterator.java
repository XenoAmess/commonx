package com.xenoamess.commons.primitive.iterators;

import com.xenoamess.commons.primitive.comparators.ShortComparator;
import com.xenoamess.commons.primitive.functions.ShortConsumer;

import java.util.Spliterator;

/**
 * A ShortSpliterator.ShortOfShort designed for use by sources that traverse and split
 * elements maintained in an unmodifiable {@code int[]} array.
 */
public class ShortArraySpliterator implements ShortSpliterator.ShortOfShort {
    private final short[] array;
    private int index;        // current index, modified on advance/split
    private final int fence;  // one past last index
    private final int characteristics;

    /**
     * Creates a spliterator covering all of the given array.
     *
     * @param array                     the array, assumed to be unmodified during use
     * @param additionalCharacteristics Additional spliterator characteristics
     *                                  of this spliterator's source or elements beyond {@code SIZED} and
     *                                  {@code SUBSIZED} which are always reported
     */
    public ShortArraySpliterator(short[] array, int additionalCharacteristics) {
        this(array, 0, array.length, additionalCharacteristics);
    }

    /**
     * Creates a spliterator covering the given array and range
     *
     * @param array                     the array, assumed to be unmodified during use
     * @param origin                    the least index (inclusive) to cover
     * @param fence                     one past the greatest index to cover
     * @param additionalCharacteristics Additional spliterator characteristics
     *                                  of this spliterator's source or elements beyond {@code SIZED} and
     *                                  {@code SUBSIZED} which are always reported
     */
    public ShortArraySpliterator(short[] array, int origin, int fence, int additionalCharacteristics) {
        this.array = array;
        this.index = origin;
        this.fence = fence;
        this.characteristics = additionalCharacteristics | ShortSpliterator.SIZED | ShortSpliterator.SUBSIZED;
    }

    @Override
    public ShortOfShort trySplit() {
        int lo = index, mid = (lo + fence) >>> 1;
        return (lo >= mid)
                ? null
                : new ShortArraySpliterator(array, lo, index = mid, characteristics);
    }

    @Override
    public void forEachRemaining(ShortConsumer action) {
        short[] a;
        int i, hi; // hoist accesses and checks from loop
        if (action == null) {
            throw new NullPointerException();
        }
        if ((a = array).length >= (hi = fence) &&
                (i = index) >= 0 && i < (index = hi)) {
            do {
                action.accept(a[i]);
            } while (++i < hi);
        }
    }

    @Override
    public boolean tryAdvance(ShortConsumer action) {
        if (action == null) {
            throw new NullPointerException();
        }
        if (index >= 0 && index < fence) {
            action.accept(array[index++]);
            return true;
        }
        return false;
    }

    @Override
    public long estimateSize() {
        return (long) (fence - index);
    }

    @Override
    public int characteristics() {
        return characteristics;
    }

    @Override
    public ShortComparator getComparator() {
        if (hasCharacteristics(Spliterator.SORTED)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
