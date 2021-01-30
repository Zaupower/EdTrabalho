package src.Simulator;

public class Ligacoes {
    Room from;
    Room to;
    int dano;

    public Ligacoes(Room from, Room to) {
        this.from = from;
        this.to = to;
        this.dano =0;
    }

    public Room getFrom() {
        return from;
    }

    public void setFrom(Room from) {
        this.from = from;
    }

    public Room getTo() {
        return to;
    }

    public void setTo(Room to) {
        this.to = to;
    }

    public int getDano() {
        return dano;
    }

    public void setDano(int dano) {
        this.dano = dano;
    }

    @Override
    public String toString() {
        return "Ligacoes{" +
                "from=" + from +
                ", to=" + to +
                ", dano=" + dano +
                '}';
    }


}
