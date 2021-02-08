package src.Simulator;

import src.Graph.DGraph;
import src.Listl.LinkedList;

import java.util.Iterator;


public class GraphHandler {

    /**
     * Graph handler class
     */
    public GraphHandler() {
    }

    /**
     * Fill graph with Map
     * @param graph
     * @param ligacoesLinkedList
     * @return
     */
    public DGraph fillgraph(DGraph graph, LinkedList<Ligacoes> ligacoesLinkedList){
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
