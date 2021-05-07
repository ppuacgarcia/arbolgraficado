/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolBB;

/**
 *
 * @author Toloza XD
 */
public class Nodo {
    
    private Nodo der;
    private Nodo izq;
    private int Valor;
    private int altura;
    private int fe;
    
    public Nodo() {
        this.der = null;
        this.izq = null;
        this.Valor = 0;
        this.altura = 0;
        this.fe = 0;
    }

    public Nodo getDer() {
        return this.der;
    }

    public Nodo getIzq() {
        return this.izq;
    }

    public int getValor() {
        return this.Valor;
    }

    public int getAltura() {
        return this.altura;
    }

    public int getFe() {
        return this.fe;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public void setValor(int Valor) {
        this.Valor = Valor;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setFe(int fe) {
        this.fe = fe;
    }
}
