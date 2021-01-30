package src.Simulator;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Alvo {


    @SerializedName("divisao")
    @Expose
    private String divisao;
    @SerializedName("tipo")
    @Expose
    private String tipo;

    public String getDivisao() {
        return divisao;
    }

    public void setDivisao(String divisao) {
        this.divisao = divisao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


}