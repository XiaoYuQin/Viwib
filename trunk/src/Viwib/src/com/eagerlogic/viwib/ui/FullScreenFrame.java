/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eagerlogic.viwib.ui;

import com.eagerlogic.viwib.config.Config;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.JPanel;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.embedded.FullScreenStrategy;
import uk.co.caprica.vlcj.player.embedded.x.XFullScreenStrategy;
import uk.co.caprica.vlcj.runtime.windows.WindowsMouseHook;

/**
 *
 * @author dipacs
 */
public class FullScreenFrame extends javax.swing.JFrame {

    public EmbeddedMediaPlayerComponent mpComponent;
    private JPanel mousePanel;
    private long lastMouseMove = System.currentTimeMillis();
    private Timer hideTimer;
    private TimerTask hideTimerTask = new TimerTask() {
        @Override
        public void run() {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    setPlayerControlVisible(false);
                }
            });
        }
    };

    private synchronized long getLastMouseMove() {
        return lastMouseMove;
    }

    private synchronized void updateLastMouseMove() {
        lastMouseMove = System.currentTimeMillis();
    }

    private synchronized boolean getPlayerControlVisible() {
        return this.playerControl.isVisible();
    }

    private synchronized void setPlayerControlVisible(boolean value) {
        this.playerControl.setVisible(value);
    }

    /**
     * Creates new form FullScreenFrame
     */
    public FullScreenFrame() {
        try {
            setExtendedState(JFrame.MAXIMIZED_BOTH);
        } catch (Exception ev) {
            ev.printStackTrace();
            setBounds(getGraphicsConfiguration().getDevice().getDefaultConfiguration().getBounds());
        }
        initComponents();

        mpComponent = new EmbeddedMediaPlayerComponent();

        mousePanel = new JPanel();
        mousePanel.setBackground(Color.BLACK);
        mousePanel.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                //resetHideTimer();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                setPlayerControlVisible(true);
            }
        });
        jLayeredPane1.add(mousePanel);
        jLayeredPane1.add(mpComponent);
        jLayeredPane1.setLayer(mpComponent, 1);
        jLayeredPane1.setLayer(mousePanel, 2);
        jLayeredPane1.setLayer(playerControl, 3);
        
        playerControl.setVisible(false);
        playerControl.setOnHideListener(new Runnable() {

            @Override
            public void run() {
                setPlayerControlVisible(false);
            }
        });

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //System.out.println(getWidth() + ", " + getHeight());
                jLayeredPane1.setSize(getWidth(), getHeight());
                mousePanel.setBounds(0, getHeight() - 5, getWidth(), getHeight());
                mpComponent.setSize(getWidth(), getHeight());
                playerControl.setBounds(0, getHeight() - 101, getWidth(), 100);

//                hideTimer = new Timer();
//                hideTimer.schedule(hideTimerTask, 5000);
            }
        });



    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The
     * content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        playerControl = new com.eagerlogic.viwib.ui.player.PlayerControlPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        playerControl.setBounds(0, 380, 762, 100);
        jLayeredPane1.add(playerControl, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 764, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FullScreenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FullScreenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FullScreenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FullScreenFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FullScreenFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    public com.eagerlogic.viwib.ui.player.PlayerControlPanel playerControl;
    // End of variables declaration//GEN-END:variables
}
