
package arbolBB;

import java.util.ArrayList;
import java.util.LinkedList;
import javax.swing.JPanel;

/**
 *
 * @author Toloza XD
 */
public class ArbolSimulado {

    Arbol miArbol = new Arbol();
    
    public ArbolSimulado() {
    }

    public boolean insertar(Integer dato) {
        return (this.miArbol.Insertar(dato));
    }

    public JPanel getDibujo() {
        return this.miArbol.getdibujo();
    }
  
    public boolean Vaciar() {
        return (this.miArbol.Vaciar());
    }
    
}
