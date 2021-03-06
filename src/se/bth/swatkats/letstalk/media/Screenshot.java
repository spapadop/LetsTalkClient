package se.bth.swatkats.letstalk.media;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import se.bth.swatkats.letstalk.connection.GuiHandler;

/**
 * Captures a screenshot and uploads it to the running conversation.
 *
 * @author Chatzakis Nikolaos, Nikolaos Kondylidis, Sokratis Papadopoulos
 */
public class Screenshot extends javax.swing.JFrame {

    private String fileName; //the file name connected with the screenshot

    /**
     * Creates new form Screenshot
     */
    public Screenshot() {
        initComponents();
        //setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("../gui/img/iconHead.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ScreenShot = new javax.swing.JDialog();
        ss = new javax.swing.JLabel();
        sendButt = new javax.swing.JButton();
        cancelButt = new javax.swing.JButton();
        tkscrnBT = new javax.swing.JButton();

        ScreenShot.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        ScreenShot.setModal(true);

        sendButt.setText("Send");
        sendButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendButtActionPerformed(evt);
            }
        });

        cancelButt.setText("Cancel");
        cancelButt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ScreenShotLayout = new javax.swing.GroupLayout(ScreenShot.getContentPane());
        ScreenShot.getContentPane().setLayout(ScreenShotLayout);
        ScreenShotLayout.setHorizontalGroup(
            ScreenShotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScreenShotLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ScreenShotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ScreenShotLayout.createSequentialGroup()
                        .addComponent(sendButt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButt))
                    .addComponent(ss))
                .addContainerGap(249, Short.MAX_VALUE))
        );
        ScreenShotLayout.setVerticalGroup(
            ScreenShotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ScreenShotLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ss)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 249, Short.MAX_VALUE)
                .addGroup(ScreenShotLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sendButt)
                    .addComponent(cancelButt))
                .addContainerGap())
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        tkscrnBT.setText("Take Screenshot");
        tkscrnBT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tkscrnBTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tkscrnBT)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tkscrnBT)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tkscrnBTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tkscrnBTActionPerformed
        try {

            Rectangle rec = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage capt = new Robot().createScreenCapture(rec);

            // Save Image
            Date now = new Date();
            SimpleDateFormat df = new SimpleDateFormat("ddMyyhhmmss");
            fileName = "Screenshot_" + GuiHandler.getInstance().getUser().getUsername() + "_" + df.format(now) + ".png";
            ImageIO.write(capt, "png", new File(fileName));

            // Resize and show at JLabel
            ImageIcon icon = new ImageIcon(capt);

            //get screen resolution
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            int width = (int) screenSize.getWidth();
            int height = (int) screenSize.getHeight();
            Image image = icon.getImage(); // transform it
            Image newimg = image.getScaledInstance(width / 2, height / 2, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way 
            icon = new ImageIcon(newimg);  // transform it back
            ss.setSize(width / 2, height / 2);
            ss.setIcon(icon);

            this.setVisible(false);
            //setting dialog
//            ScreenShot.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource(".." 
//                    + File.separator + "gui" + File.separator + "img" + File.separator +"iconHead.png")));
            ScreenShot.setSize(width / 2 + 25, height / 2 + 100);
            ScreenShot.setLocationRelativeTo(null);
            ScreenShot.setVisible(true);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed!");
            System.out.println(ex.getMessage());
        }
    }//GEN-LAST:event_tkscrnBTActionPerformed

    /**
     * Sends the screenshot to the conversation and then dispose the window.
     *
     * @param evt
     */
    private void sendButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendButtActionPerformed
        GuiHandler.getInstance().getGui().uploadFile(fileName);
        ScreenShot.setVisible(false);
//        ScreenShot.dispose();
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_sendButtActionPerformed

    /**
     * Cancels the sending of the screenshot deleting it as well.
     *
     * @param evt
     */
    private void cancelButtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtActionPerformed
        File f = new File(fileName);
        f.delete();
        ScreenShot.setVisible(false);
//        ScreenShot.dispose();

        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_cancelButtActionPerformed

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public JButton getTkscrnBT() {
        return tkscrnBT;
    }

    public void setTkscrnBT(JButton tkscrnBT) {
        this.tkscrnBT = tkscrnBT;
    }

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
            java.util.logging.Logger.getLogger(Screenshot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Screenshot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Screenshot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Screenshot.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Screenshot().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog ScreenShot;
    private javax.swing.JButton cancelButt;
    private javax.swing.JButton sendButt;
    private javax.swing.JLabel ss;
    private javax.swing.JButton tkscrnBT;
    // End of variables declaration//GEN-END:variables
}
