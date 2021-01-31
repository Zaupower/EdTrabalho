package src.Simulator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import src.Graph.DGraph;
import src.Graph.Vertex;
import src.Listl.ArrayUnorderedList;
import sun.awt.image.ImageWatched;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import src.Listl.LinkedList;

public class SimulatorTest {

    public static void main(String[] args) throws IOException, ParseException {


        JsonHandler jsHandler = new JsonHandler();

        ArrayUnorderedList<Enemy> enemies = jsHandler.getEnemies();
        LinkedList<Room> roomList = jsHandler.getRooms();
        LinkedList<Room> inOutList = jsHandler.getEntradasSaida();
        LinkedList<Ligacoes> ligacoesLinkedList = jsHandler.getLigacoes();
        String cod = jsHandler.getCod();
        String finish = jsHandler.getAlvo();
        int version = jsHandler.getVersion();

        DGraph grapht = new DGraph();
        GraphHandler graphHandler = new GraphHandler();
        DGraph graph = graphHandler.fillgraph(grapht, ligacoesLinkedList);

        Map map = new Map(cod, version, roomList,ligacoesLinkedList, inOutList, finish );

        RunSimulator run = new RunSimulator(graph, map);

        Iterator roomItr2 = roomList.iterator();
        while (roomItr2.hasNext()) {
            Room tmp = (Room) roomItr2.next();
            System.out.println("Room name: " + tmp.getName() + "Room damage: " + tmp.getDano());
        }

        System.out.println("BFS itr");
        Iterator<Vertex> grahItr = graph.iteratorBFS("Garagem");
        while (grahItr.hasNext()){

            System.out.println(grahItr.next());
        }

        String b = "Garagem";
        System.out.println("DFS itr");
        Iterator dfsItr = graph.iteratorDFS(b);
        while (dfsItr.hasNext()){
            System.out.println(dfsItr.next());
        }

        //System.out.println("");
        //System.out.println("Drj itr");
        //Iterator drtItr = graph.getPath("Escada de Emergência", "Laboratório" );
        //while (drtItr.hasNext()){
          //  Vertex v = (Vertex) drtItr.next();

            //System.out.println("Drijskta: "+v.getValue() +", Distance: "+ v.getMinDistance()/2);
        //}

        System.out.println("Adj vertices");
        Iterator adjVertices = graph.getadjacentVertexs( "Laboratório" );
        while (adjVertices.hasNext()){
            Vertex v = (Vertex) adjVertices.next();
            System.out.println("Adj vertices: "+v.getValue());
        }
    }
}
