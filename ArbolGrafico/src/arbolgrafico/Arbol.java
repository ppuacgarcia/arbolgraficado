/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolgrafico;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Toloza XD
 */

public class Arbol {
    
    private Nodo raiz;

    public Nodo getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo raiz) {
        this.raiz = raiz;
    }
    
    public Arbol() {
        this.raiz = null;
    }
    
    public boolean Insertar(Integer valor){
        
        if (Existe(valor)==false)
        {
            raiz = insertarNodo(valor, raiz);
            RecalcularAlturas();
            RecalcularFE();
            return true;
        }
        return false;
    }
    
    public Nodo insertarNodo(Integer valor, Nodo r){
        if(r==null){
            r = new Nodo();
            r.setValor(valor);
            r.setDer(null);
            r.setIzq(null);
        }
        else{
            if(r.getValor()<valor){
                r.setDer(insertarNodo(valor, r.getDer()));
                r.setFe(calcularFE(r));
                if(r.getFe()>1)
                    r = BuscarDesiquilibrio(r);
            }
            else{
                r.setIzq(insertarNodo(valor,r.getIzq()));
                r.setFe(calcularFE(r));
                if(r.getFe()<(-1))
                    r = BuscarDesiquilibrio(r);
            }
        }
        return r;
    }
    
    public Integer altura(Nodo raiz){
        Integer ad=0, ai=0;
        if(raiz==null){
            return 0;
        }
        else{
            ai = 1 + altura(raiz.getIzq());            
            ad = 1 + altura(raiz.getDer());            
            if(ai>ad)
                return ai;
            else
                return ad;                        
        }
    }
    
     public Integer calcularFE(Nodo n) {
        if (n == null) {
            return 0;
        } else {
            return (altura(n.getDer()) - altura(n.getIzq()));
        }
    }
    
    public void MostrarRaiz ()
    {
        System.out.println("El valor de la raiz es: " + this.raiz.getValor());
    }
    
    public boolean EsVacia()
    {
        if (this.raiz == null)
        {
            return true;
        }
        else 
        {
            return false;
        }
    }
    
    public void MostrarVacia()
    {
        if (EsVacia() == true)
        {
            System.out.println("El arbol esta vacio");
        }
        else
        {
            System.out.println("El arbol no esta vacio");
        }
    }
    
    //Calcular y Mostarr Alturas
    
    public void RecalcularAlturas()
    {
        if (EsVacia() == true)
        {
            System.out.println("La altura del arbol es 0");
        }
        else
        {
            int alturamax = RCA(this.raiz);
        }
    }
    
    public int RCA(Nodo raizAux)
    {
        if ((raizAux.getIzq() == null) && (raizAux.getDer() == null))
        {
            raizAux.setAltura(1);
            return 1;
        }
        else if (raizAux.getDer() == null)
        {
            raizAux.setAltura(RCA(raizAux.getIzq())+1);
        }
        else if (raizAux.getIzq() == null)
        {
            raizAux.setAltura(RCA(raizAux.getDer())+1);
        }
        else if (raizAux.getIzq().getValor() > raizAux.getDer().getValor())
        {
            raizAux.setAltura(RCA(raizAux.getIzq())+1);
            RCA(raizAux.getDer());
        }
        else if (raizAux.getDer().getValor() > raizAux.getIzq().getValor())
        {
            raizAux.setAltura(RCA(raizAux.getDer())+1);
            RCA(raizAux.getIzq());
        }
        return raizAux.getAltura();
    }
    
    public void MostrarAlturas ()
    {
        if (EsVacia() == true)
        {
            MostrarVacia();
        }
        else
        {
            String mostrar = RecorrerAlturas(this.raiz);
            System.out.println(mostrar);
        }
    }
    
    public String RecorrerAlturas(Nodo raizAux)
    {
        String resultado = "";
        resultado = resultado + "\nLa altura del valor " + raizAux.getValor() + " es " + raizAux.getAltura();
        if (raizAux.getIzq() != null)
        {
            resultado = resultado + RecorrerAlturas(raizAux.getIzq());
        }
        if (raizAux.getDer() != null)
        {
            resultado = resultado + RecorrerAlturas(raizAux.getDer());
        }
        return resultado;
    }
    
    //Calcular y Mostrar FE
    
    public void RecalcularFE()
    {
        if (EsVacia() == true)
        {
            this.MostrarVacia();
        }
        else
        {
            RCFE(this.raiz);
        }
    }
    
    public void RCFE(Nodo raizAux)
    {
        if ((raizAux.getIzq() == null) && (raizAux.getDer() == null))
        {
            raizAux.setFe(0);
        }
        else if (raizAux.getDer() == null)
        {
            raizAux.setFe(-raizAux.getIzq().getAltura());
            RCFE(raizAux.getIzq());
        }
        else if (raizAux.getIzq() == null)
        {
            raizAux.setFe(raizAux.getDer().getAltura());
            RCFE(raizAux.getDer());
        }
        else
        {
            raizAux.setFe(raizAux.getDer().getAltura()-raizAux.getIzq().getAltura());
            RCFE(raizAux.getIzq());
            RCFE(raizAux.getDer());
        }
    }
    
    public void Reordenar ()
    {
        if (EsVacia() == true)
        {
            this.MostrarVacia();
        }
        else
        {
            BuscarDesiquilibrio(this.raiz);
        }
    }
    
    public Nodo BuscarDesiquilibrio(Nodo raizAux)
    {
        if(raizAux.getFe()<(-1))
        {
            if (raizAux.getIzq().getIzq() != null)
            {
                if (raizAux.getIzq().getDer() == null)
                {
                    return RotarII(raizAux);
                }
                if (altura(raizAux.getIzq().getIzq()) > altura(raizAux.getIzq().getDer()))
                {
                    return RotarII(raizAux);
                }
            }
            if (raizAux.getIzq().getDer() != null)
            {
                if (raizAux.getIzq().getIzq() == null)
                {
                    return RotarID(raizAux);
                }
                if (altura(raizAux.getIzq().getDer()) > altura(raizAux.getIzq().getIzq()))
                {
                    return RotarID(raizAux);
                }
            }
        }
        if (raizAux.getFe()>1)
        {
            if (raizAux.getDer().getDer() != null)
            {
                if (raizAux.getDer().getIzq() == null)
                {
                    return RotarDD(raizAux);
                }
                if (altura(raizAux.getDer().getDer()) > altura(raizAux.getDer().getIzq()))
                {
                    return RotarDD(raizAux);
                }
            }
            if (raizAux.getDer().getIzq() != null)
            {
                if (raizAux.getDer().getDer() == null)
                {
                    return RotarDI(raizAux);
                }
                if (altura(raizAux.getDer().getIzq()) > altura(raizAux.getDer().getDer()))
                {
                    return RotarDI(raizAux);
                }
            }
        }
        return raizAux;
    }
    
    public Nodo RotarII (Nodo raizAux)
    {
        Nodo n1 = raizAux.getIzq();
        raizAux.setIzq(n1.getDer());
        n1.setDer(raizAux);
        raizAux.setFe(calcularFE(raizAux));
        n1.setFe(calcularFE(n1));
        return n1;
    }
    
    public Nodo RotarDD (Nodo raizAux)
    {
        Nodo n1 = raizAux.getDer();
        raizAux.setDer(n1.getIzq());
        n1.setIzq(raizAux);
        raizAux.setFe(calcularFE(raizAux));
        n1.setFe(calcularFE(n1));
        return n1;
    }
    
    public Nodo RotarID (Nodo raizAux)
    {
        Nodo n1 = raizAux.getIzq();
        Nodo n2 = raizAux.getIzq().getDer();
        n1.setDer(n2.getIzq());
        n2.setIzq(n1);
        raizAux.setIzq(n2.getDer());
        n2.setDer(raizAux);
        raizAux.setFe(calcularFE(raizAux));
        n1.setFe(calcularFE(n1));
        n2.setFe(calcularFE(n2));
        return n2;
    }
    
    public Nodo RotarDI (Nodo raizAux)
    {
        Nodo n1 = raizAux.getDer();
        Nodo n2 = raizAux.getDer().getIzq();
        n1.setIzq(n2.getDer());
        n2.setDer(n1);
        raizAux.setDer(n2.getIzq());
        n2.setIzq(raizAux);
        raizAux.setFe(calcularFE(raizAux));
        n1.setFe(calcularFE(n1));
        n2.setFe(calcularFE(n2));
        return n2;
    }
    
     public JPanel getdibujo() {
        return new ArbolGraficado(this);
    }
     
    public boolean Vaciar()
    {
        raiz = null;
        return true;
    }
    
    public boolean Existe(int dato) {
        Nodo aux = raiz;
        while (aux != null) {
            if (dato == aux.getValor()) {
                return true;
            } else if (dato > aux.getValor()) {
                aux = aux.getDer();
            } else {
                aux = aux.getIzq();
            }
        }
        return false;
    }
}
