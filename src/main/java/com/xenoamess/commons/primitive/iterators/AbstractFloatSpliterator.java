/*
 * Copyright (c) 1997, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package com.xenoamess.commons.primitive.iterators;

import com.xenoamess.commons.primitive.functions.FloatConsumer;

/**
 * An abstract {@code FloatSpliterator.FloatOfFloat} that implements
 * {@code trySplit} to permit limited parallelism.
 *
 * <p>To implement a spliterator an extending class need only
 * implement {@link #tryAdvance(FloatConsumer)
 * tryAdvance}.  The extending class should override
 * {@link #forEachRemaining(FloatConsumer) forEachRemaining}
 * if it can provide a more performant implementation.
 *
 * @author XenoAmess
 * @version 0.8.0
 * @apiNote This class is a useful aid for creating a spliterator when it is not
 * possible or difficult to efficiently partition elements in a manner
 * allowing balanced parallel computation.
 *
 * <p>An alternative to using this class, that also permits limited
 * parallelism, is to create a spliterator from an iterator
 * (see {@link com.xenoamess.commons.primitive.iterators.FloatSpliterators#spliterator(FloatIterator, long, int)}.
 * Depending on the circumstances using an iterator may be easier or more
 * convenient than extending this class. For example, if there is already an
 * iterator available to use then there is no need to extend this class.
 * @see FloatSpliterators#spliterator(FloatIterator, long, int)
 * @since 1.8
 */
public abstract class AbstractFloatSpliterator implements FloatSpliterator.FloatOfFloat {
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
    protected AbstractFloatSpliterator(long est, int additionalCharacteristics) {
        this.est = est;
        this.characteristics = ((additionalCharacteristics & FloatSpliterator.SIZED) != 0)
                ? additionalCharacteristics | FloatSpliterator.SUBSIZED
                : additionalCharacteristics;
    }

    static final class HoldingFloatConsumer implements FloatConsumer {
        float value;

        @Override
        public void acceptPrimitive(float value) {
            this.value = value;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * This implementation permits limited parallelism.
     */
    @Override
    public FloatSpliterator.FloatOfFloat trySplit() {
        HoldingFloatConsumer holder = new HoldingFloatConsumer();
        long s = est;
        if (s > 1 && tryAdvance(holder)) {
            int n = batch + BATCH_UNIT;
            if (n > s) {
                n = (int) s;
            }
            if (n > MAX_BATCH) {
                n = MAX_BATCH;
            }
            float[] a = new float[n];
            int j = 0;
            do {
                a[j] = holder.value;
            } while (++j < n && tryAdvance(holder));
            batch = j;
            if (est != Long.MAX_VALUE) {
                est -= j;
            }
            return new FloatArraySpliterator(a, 0, j, characteristics());
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
