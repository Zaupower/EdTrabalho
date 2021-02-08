package src.Listl;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayIterator<T> implements Iterator<T>
{
    private int count;    // the number of elements in the collection
    private int current;  // the current position in the iteration
    private T[] items;

    //-----------------------------------------------------------------
    //  Sets up this iterator using the specified items.
    //-----------------------------------------------------------------
    public ArrayIterator(T[] collection, int size)
    {
        items = collection;
        count = size;
        current = 0;
    }

    /**
     * Method for verify if has next
     * @return boolean of (current < count)
     */
    public boolean hasNext()
    {
        return (current < count);
    }

    /**
     * Method for iterate one array
     * @return items[current - 1]
     */
    public T next()
    {
        if (! hasNext())
            throw new NoSuchElementException();

        current++;
        return items[current - 1];
    }

    public void remove() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException();
    }
}
