/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Артур
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import org.jdesktop.swingx.JXImageView;
import java.awt.FileDialog;
import java.io.File;

public class ImagePanel extends javax.swing.JPanel{
    /**
     * Creates new form ImagePanel
     */
    public ImagePanel() {
        initComponents();
        imageView = new JXImageView();
        imageView.setSize(this.getWidth(), this.getHeight());
        //image = Toolkit.getDefaultToolkit().getImage("Duke_Blocks.gif");
    }
    public ImagePanel(String filename) {
        initComponents();
        imageView = new JXImageView();
        imageView.setSize(this.getWidth(), this.getHeight());
        setImage(filename);
    }
    public void setImage(String filename){
        try{
            imageView.setImage(new File(filename));
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,ex.toString());
        }
        repaint();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 413, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    //---------------------------------------------------------------------
    JXImageView imageView;
}
