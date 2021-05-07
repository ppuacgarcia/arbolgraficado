
package arbolgrafico;

  /**
 *
 * @author Toloza XD
 */

import java.awt.*;
import java.util.*;
import javax.swing.*;


public class ArbolGraficado extends JPanel 
{
    private final Arbol arbol;
    private HashMap posicionN = null;
    private HashMap TsubA = null;
    private final int PH = 20;
    private final int HH = 30;
    private Dimension vacio = new Dimension(0,0);
    private FontMetrics fm = null;
    private boolean lleno = true;
    
    
    
    
    public ArbolGraficado(Arbol miArbol) 
    {     
        this.setBackground(Color.WHITE);
        posicionN = new HashMap();
        TsubA = new HashMap();
        this.arbol = miArbol;
        lleno = true;
        repaint();      
    }

    private void calcularPosiciones() 
    {
         posicionN.clear();
         TsubA.clear();
         Nodo root = this.arbol.getRaiz();
         if (root != null) 
         {
           TamanioSubArbol(root);
           calcularPosicion(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
         }
    }
    
    
    private Dimension TamanioSubArbol(Nodo num) 
    {
        
          if (num == null) {
            return new Dimension(0,0);
          }
          Dimension dimI = TamanioSubArbol(num.getIzq());
          Dimension dimD = TamanioSubArbol(num.getDer());
          
          int h = fm.getHeight() + PH + Math.max(dimI.height, dimD.height);
          int w = dimI.width + HH + dimD.width;
          
          Dimension dim = new Dimension(w, h);
          TsubA.put(num, dim);
          
          return dim;
    }
    private void calcularPosicion(Nodo num, int iz, int der, int al) 
    {
      if (num == null){ 
          return;
      }
      Dimension ld = (Dimension) TsubA.get(num.getIzq());
      if (ld == null) {
          ld = vacio;
      }
      Dimension rd = (Dimension) TsubA.get(num.getDer());
      if (rd == null) {
          rd = vacio;
      }
      int center = 0;
      
      if (der != Integer.MAX_VALUE){
          center = der - rd.width - HH/2;
      }else if (iz != Integer.MAX_VALUE){
          center = iz + ld.width + HH/2;
      }
      int width = fm.stringWidth(num.getValor()+"");
      
      
      posicionN.put(num,new Rectangle(center - width/2 - 3, al, width + 6, fm.getHeight()));
      int q=al + fm.getHeight() + PH;
      calcularPosicion(num.getIzq(), Integer.MAX_VALUE, center - HH/2, q);
      calcularPosicion(num.getDer(), center + HH/2, Integer.MAX_VALUE, q);
    }
    private void dibujarArbol(Graphics2D graph, Nodo num, int ypos, int puntoX, int puntoY) 
    {
     if (num == null){
         return;
     }
     Rectangle r = (Rectangle) posicionN.get(num);
     graph.draw(r);
     graph.drawString(num.getValor()+"", r.x + 3, r.y + ypos);
     int xp=r.x + r.width/2;
     if (puntoX != Integer.MAX_VALUE){  
        graph.drawLine(puntoX, puntoY, xp, r.y);
     }
     
     int yp=r.y + r.height;
     dibujarArbol(graph, num.getIzq(), xp, yp, ypos);
     dibujarArbol(graph, num.getDer(), xp, yp, ypos);
     
   }  
   public void paint(Graphics graph) 
   {
        
         fm = graph.getFontMetrics();

         if (lleno==true) 
         {
           calcularPosiciones();
           lleno = false;
         }
         
         Graphics2D graficos = (Graphics2D) graph;
         graficos.translate(getWidth() / 2, PH);
         dibujarArbol(graficos, this.arbol.getRaiz(), Integer.MAX_VALUE, Integer.MAX_VALUE, 
         fm.getLeading() + fm.getAscent());
         fm = null;
   }
   
 }




