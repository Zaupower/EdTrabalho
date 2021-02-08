package src.Graph;

import src.Exceptionsl.ElementNotFoundException;
import src.Exceptionsl.NonComparableElementException;
import src.Listl.ArrayUnorderedList;
import src.Listl.LinkedList;
import src.Queue.LinkedQueue;
import src.Queue.PriorityQueue;

import java.util.Iterator;

public class DGraph<T> implements  GraphADT<T>
{
	private LinkedList<Vertex> vertices;
	private LinkedList<Edge> edges;
	private ArrayUnorderedList<T> dfsResultList = new ArrayUnorderedList<T>();

	/**
	 * Default Constructor
	 */
	public DGraph()
	{
		this.vertices = new LinkedList<>();
		this.edges = new LinkedList<>();
	}

	/**
	 * Creates src.Edge from two values T directed from -- to
	 * @param from Value for Vertex 1
	 * @param to Value for Vertex 2
	 * @param cost Cost or weight of edge
	 */
	public void add(T from, T to, int cost)
	{
		Vertex<T> v1T = new Vertex(from);
		Vertex<T> v2T = new Vertex(to);
		Edge temp = findEdge(v1T, v2T);
		if (temp != null)
		{

			System.out.println("src.Edge " + from + "," + to + " already exists. Changing cost.");
			temp.cost = cost;
		}
		else
		{
			Vertex<T> fromV;
			Vertex<T> toV;
			// this will also create the vertices

			fromV = findVertex(from);
			if (fromV == null)
			{
				fromV = new Vertex(from);
				vertices.add(fromV);
			}
			toV = findVertex(to);
			if (toV == null)
			{
				toV = new Vertex(to);
				vertices.add(toV);
			}
			Edge e = new Edge(fromV, toV, cost);
			edges.add(e);
		}
	}

	/**
	 * find Vertex in Graph from value
	 * @param v value of type T
	 * @return Vertex, or <code>null</code> if not found.
	 */
	public Vertex findVertex(T v)
	{
		for (Vertex<T> each : vertices)
		{
			if (each.value.equals(v) == true)
				return each;
		}
		return null;
	}

	/**
	 * Find edge containg two vertices
	 * in direction v1 -> v2
	 * @param v1 Vertex 'from'
	 * @param v2 Vertex 'to'
	 * @return src.Edge, or <code>null</code> if not found.
	 */
	public Edge findEdge(Vertex<T> v1, Vertex<T> v2)
	{
		for (Edge each : edges)
		{
			if (each.from.equals(v1) && each.to.equals(v2))
			{
				return each;
			}
		}
		return null;
	}


	/**
	 * Sets all states to UNVISITED
	 */
	private void clearStates()
	{
		for (Vertex<T> each : vertices)
		{
			each.state = State.UNVISITED;
		}
	}

	@Override
	public void addVertex(T vertex) {
	}

	@Override
	public void removeVertex(T vertex) {
		try {
			vertices.remove(vertex);
		}catch (ElementNotFoundException e){
			System.out.println(e);
		}
	}

	@Override
	public void addEdge(T vertex1, T vertex2) throws NonComparableElementException {

	}

	@Override
	public void removeEdge(T vertex1, T vertex2) {

		Vertex v1 = findVertex(vertex1);
		Vertex v2 = findVertex(vertex2);

		Edge tmp = findEdge(v1, v2);
		try {
			edges.remove(tmp);
		}catch (ElementNotFoundException e){
			System.out.println(e);
		}
	}

	@Override
	public boolean isEmpty() {
		return vertices.isEmpty();
	}

	/**
	 * Test if DFS or BFS returned a connected graph
	 * @return true if connected, false if not.
	 */
	public boolean isConnected()
	{
		for (Vertex<T> each : vertices)
		{
			if (each.state != State.COMPLETE)
				return false;
		}
		return true;
	}

	@Override
	public int size() {
		return 0;
	}

	/**
	 * Performs a recursive Depth First Search on the
	 * 'root' node (the first vertex created)
	 * @return true if connected, false if empty or not connected
	 */
	public boolean DepthFirstSearch()
	{
		if (vertices.isEmpty()) return false;

		clearStates();
		// get first node
		Vertex<T> root = vertices.getHead().getElement();
		if (root==null) return false;

		// call recursive function
		DepthFirstSearch( root);
		return isConnected();
	}

	@Override
	public Iterator iteratorDFS(T v){
		clearStates();
		Vertex<T> root = findVertex(v);
		DepthFirstSearch(root);

		return dfsResultList.iterator();
	}


	/**
	 * Helper for Depth first search
	 * @param v vertex
	 */
	public void DepthFirstSearch(Vertex<T> v)
	{

		v.state = State.VISITED;

		// loop through neighbors
		for (Vertex each : v.outgoing)
		{
			if (each.state == State.UNVISITED)
			{
				dfsResultList.addToRear((T) each);
				//System.out.println(each.value.toString());
				DepthFirstSearch(each);
			}
		}
		v.state = State.COMPLETE;
	}


	/**
	 * Performs a Breadth-First Search
	 * starting at 'root' node (First created vertex)
	 * @return true is connected, false is not or if empty
	 */
	public boolean BreadthFirstSearch()
	{
		if (vertices.isEmpty()) return false;
		clearStates();

		Vertex<T> root = vertices.getHead().getElement();
		if (root==null) return false;

		LinkedQueue<Vertex<T>> queue = new LinkedQueue<>();
		queue.enqueue(root);
		root.state = State.COMPLETE;

		while (!queue.isEmpty())
		{
			root = queue.dequeue();
			for (Vertex<T> each : root.outgoing)
			{

				if (each.state== State.UNVISITED)
				{
					each.state = State.COMPLETE;
					queue.enqueue(each);
				}
			}
			queue.dequeue();
		}
		return isConnected();
	}

	/**
	 * Perfoms a Breadth-First Search on a given starting vertex
	 * @param v1 Value of type T for starting vertex
	 * @return true if connected, false if not or if empty
	 */
	@Override
	public Iterator iteratorBFS(T v1)
	{
		ArrayUnorderedList<T> bfsResultList = new ArrayUnorderedList<T>();

		if (vertices.isEmpty()) return bfsResultList.iterator();
		clearStates();

		Vertex<T> root = findVertex(v1);
		if (root==null) return bfsResultList.iterator();

		LinkedQueue<Vertex<T>> queue = new LinkedQueue<>();
		queue.enqueue(root);
		root.state = State.COMPLETE;

		while (!queue.isEmpty())
		{
			root = queue.first();
			for (Vertex<T> each : root.outgoing)
			{

				if (each.state== State.UNVISITED)
				{
					bfsResultList.addToRear((T) each);
					each.state = State.COMPLETE;
					queue.enqueue(each);
				}
			}
			queue.dequeue();
		}
		return bfsResultList.iterator();
	}

	public Iterator getadjacentVertexs(T v1){
		ArrayUnorderedList<T> pathList = new ArrayUnorderedList<T>();
		if (vertices.isEmpty()) return null;

		Vertex<T> source = findVertex(v1);
		if (source==null) return null;

		for (Vertex<T> v : source.outgoing)
		{
			//System.out.println(v.getValue());
			pathList.addToRear((T) v);
		}

		return pathList.iterator();
	}
	/**
	 * Creates path information on the graph using the Dijkstra Algorithm,
	 * Puts the information into the Vertices, based on given starting vertex.
	 * @param v1 Value of type T for starting vertex
	 * @return true if successfull, false if empty or not found.
	 */
	private boolean Dijkstra(T v1)
	{
		if (vertices.isEmpty()) return false;

		// reset all vertices minDistance and previous
		resetDistances();

		// make sure it is valid
		Vertex<T> source = findVertex(v1);
		if (source==null) return false;

		// set to 0 and add to heap
		source.minDistance = 0;
		PriorityQueue<Vertex<T>> pq = new PriorityQueue<>();
		pq.addElement(source, (int) source.minDistance);

		while (!pq.isEmpty())
		{
			//pull off top of queue
			Vertex<T> u = pq.removeMin().getElement();

			// loop through adjacent vertices
			for (Vertex<T> v : u.outgoing)
			{
				// get edge
				Edge e = findEdge(u, v);
				if (e==null) return false;
				// add cost to current
				double totalDistance = u.minDistance + e.cost;
				if (totalDistance < v.minDistance)
				{
					// new cost is smaller, set it and add to queue
					if (!pq.isEmpty()){
						pq = pq.remove(v);
					}

					v.minDistance = totalDistance;
					// link vertex
					v.previous = u;
					pq.addElement(v, (int) v.minDistance);
				}
			}
		}
		return true;
	}

	/**
	 * Goes through the result tree created by the Dijkstra method
	 * and steps backward
	 * @param target Vertex end of path
	 * @return string List of vertices and costs
	 */
	private Iterator getShortestPath(Vertex<T> target)
	{
		ArrayUnorderedList<T> path = new ArrayUnorderedList<T>();
		ArrayUnorderedList<T> pathNull = new ArrayUnorderedList<T>();
		// check for no path found
		if (target.minDistance==Integer.MAX_VALUE)
		{
			path.addToRear(null);
			return pathNull.iterator() ;
		}

		// loop through the vertices from end target
		for (Vertex<T> v = target; v !=null; v = v.previous)
		{
			path.addToFront((T) v);
		}

		return path.iterator();
	}

	/**
	 * for Dijkstra, resets all the path tree fields
	 */
	private void resetDistances()
	{
		for (Vertex<T> each : vertices)
		{
			each.minDistance = Integer.MAX_VALUE;
			each.previous = null;
		}
	}

	/**
	 * PUBLIC WRAPPER FOR PRIVATE FUNCTIONS
	 * Calls the Dijkstra method to build the path tree for the given
	 * starting vertex, then calls getShortestPath method to return
	 * a list containg all the steps in the shortest path to
	 * the destination vertex.
	 * @param from value of type T for Vertex 'from'
	 * @param to value of type T for vertex 'to'
	 * @return ArrayList of type String of the steps in the shortest path.
	 */
	@Override
	public Iterator<T> iteratorShortestPath(T from, T to)
	{
		boolean test = Dijkstra(from);
		if (test==false) return null;
		return getShortestPath(findVertex(to));
	}

	/**
	 * @return string of vertices
	 */
	@Override
	public String toString()
	{
		String retval = "";
		for (Vertex<T> each : vertices)
		{
			retval += each.toString() + "\n";
		}
		return retval;
	}

	/**
	 * list all the edges into a string
	 * @return string of edge data
	 */
	public String edgesToString()
	{
		String retval = "";
		for (Edge each : edges)
		{
			retval += each + "\n";
		}
		return retval;
	}

}