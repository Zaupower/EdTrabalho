package src.Simulator;


import src.Listl.LinkedList;

public class Example {

    private String codMissao;

    private Integer versao;

    private LinkedList<Room> edificios = new LinkedList<>();

    private LinkedList<Ligacoes> ligacoes = new LinkedList<>();

    private LinkedList<Enemy> inimigos;

    private LinkedList<String> entradasSaidas;

    private Alvo alvo;

    public Example() {
       this.entradasSaidas = new LinkedList<>();
       this.ligacoes = new LinkedList<>();
       this.inimigos = new LinkedList<>();
    }

    public String getCodMissao() {
        return codMissao;
    }

    public void setCodMissao(String codMissao) {
        this.codMissao = codMissao;
    }

    public Integer getVersao() {
        return versao;
    }

    public void setVersao(Integer versao) {
        this.versao = versao;
    }

    public LinkedList<Room> getEdificio() {
        return edificios;
    }

    public void setEdificio(LinkedList<Room> edificio) {
        this.edificios = edificio;
    }

    public LinkedList<Ligacoes> getLigacoes() {
        return ligacoes;
    }

    public void setLigacoes(LinkedList<Ligacoes> ligacoes) {
        this.ligacoes = ligacoes;
    }

    public LinkedList<Enemy> getInimigos() {
        return inimigos;
    }

    public void setInimigos(LinkedList<Enemy> inimigos) {
        this.inimigos = inimigos;
    }

    public LinkedList<String> getEntradasSaidas() {
        return entradasSaidas;
    }

    public void setEntradasSaidas(LinkedList<String> entradasSaidas) {
        this.entradasSaidas = entradasSaidas;
    }

    public Alvo getAlvo() {
        return alvo;
    }

    public void setAlvo(Alvo alvo) {
        this.alvo = alvo;
    }

}