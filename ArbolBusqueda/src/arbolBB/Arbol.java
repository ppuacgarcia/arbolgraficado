/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolBB;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Toloza XD
 */

public class Arbol {

    private Nodo raiz;

    public Arbol() {
        raiz = null;
    }
    
    public boolean Insertar(Integer valor){
        if (existe(valor) == false)
        {
            raiz = InsertarNodo(valor, raiz);
            return true;
        }
        return false;
    }
    
    public Nodo InsertarNodo(Integer valor, Nodo raiz){
        if(raiz==null){
            raiz = new Nodo();
            raiz.setValor(valor);
            raiz.setDer(null);
            raiz.setIzq(null);
        }
        else{
            if(raiz.getValor()<valor)
                raiz.setDer(InsertarNodo(valor, raiz.getDer()));
            else
                raiz.setIzq(InsertarNodo(valor,raiz.getIzq()));
        }
        return raiz;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public boolean existe(int valor) {
        Nodo aux = raiz;
        while (aux != null) {
            if (valor == aux.getValor()) {
                return true;
            } else if (valor > aux.getValor()) {
                aux = aux.getDer();
            } else {
                aux = aux.getIzq();
            }
        }
        return false;
    }
    
     public JPanel getdibujo() {
        return new ArbolGraficado(this);
    }
}
