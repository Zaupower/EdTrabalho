package src.Simulator;

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

    public String getName() {
        return name;
    }

    public int getDano(){
        return this.dano;
    }
    public void setDano(int dano){
        this.dano = this.dano + dano;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Object o) {
        if (this.name.equals(o))
            return 0;
        else return -1;
    }
}
