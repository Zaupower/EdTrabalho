package src.Simulator;

/**
 * Class representation of Enemy
 */
public class Enemy {

    private String nome;

    private Integer poder;

    private String divisao;


    public Enemy(String nome, Integer poder, String divisao) {
        this.nome = nome;
        this.poder = poder;
        this.divisao = divisao;
    }

    public Enemy(String badguy1, int poder) {
        this.nome = badguy1;
        this.poder = poder;
    }

    /**
     *  Get enemy name
     * @return name
     */
    public String getNome() {
        return nome;
    }

    /**
     * Set enemy name
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Get enemy power
     * @return
     */
    public Integer getPoder() {
        return poder;
    }

    /**
     * Set enemy power
     * @param poder
     */
    public void setPoder(Integer poder) {
        this.poder = poder;
    }

    /**
     * Get enemy room name
     * @return
     */
    public String getDivisao() {
        return divisao;
    }

    /**
     * Set enemy room name
     * @param divisao
     */
    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    /**
     * Enemy to string method
     * @return String
     */
    @Override
    public String toString() {
        return "Enemy: " +
                "nome='" + nome + '\'' +
                ", poder=" + poder +
                ", divisao='" + divisao + '\'';
    }
}