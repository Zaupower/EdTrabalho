package src.Simulator;

/**
 * Room class representation
 */
public class Room implements Comparable {

    String name;
    int dano;


    public Room(String name) {
        this.name = name;
        this.dano = 0;
    }

    public Room(String name, int dano) {
        this.name = name;
        this.dano = dano;
    }

    /**
     * Get room name
     * @return
     */
    public String getName() {
        return name;
    }

    public int getDano(){
        return this.dano;
    }

    /**
     * Set room damage
     * @param dano
     */
    public void setDano(int dano){
        this.dano = this.dano + dano;
    }

    /**
     * Set room name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Comparable method
     * @param o
     * @return int 0 equals, 1 higher, -1 lower
     */
    @Override
    public int compareTo(Object o) {
        if (this.name.equals(o))
            return 0;
        else return -1;
    }
}
