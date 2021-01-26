import java.util.Iterator;

@SuppressWarnings("unchecked")
/**
 * Directed Graph Test - Dijkstra Shortest Path
 * 
 * @author /u/Philboyd_Studge
 */
public class DiGraphTest
{
	public static void main(String[] args)
	{

		Person p = new Person("Marcelo");
		// create graph
		DiGraph graph = new DiGraph();

		// add a bunch of edges
		graph.add("s0", "s1", 3);
		graph.add("s1", "s0", 3);

		graph.add("s0", "s4", 10);
		graph.add("s4", "s0", 10);

		graph.add("s0", "s5", 1);
		graph.add("s5", "s0", 1);

		graph.add("s1", "s2", 1);
		graph.add("s2", "s1", 1);

		graph.add("s1", "s3", 1);
		graph.add("s3", "s1", 1);

		graph.add("s5", "s6", 5);
		graph.add("s6", "s5", 5);

		graph.add("s5", "s7", 2);
		graph.add("s7", "s5", 2);

		graph.add("s7", "s8", 1);
		graph.add("s8", "s7", 1);

		graph.add("s8", "s3", 2);
		graph.add("s3", "s8", 2);



		//System.out.println("Graph is connected: ");
		System.out.println("DFS Iterator");
		Iterator<Vertex> itrDFS = graph.DepthFirstSearch("s2");
		while (itrDFS.hasNext()){
			System.out.println("NO MAIN DFS: "+itrDFS.next().toString());
		}
		System.out.println("BFS Iterator");
		Iterator<Vertex> itrBFS = graph.DepthFirstSearch("s2");
		while (itrBFS.hasNext()){
			System.out.println("NO MAIN BFS: "+itrBFS.next().toString());
		}

		System.out.println();

		System.out.println("s1 to s6");
		Iterator<Vertex> itrDrk = graph.getPath("s0", "s4");
		while (itrDrk.hasNext()){
			Vertex v = itrDrk.next();
			System.out.println("NO MAIN Drijskta: "+v.value.toString() +", Distance: "+ v.minDistance);
		}

	}
}