package src.Node;

public class LinearNode<E>
{
    private LinearNode<E> next;
    private E element;

    /**
     * Creates an empty node.
     */
    public LinearNode()
    {
        next = null;
        element = null;
    }

    /**
     * Creates a node storing the specified element.
     * @param elem
     */
    public LinearNode(E elem)
    {
        next = null;
        element = elem;
    }

    /**
     *  Returns the node that follows this one.
     * @return LinearNode<E>
     */
    public LinearNode<E> getNext()
    {
        return next;
    }

    /**
     * Sets the node that follows this one.
     * @param node
     */
    public void setNext (LinearNode<E> node)
    {
        next = node;
    }

    /**
     * Returns the element stored in this node.
     * @return E
     */
    public E getElement()
    {
        return element;
    }

    /**
     * Sets the element stored in this node.
     * @param elem
     */
    public void setElement (E elem)
    {
        element = elem;
    }
}
