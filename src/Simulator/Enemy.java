package src.Simulator;

import java.util.SplittableRandom;

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPoder() {
        return poder;
    }

    public void setPoder(Integer poder) {
        this.poder = poder;
    }

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    @Override
    public String toString() {
        return "Enemy: " +
                "nome='" + nome + '\'' +
                ", poder=" + poder +
                ", divisao='" + divisao + '\'';
    }
}