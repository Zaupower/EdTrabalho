package src.Graph;

import src.Simulator.Map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

@SuppressWarnings("unchecked")
/**
 * Directed Graph Test - Dijkstra Shortest Path
 * 
 * @author /u/Philboyd_Studge
 */
public class DiGraphTest
{
	public static void main(String[] args) throws IOException {

		//Map<String> m = new Map("Svenk", version, roomList, ligacoesLinkedList);

		//m.addPath("s1");
		//m.addPath("s2");
		//m.addPath("s3");

		// create graph
		DGraph graph = new DGraph();

		// add a bunch of edges
		graph.add("s0", "s1", 3);
		graph.add("s1", "s0", 3);

		graph.add("s0", "s4", 10);
		graph.add("s4", "s0", 10);

		graph.add("s0", "s5", 5);
		graph.add("s5", "s0", 5);

		graph.add("s1", "s2", 1);
		graph.add("s2", "s1", 1);

		graph.add("s1", "s3", 1);
		graph.add("s3", "s1", 1);

		graph.add("s5", "s6", 0);
		graph.add("s6", "s5", 0);

		graph.add("s5", "s7", 2);
		graph.add("s7", "s5", 2);

		graph.add("s7", "s8", 1);
		graph.add("s8", "s7", 1);

		graph.add("s8", "s3", 2);
		graph.add("s3", "s8", 2);



		//System.out.println("Graph is connected: ");
		System.out.println("DFS Iterator");
		Iterator<Vertex> itrDFS = graph.iteratorDFS("s2");
		while (itrDFS.hasNext()){
			System.out.println("NO MAIN DFS: "+itrDFS.next().toString());
		}
		System.out.println("BFS Iterator");
		Iterator<Vertex> itrBFS = graph.iteratorBFS("s2");
		while (itrBFS.hasNext()){
			System.out.println("NO MAIN BFS: "+itrBFS.next().toString());
		}

		System.out.println();

		System.out.println("s1 to s6");
		Iterator<Vertex> itrDrk = graph.iteratorShortestPath("s2", "s6");
		while (itrDrk.hasNext()){
			Vertex v = itrDrk.next();
			System.out.println("NO MAIN Drijskta: "+v.getValue().toString() +", Distance: "+ v.getMinDistance());
		}


	}


	public static void manualItr(String start, DGraph graph, boolean finished, Map m) throws IOException {
		if (finished){
			return;
		}
		System.out.println("Escolheu: "+start);
		Iterator<Vertex> pathItr = graph.getadjacentVertexs(start);
		int i = 0;
		while (pathItr.hasNext()){

			System.out.println("caminho: - "+ i + " "+ pathItr.next().getValue());
			i++;
		}

		//Enter data using BufferReader
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		// Reading data using readLine
		String entrada = reader.readLine();
		int startNew = Integer.parseInt(entrada);

	}
}