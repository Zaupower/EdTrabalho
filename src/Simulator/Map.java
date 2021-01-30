package src.Simulator;

import src.Listl.LinkedList;
import sun.awt.image.ImageWatched;

import java.util.Iterator;


public class Map {
    private final LinkedList<Ligacoes> ligacoes;
    private final int version;
    private String cod;
    private LinkedList<Room> rooms;
    private LinkedList<Room> inOutRooms;
    private int pathListCounter = 0;
    String finish;


    public Map(String cod, int version,LinkedList<Room> rooms, LinkedList<Ligacoes> ligacoesLinkedList,LinkedList<Room> inOutRooms, String finish ) {
        this.cod = cod;
        this.version = version;
        this.rooms = rooms;
        this.ligacoes = ligacoesLinkedList;
        this.inOutRooms = inOutRooms;
        this.finish = finish;
    }

    public String getCode() {
        return cod;
    }

    public void getEntradasSaidas() {
        Iterator inOutitr = inOutRooms.iterator();
        while (inOutitr.hasNext()){
            Room tmp = (Room) inOutitr.next();
            System.out.println(tmp.getName());
        }
    }

    public String getFinish(){

        return  finish;
    }
}
