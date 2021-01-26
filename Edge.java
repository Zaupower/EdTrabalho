import java.util.Iterator;

class Edge {
    Vertex from;
    Vertex to;
    double cost;

    /**
     * @param v1   value of type T for 'from' vertex
     * @param v2   value of type T for 'to' vertex
     * @param cost integer value for cost/weight of edge
     */
    public Edge(Vertex v1, Vertex v2, int cost) {

        from = v1;
        to = v2;
        this.cost = cost;
        from.addOutgoing(to);
        to.addIncoming(from);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "from=" + from +
                ", to=" + to +
                ", cost=" + cost +
                '}';
    }
}