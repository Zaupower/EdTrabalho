package src.Simulator;

/**
 * Class representation of Edges
 */
public class Ligacoes {
    Room from;
    Room to;
    int dano;

    public Ligacoes(Room from, Room to) {
        this.from = from;
        this.to = to;
        this.dano =0;
    }

    /**
     * Get Room from
     * @return from
     */
    public Room getFrom() {
        return from;
    }

    /**
     * Set Room from
     * @param from
     */
    public void setFrom(Room from) {
        this.from = from;
    }

    /**
     * Get Room to
     * @return to
     */
    public Room getTo() {
        return to;
    }

    /**
     * Set Room to
     * @param to
     */
    public void setTo(Room to) {
        this.to = to;
    }

    /**
     * Get damage
     * @return dano
     */
    public int getDano() {
        return dano;
    }

    /**
     * Set damage
     * @param dano
     */
    public void setDano(int dano) {
        this.dano = dano;
    }

    /**
     * To string method
     * @return Egde to string
     */
    @Override
    public String toString() {
        return "Ligacoes{" +
                "from=" + from +
                ", to=" + to +
                ", dano=" + dano +
                '}';
    }


}
