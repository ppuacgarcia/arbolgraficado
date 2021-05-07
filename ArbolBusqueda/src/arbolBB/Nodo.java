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
    private int  valor;
    private Nodo izq,der;

    public Nodo(int valor, Nodo izq, Nodo der) {
        this.valor = valor;
        this.izq = izq;
        this.der = der;
    }
    
    public Nodo(int valor) {
        this.valor = valor;
        this.izq = null;
        this.der = null;
    }
    
    public Nodo() {
        this.valor = 0;
        this.izq = null;
        this.der = null;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }
 
}
