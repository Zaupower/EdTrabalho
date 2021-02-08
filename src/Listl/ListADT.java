package src.Listl;

import java.util.Iterator;

public interface ListADT<T>
{
    /**
     * Removes and returns the first element from this list
     * @return T
     */
    public T removeFirst ();

    /**
     * Removes and returns the last element from this list
     * @return T
     */
    public T removeLast ();

    /**
     * Removes and returns the specified element from this list
     *
     * @param element
     * @return T
     */
    public T remove (T element);

    /** Returns a reference to the first element on this list
     *
     * @return T
     */
    public T first ();

    /**  Returns a reference to the last element on this list
     *
     * @return T
     */
    public T last ();

    /** Returns true if this list contains the specified target element
     *
     * @param target
     * @return boolean
     */
    public boolean contains (T target);

    /** Returns true if this list contains no elements
     *
     * @return boolean
     */
    public boolean isEmpty();

    /** Returns the number of elements in this list
     *
     * @return int
     */
    public int size();

    /** Returns an iterator for the elements in this list
     *
     * @return Iterator<T>
     */
    public Iterator<T> iterator();

    /** Returns a string representation of this list
     *
     * @return string
     */
    public String toString();
}