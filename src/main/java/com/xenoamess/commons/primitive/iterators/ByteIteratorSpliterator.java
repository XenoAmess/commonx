package com.xenoamess.commons.primitive.iterators;

import com.xenoamess.commons.primitive.collections.ByteCollection;
import com.xenoamess.commons.primitive.comparators.ByteComparator;
import com.xenoamess.commons.primitive.functions.ByteConsumer;

import java.util.Spliterator;

public class ByteIteratorSpliterator implements ByteSpliterator.ByteOfByte {
    static final int BATCH_UNIT = 1 << 10;  // batch array size increment
    static final int MAX_BATCH = 1 << 25;  // max batch array size;
    private final ByteCollection collection; // null OK
    private ByteIterator it;
    private final int characteristics;
    private long est;             // size estimate
    private int batch;            // batch size for splits

    /**
     * Creates a spliterator using the given
     * collection's {@link java.util.Collection#iterator()) for traversal,
     * and reporting its {@link java.util.Collection#size()) as its initial
     * size.
     *
     * @param c               the collection
     * @param characteristics properties of this spliterator's
     *                        source or elements.
     */
    public ByteIteratorSpliterator(ByteCollection collection, int characteristics) {
        this.collection = collection;
        this.it = null;
        this.characteristics = (characteristics & Spliterator.CONCURRENT) == 0
                ? characteristics | Spliterator.SIZED | Spliterator.SUBSIZED
                : characteristics;
    }

    /**
     * Creates a spliterator using the given iterator
     * for traversal, and reporting the given initial size
     * and characteristics.
     *
     * @param iterator        the iterator for the source
     * @param size            the number of elements in the source
     * @param characteristics properties of this spliterator's
     *                        source or elements.
     */
    public ByteIteratorSpliterator(ByteIterator iterator, long size, int characteristics) {
        this.collection = null;
        this.it = iterator;
        this.est = size;
        this.characteristics = (characteristics & ByteSpliterator.CONCURRENT) == 0
                ? characteristics | ByteSpliterator.SIZED | ByteSpliterator.SUBSIZED
                : characteristics;
    }

    /**
     * Creates a spliterator using the given iterator for a
     * source of unknown size, reporting the given
     * characteristics.
     *
     * @param iterator        the iterator for the source
     * @param characteristics properties of this spliterator's
     *                        source or elements.
     */
    public ByteIteratorSpliterator(ByteIterator iterator, int characteristics) {
        this.collection = null;
        this.it = iterator;
        this.est = Long.MAX_VALUE;
        this.characteristics = characteristics & ~(Spliterator.SIZED | ByteSpliterator.SUBSIZED);
    }

    @Override
    public ByteOfByte trySplit() {
        /*
         * Split into arrays of arithmetically increasing batch
         * sizes.  This will only improve parallel performance if
         * per-element Consumer actions are more costly than
         * transferring them into an array.  The use of an
         * arithmetic progression in split sizes provides overhead
         * vs parallelism bounds that do not particularly favor or
         * penalize cases of lightweight vs heavyweight element
         * operations, across combinations of #elements vs #cores,
         * whether or not either are known.  We generate
         * O(sqrt(#elements)) splits, allowing O(sqrt(#cores))
         * potential speedup.
         */
        ByteIterator i;
        long s;
        if ((i = it) == null) {
            i = it = collection.iterator();
            s = est = (long) collection.size();
        } else {
            s = est;
        }
        if (s > 1 && i.hasNext()) {
            int n = batch + BATCH_UNIT;
            if (n > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            byte[] a = new byte[n];
            int j = 0;
            do {
                a[j] = i.nextPrimitive();
            } while (++j < n && i.hasNext());
            batch = j;
            if (est != Long.MAX_VALUE) {
                est -= j;
            }
            return new ByteArraySpliterator(a, 0, j, characteristics);
        }
        return null;
    }

    @Override
    public void forEachRemaining(ByteConsumer action) {
        if (action == null) {
            throw new NullPointerException();
        }
        it.forEachRemaining(action);
    }

    @Override
    public boolean tryAdvance(ByteConsumer action) {
        if (action == null) {
            throw new NullPointerException();
        }
        if (it.hasNext()) {
            action.accept(it.nextPrimitive());
            return true;
        }
        return false;
    }

    @Override
    public long estimateSize() {
        return est;
    }

    @Override
    public int characteristics() {
        return characteristics;
    }

    @Override
    public ByteComparator getComparator() {
        if (hasCharacteristics(Spliterator.SORTED)) {
            return null;
        }
        throw new IllegalStateException();
    }
}
