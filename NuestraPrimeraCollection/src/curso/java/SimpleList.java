package curso.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.E;

public class SimpleList {

    public String getAlfa() {
        return alfa;
    }

    public void setAlfa(String alfa) {
        this.alfa = alfa;
    }

    public Integer getBeta() {
        return beta;
    }

    public void setBeta(Integer beta) {
        this.beta = beta;
    }

    public String getGamma() {
        return gamma;
    }

    public void setGamma(String gamma) {
        this.gamma = gamma;
    }

    String alfa;
    Integer beta;
    String gamma;

    public List<String> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<String> pedidos) {
        this.pedidos = pedidos;
    }

    List<String> pedidos = new ArrayList<>();




}
