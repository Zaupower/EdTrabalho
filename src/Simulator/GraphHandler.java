package src.Simulator;

import src.Graph.DiGraph;
import src.Listl.LinkedList;

import java.util.Iterator;


public class GraphHandler {


    public GraphHandler() {
    }

    public DiGraph fillgraph(DiGraph graph, LinkedList<Ligacoes> ligacoesLinkedList){
        Iterator linkItr = ligacoesLinkedList.iterator();
        while (linkItr.hasNext()) {
            Ligacoes tmp = (src.Simulator.Ligacoes) linkItr.next();
            System.out.println("From:"+tmp.getFrom().getName() + " To: "+tmp.getTo().getName() + " Dano: "+ tmp.getDano() );
            graph.add(tmp.getFrom().getName(), tmp.getTo().getName(), tmp.dano);
            graph.add(tmp.getTo().getName(), tmp.getFrom().getName(), tmp.dano);
        }
        return graph;
    }
}
