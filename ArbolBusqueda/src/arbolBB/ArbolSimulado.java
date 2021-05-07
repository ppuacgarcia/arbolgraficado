
package arbolBB;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Toloza XD
 */
public class ArbolSimulado {

    Arbol grafico = new Arbol();
    
    public ArbolSimulado() {
    }

    public boolean insertar(Integer dato) {
        return (this.grafico.Insertar(dato));
    }
    public boolean Vaciar() {
        return (this.grafico.Vaciar());
    }

    public JPanel getDibujo() {
        return this.grafico.getdibujo();
    }
  
    
    
}
