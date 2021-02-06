package tests;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import org.junit.jupiter.api.Test;
import src.Exceptionsl.NonComparableElementException;
import src.Graph.DGraph;
import src.Graph.Edge;
import src.Graph.Vertex;

import static org.junit.jupiter.api.Assertions.*;

class DGraphTest extends DGraph {

    @Test
    void testAdd() {
        DGraph graph = new DGraph();
        graph.add("s0", "s1", 3);
        graph.add("s1", "s0", 3);
    }

    @Test
    void testFindVertex() {

    }

    @Test
    void testFindEdge() {

    }

    @Test
    void testAddEdge() throws NonComparableElementException {

    }

    @Test
    void testRemoveEdge() throws NonComparableElementException {
    }

    @Test
    void testIsEmpty() {
        DGraph graph = new DGraph();

        graph.isEmpty();

    }

    @Test
    void testIteratorDFS() {
    }

    @Test
    void testIteratorBFS() {
    }

    @Test
    void testIteratorShortestPath() {
    }
}