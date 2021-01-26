package Heap;

import Exceptionsl.EmptyCollectionException;
import Node.HeapNode;
import Tree.LinkedBinaryTree;

public class Heap<T> extends LinkedBinaryTree<T> implements HeapADT<T>
{
   public HeapNode<T> lastNode;

   /******************************************************************
     Constructs an empty heap.
   ******************************************************************/
   public Heap() 
   {
      super();
   }

   /******************************************************************
     Adds the specified element to the heap in the appropriate
     position according to its key value.  Note that equal elements
     are added to the right.
   ******************************************************************/
   public void addElement (T obj) 
   {
      HeapNode<T> node = new HeapNode<T>(obj);

      if (root == null)
         root=node;
      else
      {
         HeapNode<T> next_parent = getNextParentAdd(); 
         if (next_parent.getLeft() == null)
            next_parent.setLeft(node);
         else
            next_parent.setRight(node);
         node.setParent(next_parent);
      }
      lastNode = node;
      count++;
      if (count>1)
         heapifyAdd();
   }

   /******************************************************************
     Returns the node that will be the parent of the new node.
   ******************************************************************/
   private HeapNode<T> getNextParentAdd()
   {
      HeapNode<T> result = lastNode;
      while ((result != root) && (result.getParent().getLeft() != result))
         result = result.getParent();

      if (result != root)
         if (result.getParent().getRight() == null)
            result = result.getParent();
         else
         {
            result = (HeapNode<T>)result.getParent().getRight();
            while (result.getLeft() != null)
               result = (HeapNode<T>)result.getLeft();
         }
      else
         while (result.getLeft() != null)
            result = (HeapNode<T>)result.getLeft();
        
      return result;
   }
   
   /******************************************************************
     Reorders the heap after adding a node.
   ******************************************************************/
   private void heapifyAdd()
   {
      T temp;
      HeapNode<T> next = lastNode;
      
      while ((next != root) && (((Comparable)
              next.getElement()).compareTo(next.getParent().getElement()) < 0))
      {
         temp = next.getElement();
         next.setElement(next.getParent().getElement());
         next.getParent().setElement(temp);
         next = next.getParent();
      }
   }
   
   /******************************************************************
     Remove the element with the lowest value in the heap and
     returns a reference to it.  Throws an EmptyCollectionException
     if the heap is empty.
   ******************************************************************/
   public T removeMin() throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("Empty Heap");

      T minElement =  root.getElement();

      if (count == 1)
      {
         root = null;
         lastNode = null;
      }
      else
      {
         HeapNode<T> next_last = getNewLastNode();
         if (lastNode.getParent().getLeft() == lastNode)
            lastNode.getParent().setLeft(null);
         else
            lastNode.getParent().setRight(null);

         root.setElement(lastNode.getElement());
         lastNode = next_last;
         heapifyRemove();
      }

      count--;
      return minElement;
   }
   
   /******************************************************************
     Reorders the heap after removing the root element.
   ******************************************************************/
   private void heapifyRemove()
   {
      T temp;
      HeapNode<T> node = (HeapNode<T>)root;
      HeapNode<T> left = (HeapNode<T>)node.getLeft();
      HeapNode<T> right = (HeapNode<T>)node.getRight();
      HeapNode<T> next;
      
      if ((left == null) && (right == null))
         next = null;
      else if (left == null)
         next = right;
      else if (right == null)
         next = left;
      else if (((Comparable)left.getElement()).compareTo(right.getElement()) < 0)
         next = left;
      else
         next = right;

      while ((next != null) && (((Comparable)
              next.getElement()).compareTo(node.getElement()) < 0))
      {
         temp = node.getElement();
         node.setElement(next.getElement());
         next.setElement(temp);
         node = next;
         left = (HeapNode<T>)node.getLeft();
         right = (HeapNode<T>)node.getRight();
         if ((left == null) && (right == null))
            next = null;
         else if (left == null)
            next = right;
         else if (right == null)
            next = left;
         else if (((Comparable)left.getElement()).compareTo
                   (right.getElement()) < 0)
            next = left;
         else
            next = right;
      }
   }

   /******************************************************************
     Returns the node that will be the new last node after a remove.
   ******************************************************************/
   private HeapNode<T> getNewLastNode()
   {
      HeapNode<T> result = lastNode;
      
      while ((result != root) && (result.getParent().getLeft() == result))
         result = result.getParent();
      
      if (result != root)
         result = (HeapNode<T>)result.getParent().getLeft();

      while (result.getRight() != null)
         result = (HeapNode<T>)result.getRight();

      return result;
   }
   
   /******************************************************************
     Returns the element with the lowest value in the heap.
     Throws an EmptyCollectionException if the heap is empty.
   ******************************************************************/
   public T findMin () throws EmptyCollectionException
   {
      if (isEmpty())
         throw new EmptyCollectionException ("Empty Heap");

      return root.getElement();
   }
}