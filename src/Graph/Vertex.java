package src.Graph;

import src.Listl.LinkedList;

public class Vertex<T> implements Comparable<Vertex<T>>
{
    protected T value;

    //Variaveis para definir o caminho mais curto reverso
    Vertex previous = null;
    double minDistance = Double.MAX_VALUE;

    LinkedList<Vertex<T>> incoming;
    LinkedList<Vertex<T>> outgoing;
    State state;

    /**
     * Creates new src.Graph.Vertex with value T
     * @param value T
     */
    public Vertex(T value)
    {
        this.value = value;
        this.incoming = new LinkedList<>();
        this.outgoing = new LinkedList<>();
        state = State.UNVISITED;
    }

    public Vertex() {

    }

    /**
     * @param other src.Graph.Vertex to compare to
     */
    @Override
    public int compareTo(Vertex other)
    {
        return Double.compare(minDistance, other.minDistance);
    }

    /**
     * Add src.Graph.Vertex to adjacent incoming list
     * @param vert src.Graph.Vertex of incoming adjacent
     */
    public void addIncoming(Vertex<T> vert)
    {
        incoming.add(vert);
    }
    public void addOutgoing(Vertex<T> vert)
    {
        outgoing.add(vert);
    }

    /**
     * Get string of src.Graph.Vertex with all it's ingoing and outgoing adjacencies
     * @ return string
     */
    @Override
    public String toString() {
        return "src.Graph.Vertex{" +
                "value=" + value +
                '}';
    }


    /**
     *  Method for get the name
     * @return
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Method for get min distance calculate from djistra
     * @return
     */
    public Double getMinDistance() {
        return this.minDistance;
    }

    public LinkedList<Vertex<T>> getOugoing() {
        return this.outgoing;
    }
}