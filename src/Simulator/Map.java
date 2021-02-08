package src.Simulator;

import src.Listl.LinkedList;
import sun.awt.image.ImageWatched;

import java.util.Iterator;

/**
 * Map class representation
 */
public class Map {
    private final LinkedList<Ligacoes> ligacoes;
    private final int version;
    private String cod;
    private LinkedList<Room> rooms;
    private LinkedList<Room> inOutRooms;
    private int pathListCounter = 0;
    String finish;

    /**
     * Map constructor
     * @param cod
     * @param version
     * @param rooms
     * @param ligacoesLinkedList
     * @param inOutRooms
     * @param finish
     */
    public Map(String cod, int version,LinkedList<Room> rooms, LinkedList<Ligacoes> ligacoesLinkedList,LinkedList<Room> inOutRooms, String finish ) {
        this.cod = cod;
        this.version = version;
        this.rooms = rooms;
        this.ligacoes = ligacoesLinkedList;
        this.inOutRooms = inOutRooms;
        this.finish = finish;
    }

    /**
     * Get Mission code
     * @return cod
     */
    public String getCode() {
        return cod;
    }

    public void printEntradasSaidas() {
        Iterator inOutitr = inOutRooms.iterator();
        while (inOutitr.hasNext()){
            Room tmp = (Room) inOutitr.next();
            System.out.println(tmp.getName());
        }
    }

    /**
     * Get Iterator of inOutRooms
     * @return Iterator inOutitr
     */
    public Iterator getEntradasSaidas() {
        Iterator inOutitr = inOutRooms.iterator();

        return inOutitr;
    }

    /**
     * Get inOutRooms List
     * @return this.inOutRooms
     */
    public LinkedList<Room> getInOutRooms(){
        return this.inOutRooms;
    }

    /**
     * Get finish room
     * @return finish
     */
    public String getFinish(){

        return  finish;
    }
}
