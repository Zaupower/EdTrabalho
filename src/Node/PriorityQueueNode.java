package src.Node;

public class PriorityQueueNode<T> implements Comparable<PriorityQueueNode>
{

    private static int nextorder = 0;
    private int priority;
    private int order;
    private T element;

    /**
     *  Creates a new PriorityQueueNode with the specified data.
     * @param obj
     * @param prio
     */
    public PriorityQueueNode (T obj, int prio)
    {
        element = obj;
        priority = prio;
        order = nextorder;
        nextorder++;
    }



    /**
     * method getElement() returns the element
     * @return T
     */
    public T getElement()
    {

        return element;

    }

    /**
     *  method getPriority() returns the priority
     * @return int priority
     */
    public int getPriority()
    {

        return priority;

    }

    /**
     * method getOrder() returns the order
     * @return order
     */
    public int getOrder()
    {

        return order;

    }

    /**
     *  method compareTo() returns the 1 if the current node has higher
     *  priority than the given node, -1 otherwise
     * @param obj
     * @return
     */
    public int compareTo(PriorityQueueNode obj)
    {
        int result;
        PriorityQueueNode<T> temp = obj;
        if (priority > temp.getPriority())
            result = 1;
        else if (priority < temp.getPriority())
            result = -1;
        else if (order > temp.getOrder())
            result = 1;
        else
            result = -1;
        return result;

    }


}
