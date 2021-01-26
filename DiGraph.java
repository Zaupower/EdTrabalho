/* Generic Directed Weighted Graph with Dijkstra's Shortest Path Algorithm
* by /u/Philboyd_Studge
* for /r/javaexamples
*/


import Listl.ArrayIterator;
import Listl.ArrayUnorderedList;
import Listl.LinkedList;
import Queue.LinkedQueue;
import Queue.PriorityQueue;

import java.util.Iterator;

@SuppressWarnings("unchecked")
/**
 * Creates a directed, weighted <tt>Graph</tt> for any Comparable type
 * <p> add Edge date with <code>add(T valueforVertexFrom, T valueForVertexTo, int cost)</code>
 * <p> use <code>getPath(T valueFrom, T valueTo)</code> to get the shortest path between
 * the two using Dijkstra's Algorithm
 * <p> If returned List has a size of 1 and a cost of Integer.Max_Value then no conected path
 * was found
 *
 * @author /u/Marcelo_Carvalho
 */


public class DiGraph<T extends Comparable<T>>
{



	private LinkedList<Vertex> vertices;
	private LinkedList<Edge> edges;
	private ArrayUnorderedList<T> dfsResultList = new ArrayUnorderedList<T>();

	/**
	 * Default Constructor
	 */
	public DiGraph()
	{
		this.vertices = new LinkedList<>();
		this.edges = new LinkedList<>();
	}

	/**
	 * Creates Edge from two values T directed from -- to
	 * @param from Value for Vertex 1
	 * @param to Value for Vertex 2
	 * @param cost Cost or weight of edge
	 */
	public void add(T from, T to, int cost)
	{
		Vertex v1T = new Vertex(from);
		Vertex v2T = new Vertex(to);
		Edge temp = findEdge(v1T, v2T);
		if (temp != null)
		{

			System.out.println("Edge " + from + "," + to + " already exists. Changing cost.");
			temp.cost = cost;
		}
		else
		{
			Vertex fromV;
			Vertex toV;
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
	private Vertex findVertex(T v)
	{
		for (Vertex<T> each : vertices)
		{
			if (each.value.compareTo(v)==0)
				return each;
		}
		return null;
	}

	/**
	 * Find edge containg two vertices
	 * in direction v1 -> v2
	 * @param v1 Vertex 'from'
	 * @param v2 Vertex 'to'
	 * @return Edge, or <code>null</code> if not found.
	 */
	public Edge findEdge(Vertex v1, Vertex v2)
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
	 * Find edge from two values
	 * @param from from value of type T
	 * @param to to value of type T
	 * @return Edge, or <code>null</code> if not found.
	 */
	private Edge findEdge(T from, T to)
	{
		for (Edge each : edges)
		{
			if (each.from.value.equals(from) && each.to.value.equals(to))
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
		for (Vertex each : vertices)
		{
			each.state = State.UNVISITED;
		}
	}

	/**
	 * Test if DFS or BFS returned a connected graph
	 * @return true if connected, false if not.
	 */
	public boolean isConnected()
	{
		for (Vertex each : vertices)
		{
			if (each.state != State.COMPLETE)
				return false;
		}
		return true;
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

	public Iterator DepthFirstSearch(T v){
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
			if (each.state ==State.UNVISITED)
			{
				dfsResultList.addToRear((T) each);
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

		LinkedQueue<Vertex> queue = new LinkedQueue<>();
		queue.enqueue(root);
		root.state = State.COMPLETE;

		while (!queue.isEmpty())
		{
			root = queue.dequeue();
			for (Vertex each : root.outgoing)
			{

				if (each.state==State.UNVISITED)
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
	public Iterator BreadthFirstSearch(T v1)
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
			for (Vertex each : root.outgoing)
			{

				if (each.state==State.UNVISITED)
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
		Vertex source = findVertex(v1);
		if (source==null) return false;

		// set to 0 and add to heap
		source.minDistance = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		pq.addElement(source, (int) source.minDistance);

		while (!pq.isEmpty())
		{
			//pull off top of queue
			Vertex<T> u = pq.removeMin().getElement();

			// loop through adjacent vertices
			for (Vertex v : u.outgoing)
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
	private Iterator getShortestPath(Vertex target)
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
		for (Vertex v = target; v !=null; v = v.previous)
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
		for (Vertex each : vertices)
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
	public Iterator<T> getPath(T from, T to)
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
		for (Vertex each : vertices)
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