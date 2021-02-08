package src.Node;

public class BinaryTreeNode<T> {

    protected T element;
    protected BinaryTreeNode<T> left, right;

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    /**
     * Creates a new tree node with the specified data.
     * @param obj
     */
    public BinaryTreeNode(T obj)
    {
        element = obj;
        left = null;
        right = null;
    }

    /**
     *  Returns the number of non-null children of this node.
     *  This method may be able to be written more efficiently
     * @return children
     */
    public int numChildren()
    {

        int children = 0;

        if (left != null)
            children = 1 + left.numChildren();

        if (right != null)
            children = children + 1 + right.numChildren();

        return children;

    }

}