package Listl;

import Exceptionsl.ElementNotFoundException;
import Exceptionsl.EmptyCollectionException;
import Node.LinearNode;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T>, ListADT {

    private int counter = 0;
    private LinearNode<T> head, tail;

    /**
     *
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * @param data
     * @return
     */
    public void add(T data) {

        LinearNode<T> newNode = new LinearNode<>(data);

        if (this.head == null) {
            this.head = newNode;
            this.tail = this.head;
        } else {
            this.tail.setNext(newNode);
            this.tail = newNode;
        }
        counter++;
    }

    /**
     * @param data
     */

    public T remove(Object data) throws EmptyCollectionException {

        if (counter == 0) {
            throw new EmptyCollectionException("List is Empty!");
        } else {

            LinearNode current = this.head;
            LinearNode p = null;
            boolean found = false;

            while (current != null && !found) {

                if (current.getElement() == data) {
                    found = true;
                } else {
                    p = current;
                    current = current.getNext();
                }
            }

            if (found == true) {

                if (this.head.getElement().equals(data) || this.head.getElement().equals(null)) { //remover o primeiro com ou sem data
                    this.head.setNext(current.getNext());
                    this.head = current.getNext();

                    if (current.equals(tail)) {
                        this.tail = null;
                    }

                } else if (current != this.tail && current.getElement().equals(data) || current.getElement().equals(null)) { //remover do meio com ou sem data
                    p.setNext(current.getNext());

                } else if (this.tail.getElement().equals(data) || this.tail.getElement().equals(null)) {//remover ultimo com ou sem data
                    p.setNext(tail.getNext());
                    this.tail = p;
                }

                counter--; //decrementar o counter na remoção
                return (T) current;
            } else { //se nao for encontrado lança exception
                throw new ElementNotFoundException("Node Not found!!");
            }
        }
    }


    /**
     * Imprimir todos os elementos da list
     */
    public void printList() throws EmptyCollectionException {
        LinearNode Node = this.head;
        //só imprime se houver elementos na lista
        if (Node != null) {
            do {
                // Print da informação
                System.out.print(Node.toString() + "\n");
                Node = Node.getNext();
            } while (Node != null);
        } else {
            throw new EmptyCollectionException("Can't print, List is Empty!!");
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            throw new EmptyCollectionException("Can't print, List is Empty!!");
        }
        LinearNode<T> oldHead = head;
        LinearNode<T> newHead = head.getNext();
        this.head = newHead;
        return (T) oldHead;
    }

    @Override
    public T removeLast() throws EmptyCollectionException {
        if (isEmpty()) {
            System.out.println("Erro Lista vazia");
        }

        if (counter == 1){
            counter--;
            return (T) tail;

        }
        LinearNode<T> oldTail = tail;

        return (T) oldTail;
    }


    @Override
    public Object first() {
        return this.head;
    }

    @Override
    public Object last() {
        return this.tail;
    }

    public boolean isEmpty() {
        if (head == null || tail == null) {
            return true;
        }
        else return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public boolean contains(Object target)  {
        if (isEmpty())
            return false;

        boolean found = false;
        LinearNode<T> current = head;

        while (current != null && !found)
            if (target.equals (current.getElement()))
                found = true;
            else
            {
                current = current.getNext();
            }
        return found;
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator<T>();
    }

    protected class LinkedListIterator<T> implements Iterator<T>{
        LinearNode<T> current = null;
        @Override
        public boolean hasNext() {
            if (current == null &&  head != null){
                return true;
            }else if (current != null){
                return current.getNext() != null;
            }
            return false;
        }

        @Override
        public T next() {
            if (current == null && head != null){
                current = (LinearNode<T>) head;
                return (T) head.getElement();
            }else if (current != null){
                current = current.getNext();
                return current.getElement();
            }
            throw new NoSuchElementException();
        }
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public LinearNode<T> getHead() {
        return head;
    }

    public void setHead(LinearNode<T> head) {
        this.head = head;
    }

    public LinearNode<T> getTail() {
        return tail;
    }

    public void setTail(LinearNode<T> tail) {
        this.tail = tail;
    }

}