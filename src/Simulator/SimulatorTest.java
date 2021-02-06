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
    }
}
