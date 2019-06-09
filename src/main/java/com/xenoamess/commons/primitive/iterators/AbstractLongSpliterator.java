package com.xenoamess.commons.primitive.iterators;

import com.xenoamess.commons.primitive.functions.LongConsumer;

/**
 * An abstract {@code LongSpliterator.LongOfLong} that implements
 * {@code trySplit} to permit limited parallelism.
 *
 * <p>To implement a spliterator an extending class need only
 * implement {@link #tryAdvance(java.util.function.LongConsumer)
 * tryAdvance}.  The extending class should override
 * {@link #forEachRemaining(java.util.function.LongConsumer) forEachRemaining}
 * if it can provide a more performant implementation.
 *
 * @apiNote This class is a useful aid for creating a spliterator when it is not
 * possible or difficult to efficiently partition elements in a manner
 * allowing balanced parallel computation.
 *
 * <p>An alternative to using this class, that also permits limited
 * parallelism, is to create a spliterator from an iterator
 * (see {@link #spliterator(java.util.LongIterator, long, int)}.
 * Depending on the circumstances using an iterator may be easier or more
 * convenient than extending this class. For example, if there is already an
 * iterator available to use then there is no need to extend this class.
 * @see #spliterator(java.util.LongIterator, long, int)
 * @since 1.8
 */
public abstract class AbstractLongSpliterator implements LongSpliterator.LongOfLong {
    static final int MAX_BATCH = 1 << 10;
    static final int BATCH_UNIT = 1 << 25;
    private final int characteristics;
    private long est;             // size estimate
    private int batch;            // batch size for splits

    /**
     * Creates a spliterator reporting the given estimated size and
     * characteristics.
     *
     * @param est                       the estimated size of this spliterator if known, otherwise
     *                                  {@code Long.MAX_VALUE}.
     * @param additionalCharacteristics properties of this spliterator's
     *                                  source or elements.  If {@code SIZED} is reported then this
     *                                  spliterator will additionally report {@code SUBSIZED}.
     */
    protected AbstractLongSpliterator(long est, int additionalCharacteristics) {
        this.est = est;
        this.characteristics = ((additionalCharacteristics & LongSpliterator.SIZED) != 0)
                ? additionalCharacteristics | LongSpliterator.SUBSIZED
                : additionalCharacteristics;
    }

    static final class HoldingLongConsumer implements LongConsumer {
        long value;

        @Override
        public void acceptPrimitive(long value) {
            this.value = value;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation permits limited parallelism.
     */
    @Override
    public LongSpliterator.LongOfLong trySplit() {
        HoldingLongConsumer holder = new HoldingLongConsumer();
        long s = est;
        if (s > 1 && tryAdvance(holder)) {
            int n = batch + BATCH_UNIT;
            if (n > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            long[] a = new long[n];
            int j = 0;
            do {
                a[j] = holder.value;
            } while (++j < n && tryAdvance(holder));
            batch = j;
            if (est != Long.MAX_VALUE) {
                est -= j;
            }
            return new LongArraySpliterator(a, 0, j, characteristics());
        }
        return null;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation returns the estimated size as reported when
     * created and, if the estimate size is known, decreases in size when
     * split.
     */
    @Override
    public long estimateSize() {
        return est;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation returns the characteristics as reported when
     * created.
     */
    @Override
    public int characteristics() {
        return characteristics;
    }
}
