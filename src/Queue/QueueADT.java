package src.Queue;

public interface QueueADT<T>
{
    /**
     * Adds one element to the rear of the queue
     * @param element
     */
    public void enqueue (T element);

    /**
     * Removes and returns the element at the front of the queue
     * @return T
     */
    public T dequeue();

    /**
     *  Returns without removing the element at the front of the queue
     * @return T
     */
    public T first();

    /**
     * Returns true if the queue contains no elements
     * @return bool
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the queue
     * @return size
     */
    public int size();

    /**
     * Returns a string representation of the queue
     * @return queue to string
     */
    public String toString();
}
