package com.xenoamess.commons.primitive.iterators;

import com.xenoamess.commons.primitive.collections.lists.AbstractDoubleList;
import com.xenoamess.commons.primitive.collections.lists.DoubleList;
import com.xenoamess.commons.primitive.functions.DoubleConsumer;

import java.util.*;
import java.util.function.Consumer;

/**
 * An index-based split-by-two, lazily initialized Spliterator covering
 * a List that access elements via {@link List#get}.
 * <p>
 * If access results in an IndexOutOfBoundsException then a
 * ConcurrentModificationException is thrown instead (since the list has
 * been structurally modified while traversing).
 * <p>
 * If the List is an instance of AbstractList then concurrent modification
 * checking is performed using the AbstractList's modCount field.
 */
public class DoubleRandomAccessSpliterator implements DoubleSpliterator {

    private final DoubleList list;
    private int index; // current index, modified on advance/split
    private int fence; // -1 until used; then one past last index

    // The following fields are valid if covering an AbstractList
    private final AbstractDoubleList alist;
    private int expectedModCount; // initialized when fence set

    public DoubleRandomAccessSpliterator(DoubleList list) {
        assert list instanceof RandomAccess;

        this.list = list;
        this.index = 0;
        this.fence = -1;

        this.alist = list instanceof AbstractDoubleList ? (AbstractDoubleList) list : null;
        this.expectedModCount = alist != null ? alist.modCount : 0;
    }

    /**
     * Create new spliterator covering the given  range
     */
    public DoubleRandomAccessSpliterator(DoubleRandomAccessSpliterator parent,
                                         int origin, int fence) {
        this.list = parent.list;
        this.index = origin;
        this.fence = fence;

        this.alist = parent.alist;
        this.expectedModCount = parent.expectedModCount;
    }

    private int getFence() { // initialize fence to size on first use
        int hi;
        DoubleList lst = list;
        if ((hi = fence) < 0) {
            if (alist != null) {
                expectedModCount = alist.modCount;
            }
            hi = fence = lst.size();
        }
        return hi;
    }

    @Override
    public DoubleSpliterator trySplit() {
        int hi = getFence(), lo = index, mid = (lo + hi) >>> 1;
        return (lo >= mid) ? null : // divide range in half unless too small
                new DoubleRandomAccessSpliterator(this, lo, index = mid);
    }

    @Override
    public boolean tryAdvance(Consumer<? super Double> action) {
        if (action == null) {
            throw new NullPointerException();
        }

        int hi = getFence(), i = index;
        if (i < hi) {
            index = i + 1;
            if (action instanceof DoubleConsumer) {
                ((DoubleConsumer) action).acceptPrimitive(getPrimitive(list, i));
            } else {
                action.accept(getPrimitive(list, i));
            }
            checkAbstractListModCount(alist, expectedModCount);
            return true;
        }
        return false;
    }

    @Override
    public void forEachRemaining(Consumer<? super Double> action) {
        Objects.requireNonNull(action);
        DoubleList lst = list;
        int hi = getFence();
        int i = index;
        index = hi;
        if (action instanceof DoubleConsumer) {
            DoubleConsumer actionDoubleConsumer = (DoubleConsumer) action;
            for (; i < hi; i++) {
                actionDoubleConsumer.acceptPrimitive(getPrimitive(lst, i));
            }
        } else {
            for (; i < hi; i++) {
                action.accept(getPrimitive(lst, i));
            }
        }
        checkAbstractListModCount(alist, expectedModCount);
    }

    @Override
    public long estimateSize() {
        return (long) (getFence() - index);
    }

    @Override
    public int characteristics() {
        return Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED;
    }

    private static double getPrimitive(DoubleList list, int i) {
        try {
            return list.getPrimitive(i);
        } catch (IndexOutOfBoundsException ex) {
            throw new ConcurrentModificationException();
        }
    }

    static void checkAbstractListModCount(AbstractDoubleList alist, int expectedModCount) {
        if (alist != null && alist.modCount != expectedModCount) {
            throw new ConcurrentModificationException();
        }
    }
}