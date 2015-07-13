package se.bth.swatkats.letstalk;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.MissingResourceException;
import java.util.Properties;
import javax.swing.JOptionPane;

public class MessageBundle {

    private static String BUNDLE_NAME = "lib" + File.separator + "client.properties"; //$NON-NLS-1$

    private static Properties prop = new Properties();

    static {
        try {
            prop.load(new FileInputStream(new File(BUNDLE_NAME)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MessageBundle() {
    }

    public static String getString(String key) {
        try {
            if (key.equals("Constants.transformation")) {
                return prop.getProperty(key);
            } else {
                return checkSeperator(prop.getProperty(key));
            }
        } catch (MissingResourceException e) {
            return '!' + key + '!';
        }
    }

    public static void changeValue(String key, String value) {
        prop.replace(key, value);
    }

    public static void save() {
        try {
            Writer writer = new FileWriter(BUNDLE_NAME);
            prop.store(writer, "");
            writer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static String changeHostIp() {
        String newHostIp = JOptionPane.showInputDialog("Unable to locate server, please provide new Server IP.");
        prop.replace("Constants.HOSTIP", newHostIp);
        save();
        return newHostIp;
    }

    /**
     * Adjust program to the appropriate file separator.
     *
     * @param old
     * @return the path with the correct file separator
     */
    public static String checkSeperator(String old) {
        char falseSeperator;
        if ("/".equals(File.separator)) {
            falseSeperator = '\\';
        } else {
            falseSeperator = '/';
        }
            // '\' = windows
        // '/' = linux
        return old.replace(falseSeperator, File.separator.charAt(0));
    }
}
