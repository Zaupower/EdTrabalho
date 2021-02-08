package src.Listl;


import src.Exceptionsl.ElementNotFoundException;
import src.Exceptionsl.EmptyCollectionException;

import java.util.Iterator;

public class ArrayList<T> implements ListADT<T>
{
    protected final int DEFAULT_CAPACITY = 100;
    private final int NOT_FOUND = -1;
    protected int rear;
    protected T[] list;


    public ArrayList()
    {
        rear = 0;
        list = (T[])(new Object[DEFAULT_CAPACITY]);
    }


    public ArrayList(int initialCapacity)
    {
        rear = 0;
        list = (T[])(new Object[initialCapacity]);
    }

    /**
     * Method for remove the last item on list
     * @return result
     * @throws EmptyCollectionException
     */
    public T removeLast () throws EmptyCollectionException
    {
        T result;

        if (isEmpty())
            throw new EmptyCollectionException ("list");

        rear--;
        result = list[rear];
        list[rear] = null;

        return result;
    }

    /**
     * Method for remove the first item on list
     * @return result
     * @throws EmptyCollectionException
     */
    public T removeFirst() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("list");

        T result = list[0];
        rear--;
        // shift the elements
        for (int scan=0; scan < rear; scan++)
            list[scan] = list[scan+1];


        list[rear] = null;

        return result;
    }

    /**
     * Method for remove one item on list
     * @param element
     * @return result
     */
    public T remove (T element)
    {
        T result;
        int index = find (element);

        if (index == NOT_FOUND)
            throw new ElementNotFoundException("list");

        result = list[index];
        rear--;
        // shift the appropriate elements
        for (int scan=index; scan < rear; scan++)
            list[scan] = list[scan+1];


        list[rear] = null;

        return result;
    }


    /**
     * Returns a reference to the element at the front of the list.
     * @return list[0]
     * @throws EmptyCollectionException
     */
    public T first() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("list");

        return list[0];
    }

    /**
     * Returns a reference to the element at the rear of the list.
     * @return list[rear-1]
     * @throws EmptyCollectionException
     */
    public T last() throws EmptyCollectionException
    {
        if (isEmpty())
            throw new EmptyCollectionException ("list");

        return list[rear-1];
    }

    /**
     * Returns true if this list contains the specified element.
     * @param target
     * @return (find(target) != NOT_FOUND)
     */
    public boolean contains (T target)
    {
        return (find(target) != NOT_FOUND);
    }

    /**
     * Returns the array index of the specified element, or the
     * constant NOT_FOUND if it is not found.
     * @param target
     * @return result
     */
    private int find (T target)
    {
        int scan = 0, result = NOT_FOUND;
        boolean found = false;

        if (! isEmpty())
            while (! found && scan < rear)
                if (target.equals(list[scan]))
                    found = true;
                else
                    scan++;

        if (found)
            result = scan;

        return result;
    }

    /**
     * Return of index of one item
     * @param index
     * @return result
     */
    public T getByIndex(int index){
        T result = list[index];
        if (index>-1){
            return result;
        }
       return null;
    }

    /**
     * Returns true if this list is empty and false otherwise.
     * @return (rear==0)
     */
    public boolean isEmpty()
    {
        return (rear == 0);
    }

    /**
     * Returns the number of elements currently in this list.
     * @return rear
     */
    public int size()
    {
        return rear;
    }

    /**
     * Returns an iterator for the elements currently in this list.
     * @return ArrayIterator<T> (list, rear)
     */
    public Iterator<T> iterator()
    {
        return new ArrayIterator<T> (list, rear);
    }

    /**
     * Returns a string representation of this list.
     * @return result
     */
    public String toString()
    {
        String result = "";

        for (int scan=0; scan < rear; scan++)
            result = result + list[scan].toString() + "\n";

        return result;
    }

    /**
     *  Creates a new array to store the contents of the list with
     *  twice the capacity of the old one.
     */
    protected void expandCapacity()
    {
        T[] larger = (T[])(new Object[list.length*2]);

        for (int scan=0; scan < list.length; scan++)
            larger[scan] = list[scan];

        list = larger;
    }
}
