package src.Simulator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import src.Graph.DGraph;
import src.Graph.Edge;
import src.Graph.Vertex;
import src.Listl.LinkedList;

import javax.sound.midi.Soundbank;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;


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
    private int counter = 1;


    public RunSimulator(DGraph graph, Map map) throws IOException {
        this.graph = graph;
        this.map = map;
        startSimulator();
    }

    public void startSimulator() throws IOException {
        if (this.graph != null && this.map != null) {
            while (true) {
                System.out.println(ANSI_RED + "Bem vindo ao simulador 3000 de ataque NCIS");
                System.out.println(ANSI_YELLOW + "Escolha uma opcao: ");
                System.out.println(ANSI_CYAN + "1- Percorrer mapa manualmente");
                System.out.println(ANSI_PURPLE + "2- Percorrer mapa automaticamente para da entrada escolhida");
                System.out.println(ANSI_BLUE + "3- Percorrer mapa automaticamente para descobrir qual a melhor rota");
                System.out.println(ANSI_YELLOW + "4- Ver testes manuais da missão");
                System.out.println("5- ver o mapa por ordem de filhos");
                System.out.println("6- Ver o mapa por ordem de profundidade" + ANSI_RESET);
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

                        //While Enter dont exist(returned false) dont continue and ask another enter
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
                        Iterator i = jsons.iterator();
                        while (i.hasNext()) {
                            JSONObject jsonObject = (JSONObject) i.next();
                            String mission = (String) jsonObject.get("Mission");
                            Double hp = (Double) jsonObject.get("Hp");
                            System.out.println(mission + "Hp " + hp);
                        }
                        Iterator it = pathArray.iterator();
                        while (it.hasNext()) {
                            System.out.println(it.next());
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
                    default:
                        break;
                }
            }
        }
    }

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

        jsons.add(jsonObject);
        counter++;
        try {
            writeFile = new FileWriter("simulation"+ counter + ".json");
            writeFile.write(jsonObject.toJSONString());
            writeFile.write(pathArray.toJSONString());
            writeFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
