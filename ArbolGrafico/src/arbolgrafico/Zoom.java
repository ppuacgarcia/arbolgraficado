package arbolgrafico;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Thanasis1101
 * @version 1.0
 */
public class Zoom extends JPanel implements MouseWheelListener, MouseListener, MouseMotionListener {
    
    private final BufferedImage image;
    
    private double FactorZoom = 1;
    private double FactorZoomAnt = 1;
    private boolean zoomer;
    private boolean presionado;
    private boolean newimg=false;
    private boolean soltado;
    private double posX = 0;
    private double posY = 0;
    private int difX;
    private int difY;
    private Point inicio;

    private double contador=0;
    

    public Zoom(BufferedImage image) {
        //this.xOffset=-3550;     
        this.image = image;
        
        initComponent();

    }

    private void initComponent() {
        addMouseWheelListener(this);
        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void paint(Graphics g) {
       
        super.paint(g);

        Graphics2D graficos = (Graphics2D) g;
       
        if (zoomer==true) {
            AffineTransform at = new AffineTransform();

            double xRel = MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen().getX();
            double yRel = MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen().getY();

            double zoomDiv = FactorZoom / FactorZoomAnt;

            posX = (zoomDiv) * (posX) + (1 - zoomDiv) * xRel;
            posY = (zoomDiv) * (posY) + (1 - zoomDiv) * yRel;

            at.translate(posX, posY);
            at.scale(FactorZoom, FactorZoom);
            FactorZoomAnt = FactorZoom;
            graficos.transform(at);
            zoomer = false;
        }

        if (presionado==true) {
            AffineTransform at = new AffineTransform();
            at.translate(posX + difX, posY + difY);
            at.scale(FactorZoom, FactorZoom);
            graficos.transform(at);

            if (soltado) {
                posX += difX;
                posY+= difY;
                presionado = false;
            }
            

        }
        if(newimg==true){ 
            
            AffineTransform at = new AffineTransform();
            this.posX -=contador*55;
            
            at.translate(this.posX, this.posY);
            graficos.transform(at);
            this.newimg=false;
        }
        

        // All drawings go here
        
        graficos.drawImage(image, 0, 0, this);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {

        zoomer = true;

        //Zoom in
       
        if (e.getWheelRotation() < 0) {
            FactorZoom *= 1.1;
            repaint();
        }
        //Zoom out
        if (e.getWheelRotation() > 0) {
            FactorZoom /= 1.1;
            repaint();
        }
    }
   
    public void newimg(int n){
      
       contador=n-1;
       
       
        newimg=true;
        repaint();
    }
    
    @Override
    public void mouseDragged(MouseEvent e) {
        Point curPoint = e.getLocationOnScreen();
        difX = curPoint.x - inicio.x;
        difY = curPoint.y - inicio.y;

        presionado = true;
        repaint();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        soltado = false;
        inicio= MouseInfo.getPointerInfo().getLocation();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        soltado = true;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
