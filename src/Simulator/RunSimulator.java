package src.Simulator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import src.Graph.DGraph;
import src.Graph.Edge;
import src.Graph.Vertex;
import src.Listl.ArrayUnorderedList;
import src.Listl.LinkedList;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Run simulator Menu
 *
 */
public class RunSimulator {


    protected LinkedList<String> savePath = new LinkedList<>();
    protected DGraph graph;
    protected Map map;
    protected double hp = 100;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    JSONArray jsons = new JSONArray();
    JSONArray pathArray = new JSONArray();
    boolean segundoMapa = false;


    /**
     * CLass que corre o menu
     *
     * @throws IOException
     * @throws ParseException
     */
    public RunSimulator() throws IOException, ParseException {

        startSimulator();
    }

    /**
     * Simulator menu
     * @throws IOException
     * @throws ParseException
     */
    public void startSimulator() throws IOException, ParseException {
            while (true) {
                System.out.println(ANSI_RED + "Bem vindo ao simulador 3000 de ataque NCIS"+ ANSI_RESET);
                if (!segundoMapa){
                    choseMap();
                }
                System.out.println(ANSI_YELLOW + "Escolha uma opcao: ");
                System.out.println(ANSI_CYAN + "1- Percorrer mapa manualmente");
                System.out.println(ANSI_PURPLE + "2- Percorrer mapa automaticamente para da entrada escolhida");
                System.out.println(ANSI_BLUE + "3- Percorrer mapa automaticamente para descobrir qual a melhor rota");
                System.out.println(ANSI_YELLOW + "4- Ver testes manuais da missão");

                System.out.println("5- ver o mapa por ordem de filhos");
                System.out.println("6- Ver o mapa por ordem de profundidade" + ANSI_RESET);
                System.out.println("7- Escolher outro mapa");
                System.out.println("8- Sair");
                System.out.println(ANSI_RESET);

                //Enter data using BufferReader
                BufferedReader reade = new BufferedReader(new InputStreamReader(System.in));
                // Reading data using readLine
                String s = reade.readLine();

                int choice = Integer.parseInt(s);
                switch (choice) {
                    case 1:
                        System.out.println("Mission: " + map.getCode());
                        System.out.println("Possiveis entradas/saidas");
                        System.out.println(" ");
                        map.printEntradasSaidas();
                        System.out.println(" ");
                        System.out.println("Alvo -" + map.getFinish());

                        System.out.println("Por favor indique o ponto de partida ");
                        //Enter data using BufferReader
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        // Reading data using readLine
                        String entrada = reader.readLine();
                        boolean finished = false;
                        boolean verify = verifyEnter(entrada);

                        //While Enter dont exist(returned false) dont continue and ask another enter
                        while (verify == false) {
                            System.out.println("Choose another enter");
                            entrada = reader.readLine();
                            verify = verifyEnter(entrada);
                        }
                        manualItr(entrada, finished);
                        break;
                    case 2:
                        String entrada2;
                        boolean finished2 = false;
                        //System.out.println("Escolheu a opcao nr 2, ira ser agora calcualdo o melhor caminho para chegar ao destivo com o maximo de vida possivel!!");
                        System.out.println("Digite a entrada e nos descobiremos a melhor rota a seguir!");
                        BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
                        entrada2 = reader2.readLine();
                        boolean verify2 = verifyEnter(entrada2);

                        while (verify2 == false) {
                            System.out.println("Choose another enter");
                            entrada2 = reader2.readLine();
                            verify2 = verifyEnter(entrada2);
                        }
                        automatic(entrada2, finished2);
                        break;

                    case 3:
                        automaticEdge();
                        break;
                    case 4:

                        //ler nome dos ficheiros da pasta CompletedMissions
                        File folder = new File("CompletedMissions\\");
                        File[] listOfFiles = folder.listFiles();

                        for (int i = 0; i < listOfFiles.length; i++) {
                            if (listOfFiles[i].isFile()) {
                                System.out.println("File " + listOfFiles[i].getName());

                            } else if (listOfFiles[i].isDirectory()) {
                                System.out.println("Directory " + listOfFiles[i].getName());
                            }
                        }

                        System.out.println("Por favor indique a simulação que deseja ver ");
                        //Enter data using BufferReader
                        BufferedReader reader20 = new BufferedReader(new InputStreamReader(System.in));
                        // Reading data using readLine
                        String mission = reader20.readLine();
                        //Run the array of files
                        for (int i = 0; i < listOfFiles.length; i++) {
                            //If find the file call the function readRuns
                            if (listOfFiles[i].getName().equals(mission)) {
                                readRuns(mission);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Por favor indique o ponto de partida ");
                        //Enter data using BufferReader
                        BufferedReader reader3 = new BufferedReader(new InputStreamReader(System.in));
                        // Reading data using readLine
                        String startBfs = reader3.readLine();
                        Iterator bfsItr = graph.iteratorBFS(startBfs);
                        while (bfsItr.hasNext()) {
                            Vertex v = (Vertex) bfsItr.next();
                            System.out.println(v.getValue());
                        }
                        break;
                    case 6:
                        System.out.println("Por favor indique o ponto de partida ");
                        //Enter data using BufferReader
                        BufferedReader reader4 = new BufferedReader(new InputStreamReader(System.in));
                        // Reading data using readLine
                        String startDfs = reader4.readLine();
                        Iterator dfsItr = graph.iteratorDFS(startDfs);
                        while (dfsItr.hasNext()) {
                            Vertex v = (Vertex) dfsItr.next();
                            System.out.println(v.getValue());
                        }
                        break;
                    case 7:
                        choseMap();
                        break;
                    case 8:
                        return;
                    default:
                        break;
                }
            }

    }

    /**
     * Chose Map to play
     * @throws IOException
     * @throws ParseException
     */
    private void choseMap() throws IOException, ParseException {

        segundoMapa = true;
        System.out.println("Por favor escolha o mapa que pretrende carregar");

        File f = new File("Maps\\");
        File[] listOfFilesMap = f.listFiles();

        for (int i = 0; i < listOfFilesMap.length; i++) {
            if (listOfFilesMap[i].isFile()) {
                System.out.println("Map " + listOfFilesMap[i].getName());

            } else if (listOfFilesMap[i].isDirectory()) {
                System.out.println("Directory " + listOfFilesMap[i].getName());
            }
        }
        BufferedReader readeN = new BufferedReader(new InputStreamReader(System.in));
        String mapS = readeN.readLine();
        //Run the array of files
        String mapPath = "Maps/" + mapS;
        boolean foundMap = false;
        for (int i = 0; i < listOfFilesMap.length; i++) {
            if (listOfFilesMap[i].getName().equals(mapS)) {
                foundMap = true;
                //System.out.println("Encontrado");
            }
        }
        if (foundMap) {
                JsonHandler jsHandler = new JsonHandler(mapPath);

                ArrayUnorderedList<Enemy> enemies = jsHandler.getEnemies();
                LinkedList<Room> roomList = jsHandler.getRooms();
                LinkedList<Room> inOutList = jsHandler.getEntradasSaida();
                LinkedList<Ligacoes> ligacoesLinkedList = jsHandler.getLigacoes();
                String cod = jsHandler.getCod();
                String finish = jsHandler.getAlvo();
                int version = jsHandler.getVersion();
                DGraph grapht = new DGraph();
                GraphHandler graphHandler = new GraphHandler();
                this.map = new Map(cod, version, roomList, ligacoesLinkedList, inOutList, finish);
                this.graph = graphHandler.fillgraph(grapht, ligacoesLinkedList);
        }else {
            System.out.println("Mapa Invalido");
            choseMap();
        }
    }

    /**
     * Read saved runs
     * @param file
     * @throws IOException
     * @throws ParseException
     */
    private void readRuns(String file) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        String location = "CompletedMissions/" + file;
        Object obj = parser.parse(new FileReader(location));
        JSONObject jsonObject = (JSONObject) obj;

            String mission = (String) jsonObject.get("Mission");
            System.out.println("Mission " + mission);
            Double hp = (Double) jsonObject.get("Hp");
            System.out.println("Hp " + hp);
            System.out.println("Caminho percorrido: ");
            JSONArray caminho = (JSONArray) jsonObject.get("Caminho");
            Iterator it = caminho.iterator();
            while (it.hasNext()){
                System.out.println(it.next());
            }
    }

    /**
     * Chose type of iteration or map to run automatic simulation
     * @throws IOException
     */
    public void automaticEdge() throws IOException {
        double minStart = Double.MAX_VALUE;
        String minStartVertex = null;
        Vertex v = new Vertex();

        Iterator inItr = map.getInOutRooms().iterator();
        while (inItr.hasNext()) {
            Room tmpRoom = (Room) inItr.next();

            Iterator drkItr = graph.iteratorShortestPath(tmpRoom.getName(), map.finish);
            while (drkItr.hasNext()) {
                v = (Vertex) drkItr.next();
                Room r = new Room((String) v.getValue());
            }
            if (v.getMinDistance() < minStart) {
                minStart = v.getMinDistance();
                minStartVertex = tmpRoom.getName();
            }
            System.out.println("Partindo de:" + tmpRoom.getName() + " Dano: " + v.getMinDistance() / 2);
        }
        System.out.println(" ");

        //Calcular melhor saida
        String minEnd = null;
        double outCost = Double.MAX_VALUE;

        Iterator OutItr = map.getInOutRooms().iterator();
        while (OutItr.hasNext()) {

            Room tmpRoom = (Room) OutItr.next();
            System.out.println("Partindo de:" + tmpRoom.getName());
            Iterator drkItr = graph.iteratorShortestPath(map.finish, tmpRoom.getName());
            while (drkItr.hasNext()) {
                v = (Vertex) drkItr.next();
                Room r = new Room((String) v.getValue());
            }
            if (v.getMinDistance() < outCost) {
                outCost = v.getMinDistance();
                minEnd = (String) v.getValue();
            }
            System.out.println("Melhor saida através da sala: " + " " + tmpRoom.getName() + "Dano recebido: " + v.getMinDistance() / 2);
            System.out.println(" ");
        }
        System.out.println(" ");
        System.out.println("Melhor Entrada: " + minStartVertex);
        System.out.println("Dano: " + minStart / 2);
        System.out.println("Melhor Saida: " + minEnd);
        System.out.println("Dano: " + outCost / 2);
        System.out.println("Vida no final do percurso: " + (hp - (minStart / 2) - (outCost / 2)));
        System.out.println("Deseja ver o Caminho completo? s/n");

        BufferedReader readerX = new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        String s = readerX.readLine();
        Iterator drkItrIn = graph.iteratorShortestPath(minStartVertex, map.finish);
        if (s.equals("s") || s.equals("S")) {
            System.out.println("Inicio: ");
            while (drkItrIn.hasNext()) {
                v = (Vertex) drkItrIn.next();
                Room r = new Room((String) v.getValue());
                System.out.println("Room: " + v.getValue() + " cost: " + v.getMinDistance() / 2);
                if (drkItrIn.hasNext() != false) {
                    System.out.println("Depois: ");
                }
            }
        } else if (s.equals("n") || s.equals("N")) {
        }
    }

    /**
     * Run game on automatic input
     * @param start
     * @param finish
     */
    public void automatic(String start, boolean finish) {
        Double damage = 0.00;
        System.out.println("Caminho a percorrer: ");
        Iterator drtItr = graph.iteratorShortestPath(start, map.finish);
        while (drtItr.hasNext()) {
            Vertex v = (Vertex) drtItr.next();
            System.out.println("Drijskta: " + v.getValue());
            damage = v.getMinDistance() / 2;
        }
        System.out.println("Dano recebido até ao fim: " + damage);
    }

    /**
     * Run game on manual input
     * @param start
     * @param finished
     * @throws IOException
     */
    public void manualItr(String start, boolean finished) throws IOException {

        if (start.equals(map.finish)) {
            System.out.println("A sua missao Chegou ao fim com sucesso, Parabens!");
            genereteJson();
            return;

        } else {
            if (hp <= 0) {
                System.out.println("Morreu babaca!!");
                genereteJson();
                return;
            }

            LinkedList<Vertex> paths = new LinkedList<>();
            Vertex tmp = new Vertex();
            System.out.println("Escolheu: " + start);
            System.out.println("");
            Iterator<Vertex> pathItr = this.graph.getadjacentVertexs(start);
            while (pathItr.hasNext()) {
                tmp = pathItr.next();
                paths.add(tmp);
                System.out.println("caminho:  " + tmp.getValue());
            }
            //Enter data using BufferReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            // Reading data using readLine
            String entrada = reader.readLine();

            Vertex test;

            boolean validPath = false;
            Iterator pathListItr = paths.iterator();
            while (pathListItr.hasNext()) {
                test = (Vertex) pathListItr.next();
                if (test.getValue().equals(entrada)) {
                    validPath = true;
                }
            }

            if (validPath) {
                Vertex in = graph.findVertex(start);
                Vertex out = graph.findVertex(entrada);
                Edge edgeTmp = graph.findEdge(in, out);

                savePath.add(entrada);

                if (edgeTmp.getCost() > 0) {
                    System.out.println("Perigo!!, Enimigo na sala!!");
                    hp = hp - edgeTmp.getCost();

                    System.out.println("Vida reduzida em: " + hp);

                }
                manualItr(entrada, finished);
            } else {
                System.out.println("Erro por favor intruduza um caminho valido");
                manualItr(start, finished);
            }
        }
    }

    //Method for verify if the Enter choosed exist
    public boolean verifyEnter(String enter) {
        Iterator verificacao = map.getEntradasSaidas();
        while (verificacao.hasNext()) {
            Room sala = (Room) verificacao.next();
            String entradaousaida = sala.getName();
            if (enter.equals(entradaousaida)) {
                pathArray.add(enter);
                return true;
            }
        }
        return false;
    }


    public void genereteJson() {

        JSONObject jsonObject = new JSONObject();
        FileWriter writeFile = null;

        jsonObject.put("Mission", map.getCode());
        jsonObject.put("Hp", hp);
        Iterator it = savePath.iterator();
        while (it.hasNext()) {
            pathArray.add(it.next());
        }
        jsonObject.put("Caminho" , pathArray);

        jsons.add(jsonObject);
        try {
            String fileName = new SimpleDateFormat("yyyyMMddHHmm").format(new Date());
            writeFile = new FileWriter("CompletedMissions\\simulation"+ map.getCode()+"Date_"+ fileName+ ".json");
            writeFile.write(jsonObject.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
