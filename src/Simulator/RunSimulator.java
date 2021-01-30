package src.Simulator;

import src.Graph.DiGraph;
import src.Graph.Edge;
import src.Graph.Vertex;
import src.Listl.LinkedList;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class RunSimulator {

    protected DiGraph graph;
    protected Map map;
    protected double hp = 100;

    public RunSimulator(DiGraph graph, Map map) throws IOException {
        this.graph = graph;
        this.map = map;
        startSimulator();
    }

    public void startSimulator() throws IOException {
        if (this.graph != null && this.map != null){
            while (true){
                System.out.println("Bem vindo ao simulador 3000 de ataque NCIS");
                System.out.println("Escolha uma opcao: ");
                System.out.println("1- Percorrer mapa manualmente");
                System.out.println("2- Percorrer mapa automaticamente para descubrir qual a melhor rota");

                //Enter data using BufferReader
                BufferedReader reade = new BufferedReader(new InputStreamReader(System.in));
                // Reading data using readLine
                String s = reade.readLine();

                int choice = Integer.parseInt(s);
                switch (choice){
                    case 1:
                        System.out.println("Mapa carregado: "+ map.getCode());
                        System.out.println("Possiveis entradas/saidas");
                        map.getEntradasSaidas();
                        System.out.println("Alvo!");
                        System.out.println(map.getFinish());

                        System.out.println("Por favor indique o ponto de partida ");
                        //Enter data using BufferReader
                        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                        // Reading data using readLine
                        String entrada = reader.readLine();
                        boolean finished = false;

                        manualItr(entrada, finished);
                        break;
                    case 2:
                        System.out.println("Escolheu a opcao nr 2, ira ser agora calcualdo o melhor caminho para chegar ao destivo com o maximo de vida possivel!!");
                       // Iterator drkItr = graph.getPath()
                        break;
                    default:
                       break;
                }

               //return;
            }
        }
    }
    public void manualItr(String start, boolean finished) throws IOException {
        if (start.equals(map.finish)){
            System.out.println("A sua missao Chegou ao fim com sucesso, Parabens!");
            return;
        }else {
            if (hp< 0 ){
                System.out.println("Murreu babaca!!");
                return;
            }
        }
        LinkedList<Vertex> paths = new LinkedList<>();
        Vertex tmp = new Vertex();
        System.out.println("Escolheu: "+start);
        System.out.println("");
        Iterator<Vertex> pathItr = this.graph.getadjacentVertexs(start);
        while (pathItr.hasNext()){
            tmp = pathItr.next();
            paths.add(tmp);
            System.out.println("caminho:  "+ tmp.getValue());
        }
        //Enter data using BufferReader
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        // Reading data using readLine
        String entrada = reader.readLine();

        Vertex test;


        boolean validPath = false;
        Iterator pathListItr = paths.iterator();
        while (pathListItr.hasNext()){
            test = (Vertex) pathListItr.next();
            if (test.getValue().equals(entrada)){
                validPath = true;
            }
        }
        
        if (validPath){
            Vertex in = graph.findVertex(start);
            Vertex out = graph.findVertex(entrada);
            Edge edgeTmp = graph.findEdge(in, out);

            if (edgeTmp.getCost() >0){
                System.out.println("Perigo!!, Enimogo na sala!!");
                hp = hp-edgeTmp.getCost();

                System.out.println("Vida reduzida em: "+hp);
            }
            manualItr( entrada, finished);
        }else {
            System.out.println("Erro por favor intruduza um caminho valido");
            manualItr( start, finished);
        }

    }
}
