package src.Tree;

import src.Exceptionsl.ElementNotFoundException;
import src.Listl.ArrayUnorderedList;
import src.Node.BinaryTreeNode;

import java.util.Iterator;

/**
 * Classe de arvore binaria
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T>
{

    protected int count;
    protected BinaryTreeNode<T> root;

    /**
     * Creates an empty binary tree.
     */
    public LinkedBinaryTree()
    {
        count = 0;
        root = null;
    }  // constructor LinkedBinaryTree

    //================================================================
    //  Creates a binary tree with the specified element as its root.
    //================================================================

    /**
     * Creates a binary tree with the specified element as its root.
     * @param element
     */
    public LinkedBinaryTree (T element)
    {
        count = 1;
        root = new BinaryTreeNode<T> (element);
    }  // constructor LinkedBinaryTree

    //================================================================
    //  Constructs a binary tree from the two specified binary trees.
    //================================================================

    /**
     * Constructs a binary tree from the two specified binary trees.
     * @param element
     * @param leftSubtree
     * @param rightSubtree
     */
    public LinkedBinaryTree (T element, LinkedBinaryTree<T> leftSubtree,
                             LinkedBinaryTree<T> rightSubtree)
    {

        root = new BinaryTreeNode<T> (element);
        count = 1;
        if (leftSubtree != null)
        {
            count = count + leftSubtree.size();
            root.setLeft(leftSubtree.root);
        }//if
        else
            root.setLeft(null);
        if (rightSubtree !=null)
        {
            count = count + rightSubtree.size();
            root.setRight(rightSubtree.root);
        }//if
        else
            root.setRight(null);

    }  // constructor LinkedBinaryTree

    //================================================================
    //  Removes the left subtree of this binary tree.
    //================================================================

    /**
     * Removes the left subtree of this binary tree.
     */
    public void removeLeftSubtree()
    {
        if (root.getLeft() != null)
            count = count - root.getLeft().numChildren() - 1;
        root.setLeft(null);
    }  // method removeLeftSubtree

    //================================================================
    //  Removes the right subtree of this binary tree.
    //================================================================
    public void removeRightSubtree()
    {
        if (root.getRight() != null)
            count = count - root.getRight().numChildren() - 1;
        root.setRight(null);
    }  // method removeRightSubtree

    //================================================================
    //  Deletes all nodes from the binary tree.
    //================================================================
    public void removeAllElements()
    {
        count = 0;
        root = null;
    }  // method removeAllElements

    @Override
    public T getRoot() {
        return (T) this.root;
    }

    //================================================================
    //  Returns true if the binary tree is empty and false otherwise.
    //================================================================
    public boolean isEmpty()
    {
        return (count == 0);
    }  // method isEmpty

    //================================================================
    //  Returns true if the binary tree is empty and false otherwise.
    //================================================================
    public int size()
    {
        return count;
    }  // method size

    //================================================================
    //  Returns true if the tree contains an element that matches the
    //  specified target element and false otherwise.
    //================================================================
    public boolean contains (T targetElement)
    {

        T temp;
        boolean found = false;

        try
        {
            temp = find (targetElement);
            found = true;
        }//try

        catch (Exception ElementNotFoundException)
        {
            found = false;
        }//catch

        return found;

    }  // method contains

    //================================================================
    //  Returns a reference to the specified target element if it is
    //  found in the binary tree.  Throws a NoSuchElementException if
    //  the specified target element is not found in the binary tree.
    //================================================================
    public T find(T targetElement) throws ElementNotFoundException {
        BinaryTreeNode<T> current = findagain( targetElement, root );
        if( current == null )
            throw new ElementNotFoundException("binarytree");
        return (current.getElement());
    } // method find

    //================================================================
    //  Returns a reference to the specified target element if it is
    //  found in the binary tree.
    //================================================================
    private BinaryTreeNode<T> findagain(T targetElement, BinaryTreeNode<T> next) {
        if (next == null) {
            return null;
        }
        if (next.getElement().equals(targetElement)) {
            return next;
        }
        BinaryTreeNode<T> temp = findagain(targetElement, next.getLeft());
        if (temp == null) {
            temp = findagain(targetElement, next.getRight());
        }
        return temp;
    } // method findagain


    //================================================================
    //  Returns a string representation of the binary tree.
    //================================================================
    public String toString()
    {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        preorder (root, templist);
        return templist.toString();
    }  // method toString

    //================================================================
    //  Performs an inorder traversal on the binary tree by calling an
    //  overloaded, recursive inorder method that starts with
    //  the root.
    //================================================================
    public Iterator<T> iteratorInOrder()
    {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        inorder (root, templist);
        return templist.iterator();
    }  // method inorder

    //================================================================
    //  Performs a recursive inorder traversal.
    //================================================================
    protected void inorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> templist)
    {

        if (node != null)
        {
            inorder (node.getLeft(), templist);
            templist.addToRear(node.getElement());
            inorder (node.getRight(), templist);
        }//if

    }  // method inorder

    //================================================================
    //  Performs an preorder traversal on the binary tree by calling an
    //  overloaded, recursive preorder method that starts with
    //  the root.
    //================================================================
    public Iterator<T> iteratorPreOrder()
    {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        preorder (root, templist);
        return templist.iterator();
    }  // method preorder

    //================================================================
    //  Performs a recursive preorder traversal.
    //================================================================
    protected void preorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> templist)
    {

        if (node != null)
        {
            templist.addToRear(node.getElement());
            preorder (node.getLeft(), templist);
            preorder (node.getRight(), templist);
        }//if

    }  // method preorder

    //================================================================
    //  Performs an postorder traversal on the binary tree by calling
    //  an overloaded, recursive postorder method that starts
    //  with the root.
    //================================================================
    public Iterator<T> iteratorPostOrder()
    {
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        postorder (root, templist);
        return templist.iterator();
    }  // method postorder

    //================================================================
    //  Performs a recursive postorder traversal.
    //================================================================
    protected void postorder (BinaryTreeNode<T> node, ArrayUnorderedList<T> templist)
    {

        if (node != null)
        {
            postorder (node.getLeft(), templist);
            postorder (node.getRight(), templist);
            templist.addToRear(node.getElement());
        }//if

    }  // method postorder

    //================================================================
    //  Performs a levelorder traversal on the binary tree, using a
    //  templist.
    //================================================================
    public Iterator<T> iteratorLevelOrder()
    {

        ArrayUnorderedList<BinaryTreeNode<T>> nodes = new ArrayUnorderedList<>();
        ArrayUnorderedList<T> templist = new ArrayUnorderedList<T>();
        BinaryTreeNode<T> current;

        nodes.addToRear (root);

        while (! nodes.isEmpty())
        {
            current = (BinaryTreeNode<T>)nodes.removeFirst();

            if (current != null)
            {
                templist.addToRear(current.getElement());
                nodes.addToRear (current.getLeft());
                nodes.addToRear (current.getRight());
            }//if
            else
                templist.addToRear(null);
        }//while
        return templist.iterator();
    }  // method levelorder
}  // class BinaryTree