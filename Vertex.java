import Listl.LinkedList;
import Node.LinearNode;

import java.util.ArrayList;
import java.util.List;

class Vertex<T> implements Comparable<Vertex>
{
    T value;

    // variables for Dijkstra Tree
    Vertex previous = null;
    double minDistance = Double.MAX_VALUE;

    LinkedList<Vertex> incoming;
    LinkedList<Vertex> outgoing;
    State state;

    /**
     * Creates new Vertex with value T
     * @param value T
     */
    public Vertex(T value)
    {
        this.value = value;
        this.incoming = new LinkedList<>();
        this.outgoing = new LinkedList<>();
        state = State.UNVISITED;
    }

    /**
     * @param other Vertex to compare to
     */
    @Override
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

    /**
     * Add Vertex to adjacent incoming list
     * @param vert Vertex of incoming adjacent
     */
    public void addIncoming(Vertex vert)
    {
        incoming.add(vert);
    }
    public void addOutgoing(Vertex vert)
    {
        outgoing.add(vert);
    }

    /**
     * Get string of Vertex with all it's ingoing and outgoing adjacencies
     * @ return string
     */
    @Override
    public String toString() {
        return "Vertex{" +
                "value=" + value +
                '}';
    }
}