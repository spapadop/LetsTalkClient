package se.bth.swatkats.letstalk.media;

import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javaFlacEncoder.FLACFileWriter;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.swing.JOptionPane;
import se.bth.swatkats.letstalk.connection.GuiHandler;

/**
 * <p>
 * @{code RecNSave} </p>
 *
 * @author Chatzakis Nikolaos
 * <i> Created 11 Ιουλ 2015 </i>
 */
public class RecNSave {

    private String lastFilename;

    public RecNSave() {

        try {
            // encoding, sample rate, sample size in bits, channels (2=stereo)
            // frame size, frame rate, bigEndian
            AudioFormat format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                    44100, 16, 2, 4, 44100, false);

            DataLine.Info info = new DataLine.Info(TargetDataLine.class, format);

            if (!AudioSystem.isLineSupported(info)) {
                // Fail Inform
                JOptionPane.showMessageDialog(null, "This line is not supported!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (TargetDataLine targetLine = (TargetDataLine) AudioSystem.getTargetDataLine(format)) {
                targetLine.open();      // Open Line
                targetLine.start();     // Record Line

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        AudioInputStream audioSteam = new AudioInputStream(targetLine);

                        //setting the file name
                        Date now = new Date();
                        SimpleDateFormat df = new SimpleDateFormat("ddMyyhhmmss");
                        lastFilename = "Record_" + GuiHandler.getInstance().getUser().getUsername() + "_" + df.format(now) + ".flac";
                        File recordFile = new File(lastFilename);   // Create file
                        try {
                            AudioSystem.write(audioSteam, FLACFileWriter.FLAC, recordFile);   // Save to file
                        } catch (IOException ex) {
                            System.out.println("Inner class .write() exception..");
                            System.out.println(ex.getMessage());
                        }
                    }
                };

                thread.start();

                JOptionPane.showMessageDialog(null, "You are now recording!\n Press 'Okay' to finish recording and send it.");

                targetLine.stop();      // Stop Recording
                targetLine.close();     // Close
            } // Open Line

        } catch (HeadlessException | LineUnavailableException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public String getLastFilename() {
        return lastFilename;
    }

    public void setLastFilename(String lastFilename) {
        this.lastFilename = lastFilename;
    }
}
