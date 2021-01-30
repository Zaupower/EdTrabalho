package src.Queue;

import src.Heap.Heap;
import src.Node.PriorityQueueNode;

import java.util.Iterator;

public class PriorityQueue<T> extends Heap<PriorityQueueNode<T>>
{

    //================================================================
    //  Creates an empty expression tree.
    //================================================================
    public PriorityQueue()
    {
        super();
    }  // constructor PriorityQueue


    //================================================================
    //  adds the given element to the PriorityQueue
    //================================================================
    public void addElement (T object, int priority)
    {
        PriorityQueueNode<T> node = new PriorityQueueNode<T> (object, priority);
        super.addElement(node);

    }  // constructor PriorityQueue


    //Removes an element return new priority queue without the element
    public PriorityQueue<T> remove(T toRemove){

        PriorityQueue<T> prAfterRemoved = new PriorityQueue<>();

        Iterator<PriorityQueueNode<T>> strItr = iteratorLevelOrder();
        while (strItr.hasNext()){

            PriorityQueueNode<T> nextEl = strItr.next();
            if (nextEl != null){
                if (!nextEl.getElement().equals(toRemove)){
                    prAfterRemoved.addElement(nextEl.getElement(), nextEl.getPriority());
                }
            }

        }
        return prAfterRemoved;
    }
    //================================================================
    //  Removes the next highest priority element from the priority queue
    //  and returns a reference to it.
    //================================================================
    public T removeNext()
    {
        PriorityQueueNode<T> temp = (PriorityQueueNode<T>)super.removeMin();
        return temp.getElement();
    }  // method removeNext



}  // class PriorityQueue