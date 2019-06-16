package com.xenoamess.commons.primitive.collections.lists;

import com.xenoamess.commons.primitive.Primitive;
import com.xenoamess.commons.primitive.collections.AbstractDoubleCollection;
import com.xenoamess.commons.primitive.collections.DoubleCollection;
import com.xenoamess.commons.primitive.functions.DoubleConsumer;
import com.xenoamess.commons.primitive.iterators.DoubleIterator;
import com.xenoamess.commons.primitive.iterators.DoubleListIterator;
import com.xenoamess.commons.primitive.iterators.DoubleSpliterator;

import java.util.*;
import java.util.function.Consumer;


/**
 * This class provides a skeletal implementation of the {@link java.util.List}
 * interface to minimize the effort required to implement this interface
 * backed by a "random access" data store (such as an array).  For sequential
 * access data (such as a linked list), {@link java.util.AbstractSequentialList} should
 * be used in preference to this class.
 *
 * <p>To implement an unmodifiable list, the programmer needs only to extend
 * this class and provide implementations for the {@link #get(int)} and
 * {@link java.util.List#size() size()} methods.
 *
 * <p>To implement a modifiable list, the programmer must additionally
 * override the {@link #set(int, Double) set(int, E)} method (which otherwise
 * throws an {@code UnsupportedOperationException}).  If the list is
 * variable-size the programmer must additionally override the
 * {@link #add(int, Double) add(int, E)} and {@link #remove(int)} methods.
 *
 * <p>The programmer should generally provide a void (no argument) and collection
 * constructor, as per the recommendation in the {@link java.util.Collection} interface
 * specification.
 *
 * <p>Unlike the other abstract collection implementations, the programmer does
 * <i>not</i> have to provide an iterator implementation; the iterator and
 * list iterator are implemented by this class, on top of the "random access"
 * methods:
 * {@link #get(int)},
 * {@link #set(int, Double) set(int, E)},
 * {@link #add(int, Double) add(int, E)} and
 * {@link #remove(int)}.
 *
 * <p>The documentation for each non-abstract method in this class describes its
 * implementation in detail.  Each of these methods may be overridden if the
 * collection being implemented admits a more efficient implementation.
 *
 * <p>This class is a member of the
 * <a href="{@docRoot}/java.base/java/util/package-summary.html#CollectionsFramework">
 * Java Collections Framework</a>.
 *
 * @author Josh Bloch
 * @author Neal Gafter
 * @author XenoAmess
 * @version 0.8.0
 * @see AbstractList
 * @since 1.2
 */
public abstract class AbstractDoubleList extends AbstractList<Double> implements AbstractDoubleCollection, DoubleList
        , Primitive {
    /**
     * Sole constructor.  (For invocation by subclass constructors, typically
     * implicit.)
     */
    public AbstractDoubleList() {
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return AbstractDoubleCollection.toString(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Double get(int index) {
        return this.getPrimitive(index);
    }

    /**
     * {@inheritDoc}
     *
     * @throws UnsupportedOperationException {@inheritDoc}
     * @throws ClassCastException            {@inheritDoc}
     * @throws NullPointerException          {@inheritDoc}
     * @implSpec This implementation iterates over the collection looking for the
     * specified element.  If it finds the element, it removes the element
     * from the collection using the iterator's remove method.
     *
     * <p>Note that this implementation throws an
     * {@code UnsupportedOperationException} if the iterator returned by this
     * collection's iterator method does not implement the {@code remove}
     * method and this collection contains the specified object.
     */
    @Override
    public final boolean remove(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Double)) {
            return false;
        }
        return this.removeByContentPrimitive((Double) o);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation always throws an
     * {@code UnsupportedOperationException}.
     */
    @Override
    public final Double remove(int index) {
        return this.removeByIndex(index);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over the elements in the collection,
     * checking each element in turn for equality with the specified element.
     */
    @Override
    public final boolean contains(Object o) {
        return DoubleList.super.contains(o);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation iterates over the elements in the collection,
     * checking each element in turn for equality with the specified element.
     */
    @Override
    public final boolean contains(double o) {
        return DoubleList.super.contains(o);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation returns an array containing all the elements
     * returned by this collection's iterator, in the same order, stored in
     * consecutive elements of the array, starting with index {@code 0}.
     * The length of the returned array is equal to the number of elements
     * returned by the iterator, even if the size of this collection changes
     * during iteration, as might happen if the collection permits
     * concurrent modification during iteration.  The {@code size} method is
     * called only as an optimization hint; the correct result is returned
     * even if the iterator returns a different number of elements.
     *
     * <p>This method is equivalent to:
     *
     * <pre> {@code
     * List<E> list = new ArrayList<E>(size());
     * for (E e : this)
     *     list.add(e);
     * return list.toArray();
     * }</pre>
     */
    @Override
    public double[] toArrayPrimitive() {
        return AbstractDoubleCollection.super.toArrayPrimitive();
    }

    //----------

    /**
     * {@inheritDoc}
     * <p>
     * Appends the specified element to the end of this list (optional
     * operation).
     *
     * <p>Lists that support this operation may place limitations on what
     * elements may be added to this list.  In particular, some
     * lists will refuse to add null elements, and others will impose
     * restrictions on the type of elements that may be added.  List
     * classes should clearly specify in their documentation any restrictions
     * on what elements may be added.
     *
     * @implSpec This implementation calls {@code add(size(), e)}.
     *
     * <p>Note that this implementation throws an
     * {@code UnsupportedOperationException} unless
     * {@link #add(int, Double) add(int, E)} is overridden.
     */
    @Override
    public final boolean add(Double e) {
        return this.addPrimitive(e);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of add(Double e)
     *
     * @see #add(Double e)
     */
    @Override
    public final boolean add(double e) {
        return addPrimitive(e);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of add(Double e)
     *
     * @implSpec This implementation calls {@code add(size(), e)}.
     *
     * <p>Note that this implementation throws an
     * {@code UnsupportedOperationException} unless
     * {@link #add(int, Double) add(int, E)} is overridden.
     * @see #add(Double e)
     */
    @Override
    public boolean addPrimitive(double e) {
        this.add(size(), e);
        return true;
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation always throws an
     * {@code UnsupportedOperationException}.
     */
    @Override
    public final Double set(int index, Double element) {
        return this.setPrimitive(index, element);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of set(int index, Double element)
     *
     * @implSpec This implementation always throws an
     * {@code UnsupportedOperationException}.
     */
    @Override
    public final double set(int index, double element) {
        return this.setPrimitive(index, element);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of set(int index, Double element)
     *
     * @implSpec This implementation always throws an
     * {@code UnsupportedOperationException}.
     */
    @Override
    public double setPrimitive(int index, double element) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation always throws an
     * {@code UnsupportedOperationException}.
     */
    @Override
    public final void add(int index, Double element) {
        this.addPrimitive(index, element);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of add(int index, Double element)
     *
     * @see #add(int index, Double element)
     */
    @Override
    public final void add(int index, double element) {
        this.addPrimitive(index, element);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of add(int index, Double element)
     *
     * @see #add(int index, Double element)
     */
    @Override
    public void addPrimitive(int index, double element) {
        throw new UnsupportedOperationException();
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of remove(int index)
     *
     * @see #remove(int index)
     */
    public final Double removeByIndex(int index) {
        return this.removeByIndexPrimitive(index);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of remove(int index)
     *
     * @see #remove(int index)
     */
    @Override
    public double removeByIndexPrimitive(int index) {
        throw new UnsupportedOperationException();
    }


    // Search Operations

    /**
     * Returns the index of the first occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the lowest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in
     * this list, or -1 if this list does not contain the element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this list
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              list does not permit null elements
     *                              (<a href="Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public final int indexOf(Object o) {
        return DoubleList.super.indexOf(o);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of indexOf(Object o)
     *
     * @see #indexOf(Object o)
     */
    @Override
    public final int indexOf(double o) {
        return DoubleList.super.indexOf(o);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of indexOf(Object o)
     *
     * @see #indexOf(Object o)
     */
    @Override
    public int indexOfPrimitive(double o) {
        DoubleListIterator it = listIterator();
        while (it.hasNext()) {
            if (o == it.nextPrimitive()) {
                return it.previousIndex();
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     */
    @Override
    public final int lastIndexOf(Object o) {
        return DoubleList.super.lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the index of the last occurrence of the specified element
     * in this list, or -1 if this list does not contain the element.
     * More formally, returns the highest index {@code i} such that
     * {@code Objects.equals(o, get(i))},
     * or -1 if there is no such index.
     */
    @Override
    public final int lastIndexOf(double o) {
        return DoubleList.super.lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     * <p>
     * Primitive replacement of lastIndexOf(Object o)
     *
     * @see #lastIndexOf(Object o)
     */
    @Override
    public int lastIndexOfPrimitive(double o) {
        DoubleListIterator it = listIterator(size());
        while (it.hasPrevious()) {
            if (o == it.previousPrimitive()) {
                return it.nextIndex();
            }
        }
        return -1;
    }


    // Bulk Operations

    /**
     * {@inheritDoc}
     * <p>
     * Removes all of the elements from this list (optional operation).
     * The list will be empty after this call returns.
     *
     * @implSpec This implementation calls {@code removeRange(0, size())}.
     *
     * <p>Note that this implementation throws an
     * {@code UnsupportedOperationException} unless {@code remove(int
     * index)} or {@code removeRange(int fromIndex, int toIndex)} is
     * overridden.
     */
    @Override
    public void clear() {
        removeRange(0, size());
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation gets an iterator over the specified collection
     * and iterates over it, inserting the elements obtained from the
     * iterator into this list at the appropriate position, one at a time,
     * using {@code add(int, E)}.
     * Many implementations will override this method for efficiency.
     *
     * <p>Note that this implementation throws an
     * {@code UnsupportedOperationException} unless
     * {@link #add(int, Double) add(int, E)} is overridden.
     */
    @Override
    public boolean addAll(int index, Collection<? extends Double> c) {
        rangeCheckForAdd(index);
        boolean modified = false;

        if (c instanceof DoubleCollection) {
            DoubleCollection cDoubleCollection = (DoubleCollection) c;
            DoubleIterator cDoubleCollectionIterator = cDoubleCollection.iterator();
            while (cDoubleCollectionIterator.hasNext()) {
                addPrimitive(index++, cDoubleCollectionIterator.nextPrimitive());
                modified = true;
            }
        } else {
            for (Double e : c) {
                add(index++, e);
                modified = true;
            }
        }
        return modified;
    }


    // Iterators

    /**
     * {@inheritDoc}
     * <p>
     * Returns an iterator over the elements in this list in proper sequence.
     *
     * @implSpec This implementation returns a straightforward implementation of the
     * iterator interface, relying on the backing list's {@code size()},
     * {@code get(int)}, and {@code remove(int)} methods.
     *
     * <p>Note that the iterator returned by this method will throw an
     * {@link java.lang.UnsupportedOperationException} in response to its
     * {@code remove} method unless the list's {@code remove(int)} method is
     * overridden.
     *
     * <p>This implementation can be made to throw runtime exceptions in the
     * face of concurrent modification, as described in the specification
     * for the (protected) {@link #modCount} field.
     */
    @Override
    public DoubleIterator iterator() {
        return new AbstractDoubleList.Itr();
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation returns {@code listIterator(0)}.
     * @see #listIterator(int)
     */
    @Override
    public DoubleListIterator listIterator() {
        return listIterator(0);
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation returns a straightforward implementation of the
     * {@code ListIterator} interface that extends the implementation of the
     * {@code Iterator} interface returned by the {@code iterator()} method.
     * The {@code ListIterator} implementation relies on the backing list's
     * {@code get(int)}, {@code set(int, E)}, {@code add(int, E)}
     * and {@code remove(int)} methods.
     *
     * <p>Note that the list iterator returned by this implementation will
     * throw an {@link java.lang.UnsupportedOperationException} in response to its
     * {@code remove}, {@code set} and {@code add} methods unless the
     * list's {@code remove(int)}, {@code set(int, E)}, and
     * {@code add(int, E)} methods are overridden.
     *
     * <p>This implementation can be made to throw runtime exceptions in the
     * face of concurrent modification, as described in the specification for
     * the (protected) {@link #modCount} field.
     */
    @Override
    public DoubleListIterator listIterator(final int index) {
        rangeCheckForAdd(index);

        return new AbstractDoubleList.ListItr(index);
    }

    private class Itr implements DoubleIterator {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        int cursor = 0;

        /**
         * Index of element returned by most recent call to next or
         * previous.  Reset to -1 if this element is deleted by a call
         * to remove.
         */
        int lastRet = -1;

        /**
         * The modCount value that the iterator believes that the backing
         * List should have.  If this expectation is violated, the iterator
         * has detected concurrent modification.
         */
        int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return cursor != size();
        }

        @Override
        public double nextPrimitive() {
            checkForComodification();
            try {
                int i = cursor;
                double next = getPrimitive(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        @Override
        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                AbstractDoubleList.this.removeByIndexPrimitive(lastRet);
                if (lastRet < cursor) {
                    cursor--;
                }
                lastRet = -1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends AbstractDoubleList.Itr implements DoubleListIterator {
        ListItr(int index) {
            cursor = index;
        }

        @Override
        public boolean hasPrevious() {
            return cursor != 0;
        }

        @Override
        public double previousPrimitive() {
            checkForComodification();
            try {
                int i = cursor - 1;
                double previous = getPrimitive(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                checkForComodification();
                throw new NoSuchElementException();
            }
        }

        @Override
        public int nextIndex() {
            return cursor;
        }

        @Override
        public int previousIndex() {
            return cursor - 1;
        }

        @Override
        public void setPrimitive(double e) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            checkForComodification();

            try {
                AbstractDoubleList.this.setPrimitive(lastRet, e);
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        @Override
        public void addPrimitive(double e) {
            checkForComodification();

            try {
                int i = cursor;
                AbstractDoubleList.this.addPrimitive(i, e);
                lastRet = -1;
                cursor = i + 1;
                expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * {@inheritDoc}
     *
     * @implSpec This implementation returns a list that subclasses
     * {@code AbstractList}.  The subclass stores, in private fields, the
     * size of the subList (which can change over its lifetime), and the
     * expected {@code modCount} value of the backing list.  There are two
     * variants of the subclass, one of which implements {@code RandomAccess}.
     * If this list implements {@code RandomAccess} the returned list will
     * be an instance of the subclass that implements {@code RandomAccess}.
     *
     * <p>The subclass's {@code set(int, E)}, {@code get(int)},
     * {@code add(int, E)}, {@code remove(int)}, {@code addAll(int,
     * Collection)} and {@code removeRange(int, int)} methods all
     * delegate to the corresponding methods on the backing abstract list,
     * after bounds-checking the index and adjusting for the offset.  The
     * {@code addAll(Collection c)} method merely returns {@code addAll(size,
     * c)}.
     *
     * <p>The {@code listIterator(int)} method returns a "wrapper object"
     * over a list iterator on the backing list, which is created with the
     * corresponding method on the backing list.  The {@code iterator} method
     * merely returns {@code listIterator()}, and the {@code size} method
     * merely returns the subclass's {@code size} field.
     *
     * <p>All methods first check to see if the actual {@code modCount} of
     * the backing list is equal to its expected value, and throw a
     * {@code ConcurrentModificationException} if it is not.
     */
    @Override
    public DoubleList subList(int fromIndex, int toIndex) {
        subListRangeCheck(fromIndex, toIndex, size());
        return (this instanceof RandomAccess ?
                new AbstractDoubleList.DoubleRandomAccessSubList(this, fromIndex, toIndex) :
                new AbstractDoubleList.DoubleSubList(this, fromIndex, toIndex));
    }

    /**
     * {@inheritDoc}
     * <p>
     * A copy of AbstractList.subListRangeCheck(int fromIndex, int toIndex, int size)
     * I just cannot understand why they choose to make it package private, so I have to copy it.
     * But anyway, they might have their reasons.
     */
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

    // Comparison and hashing

    /**
     * {@inheritDoc}
     * <p>
     * Compares the specified object with this list for equality.  Returns
     * {@code true} if and only if the specified object is also a list, both
     * lists have the same size, and all corresponding pairs of elements in
     * the two lists are <i>equal</i>.  (Two elements {@code e1} and
     * {@code e2} are <i>equal</i> if {@code (e1==null ? e2==null :
     * e1.equals(e2))}.)  In other words, two lists are defined to be
     * equal if they contain the same elements in the same order.
     *
     * @implSpec This implementation first checks if the specified object is this
     * list. If so, it returns {@code true}; if not, it checks if the
     * specified object is a list. If not, it returns {@code false}; if so,
     * it iterates over both lists, comparing corresponding pairs of elements.
     * If any comparison returns {@code false}, this method returns
     * {@code false}.  If either iterator runs out of elements before the
     * other it returns {@code false} (as the lists are of unequal length);
     * otherwise it returns {@code true} when the iterations complete.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof List)) {
            return false;
        }

        DoubleListIterator e1 = listIterator();
        List<?> oList = (List<?>) o;
        if (oList instanceof DoubleList) {
            DoubleList oDoubleList = (DoubleList) oList;
            DoubleListIterator e2 = oDoubleList.listIterator();
            while (e1.hasNext() && e2.hasNext()) {
                double o1 = e1.nextPrimitive();
                double o2 = e2.nextPrimitive();
                if (o1 != o2) {
                    return false;
                }
            }
            return !(e1.hasNext() || e2.hasNext());
        } else {
            ListIterator<?> e2 = ((List<?>) o).listIterator();
            while (e1.hasNext() && e2.hasNext()) {
                double o1 = e1.nextPrimitive();
                Object o2 = e2.next();
                if (o2 == null || !o2.equals(o1)) {
                    return false;
                }
            }
            return !(e1.hasNext() || e2.hasNext());
        }

    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns the hash code value for this list.
     *
     * @implSpec This implementation uses exactly the code that is used to define the
     * list hash function in the documentation for the {@link java.util.List#hashCode}
     * method.
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        DoubleIterator iterator = this.iterator();
        while (iterator.hasNext()) {
            double e = iterator.nextPrimitive();
            hashCode = 31 * hashCode + Double.hashCode(e);
        }
        return hashCode;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Removes from this list all of the elements whose index is between
     * {@code fromIndex}, inclusive, and {@code toIndex}, exclusive.
     * Shifts any succeeding elements to the left (reduces their index).
     * This call shortens the list by {@code (toIndex - fromIndex)} elements.
     * (If {@code toIndex==fromIndex}, this operation has no effect.)
     *
     * <p>This method is called by the {@code clear} operation on this list
     * and its subLists.  Overriding this method to take advantage of
     * the internals of the list implementation can <i>substantially</i>
     * improve the performance of the {@code clear} operation on this list
     * and its subLists.
     *
     * @implSpec This implementation gets a list iterator positioned before
     * {@code fromIndex}, and repeatedly calls {@code ListIterator.next}
     * followed by {@code ListIterator.remove} until the entire range has
     * been removed.  <b>Note: if {@code ListIterator.remove} requires linear
     * time, this implementation requires quadratic time.</b>
     */
    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        DoubleListIterator it = listIterator(fromIndex);
        for (int i = 0, n = toIndex - fromIndex; i < n; i++) {
            it.nextPrimitive();
            it.remove();
        }
    }

    /**
     * The number of times this list has been <i>structurally modified</i>.
     * Structural modifications are those that change the size of the
     * list, or otherwise perturb it in such a fashion that iterations in
     * progress may yield incorrect results.
     *
     * <p>This field is used by the iterator and list iterator implementation
     * returned by the {@code iterator} and {@code listIterator} methods.
     * If the value of this field changes unexpectedly, the iterator (or list
     * iterator) will throw a {@code ConcurrentModificationException} in
     * response to the {@code next}, {@code remove}, {@code previous},
     * {@code set} or {@code add} operations.  This provides
     * <i>fail-fast</i> behavior, rather than non-deterministic behavior in
     * the face of concurrent modification during iteration.
     *
     * <p><b>Use of this field by subclasses is optional.</b> If a subclass
     * wishes to provide fail-fast iterators (and list iterators), then it
     * merely has to increment this field in its {@code add(int, E)} and
     * {@code remove(int)} methods (and any other methods that it overrides
     * that result in structural modifications to the list).  A single call to
     * {@code add(int, E)} or {@code remove(int)} must add no more than
     * one to this field, or the iterators (and list iterators) will throw
     * bogus {@code ConcurrentModificationExceptions}.  If an implementation
     * does not wish to provide fail-fast iterators, this field may be
     * ignored.
     */
    public transient int modCount = 0;

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private String outOfBoundsMsg(int index) {
        return "Index: " + index + ", Size: " + size();
    }

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
    static final class DoubleRandomAccessSpliterator implements DoubleSpliterator {

        private final DoubleList list;
        private int index; // current index, modified on advance/split
        private int fence; // -1 until used; then one past last index

        // The following fields are valid if covering an AbstractList
        private final AbstractDoubleList alist;
        private int expectedModCount; // initialized when fence set

        DoubleRandomAccessSpliterator(DoubleList list) {
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
        private DoubleRandomAccessSpliterator(AbstractDoubleList.DoubleRandomAccessSpliterator parent,
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
                    new AbstractDoubleList.DoubleRandomAccessSpliterator(this, lo, index = mid);
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

    private static class DoubleSubList extends AbstractDoubleList {
        private final AbstractDoubleList root;
        private final AbstractDoubleList.DoubleSubList parent;
        private final int offset;
        protected int size;

        /**
         * Constructs a sublist of an arbitrary AbstractList, which is
         * not a SubList itself.
         */
        public DoubleSubList(AbstractDoubleList root, int fromIndex, int toIndex) {
            this.root = root;
            this.parent = null;
            this.offset = fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        /**
         * Constructs a sublist of another SubList.
         */
        protected DoubleSubList(AbstractDoubleList.DoubleSubList parent, int fromIndex, int toIndex) {
            this.root = parent.root;
            this.parent = parent;
            this.offset = parent.offset + fromIndex;
            this.size = toIndex - fromIndex;
            this.modCount = root.modCount;
        }

        @Override
        public double setPrimitive(int index, double element) {
            AbstractDoubleList.checkIndex(index, size);
            checkForComodification();
            return root.setPrimitive(offset + index, element);
        }

        @Override
        public double getPrimitive(int index) {
            AbstractDoubleList.checkIndex(index, size);
            checkForComodification();
            return root.getPrimitive(offset + index);
        }

        @Override
        public int size() {
            checkForComodification();
            return size;
        }

        @Override
        public void addPrimitive(int index, double element) {
            rangeCheckForAdd(index);
            checkForComodification();
            root.addPrimitive(offset + index, element);
            updateSizeAndModCount(1);
        }

        @Override
        public double removeByIndexPrimitive(int index) {
            AbstractDoubleList.checkIndex(index, size);
            checkForComodification();
            double result = root.removeByIndexPrimitive(offset + index);
            updateSizeAndModCount(-1);
            return result;
        }

        @Override
        protected void removeRange(int fromIndex, int toIndex) {
            checkForComodification();
            root.removeRange(offset + fromIndex, offset + toIndex);
            updateSizeAndModCount(fromIndex - toIndex);
        }

        @Override
        public boolean addAll(Collection<? extends Double> c) {
            return addAll(size, c);
        }

        @Override
        public boolean addAll(int index, Collection<? extends Double> c) {
            rangeCheckForAdd(index);
            int cSize = c.size();
            if (cSize == 0) {
                return false;
            }
            checkForComodification();
            root.addAll(offset + index, c);
            updateSizeAndModCount(cSize);
            return true;
        }

        @Override
        public DoubleIterator iterator() {
            return listIterator();
        }

        @Override
        public DoubleListIterator listIterator(int index) {
            checkForComodification();
            rangeCheckForAdd(index);

            return new DoubleListIterator() {
                private final DoubleListIterator i =
                        root.listIterator(offset + index);

                @Override
                public boolean hasNext() {
                    return nextIndex() < size;
                }

                @Override
                public double nextPrimitive() {
                    if (hasNext()) {
                        return i.nextPrimitive();
                    } else {
                        throw new NoSuchElementException();
                    }
                }

                @Override
                public boolean hasPrevious() {
                    return previousIndex() >= 0;
                }

                @Override
                public double previousPrimitive() {
                    if (hasPrevious()) {
                        return i.previousPrimitive();
                    } else {
                        throw new NoSuchElementException();
                    }
                }

                @Override
                public int nextIndex() {
                    return i.nextIndex() - offset;
                }

                @Override
                public int previousIndex() {
                    return i.previousIndex() - offset;
                }

                @Override
                public void remove() {
                    i.remove();
                    updateSizeAndModCount(-1);
                }

                @Override
                public void setPrimitive(double e) {
                    i.setPrimitive(e);
                }

                @Override
                public void addPrimitive(double e) {
                    i.addPrimitive(e);
                    updateSizeAndModCount(1);
                }
            };
        }

        @Override
        public DoubleList subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new AbstractDoubleList.DoubleSubList(this, fromIndex, toIndex);
        }

        private void rangeCheckForAdd(int index) {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
            }
        }

        private String outOfBoundsMsg(int index) {
            return "Index: " + index + ", Size: " + size;
        }

        private void checkForComodification() {
            if (root.modCount != this.modCount) {
                throw new ConcurrentModificationException();
            }
        }

        private void updateSizeAndModCount(int sizeChange) {
            AbstractDoubleList.DoubleSubList slist = this;
            do {
                slist.size += sizeChange;
                slist.modCount = root.modCount;
                slist = slist.parent;
            } while (slist != null);
        }
    }

    private static class DoubleRandomAccessSubList
            extends AbstractDoubleList.DoubleSubList implements RandomAccess {

        /**
         * Constructs a sublist of an arbitrary AbstractList, which is
         * not a RandomAccessSubList itself.
         */
        DoubleRandomAccessSubList(AbstractDoubleList root,
                                  int fromIndex, int toIndex) {
            super(root, fromIndex, toIndex);
        }

        /**
         * Constructs a sublist of another RandomAccessSubList.
         */
        DoubleRandomAccessSubList(AbstractDoubleList.DoubleRandomAccessSubList parent,
                                  int fromIndex, int toIndex) {
            super(parent, fromIndex, toIndex);
        }

        @Override
        public DoubleList subList(int fromIndex, int toIndex) {
            subListRangeCheck(fromIndex, toIndex, size);
            return new AbstractDoubleList.DoubleRandomAccessSubList(this, fromIndex, toIndex);
        }
    }

    /**
     * Checks if the {@code index} is within the bounds of the range from
     * {@code 0} (inclusive) to {@code length} (exclusive).
     * <p>
     * This function is used here in replacement of java.util.AbstractDoubleList.checkIndex(int index, int length),
     * as it is
     * only since java 9.
     *
     * <p>The {@code index} is defined to be out of bounds if any of the
     * following inequalities is true:
     * <ul>
     * <li>{@code index < 0}</li>
     * <li>{@code index >= length}</li>
     * <li>{@code length < 0}, which is implied from the former inequalities</li>
     * </ul>
     *
     * @param index  the index
     * @param length the upper-bound (exclusive) of the range
     * @return {@code index} if it is within bounds of the range
     * @throws java.lang.IndexOutOfBoundsException if the {@code index} is out of bounds
     * @see java.util.Objects#checkIndex(int index, int length)
     * @since 8
     */
    public static int checkIndex(int index, int length) {
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Index out of range: " + index);
        }
        return index;
    }


}
