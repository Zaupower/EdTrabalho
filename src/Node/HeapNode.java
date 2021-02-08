package src.Node;

public class HeapNode<T> extends BinaryTreeNode<T>
{


    protected HeapNode<T> parent;

    public HeapNode<T> getParent() {
        return parent;
    }

    public void setParent(HeapNode<T> parent) {
        this.parent = parent;
    }

    /**
     * Creates a new heap node with the specified data.
     * @param obj
     */
    public HeapNode(T obj)
    {
        super(obj);
        parent = null;
    }


}