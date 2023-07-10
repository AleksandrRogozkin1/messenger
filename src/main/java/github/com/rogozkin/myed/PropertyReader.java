package github.com.rogozkin.myed;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    public static String getHostForSocket() {
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return null;
            }

            prop.load(input);

            return prop.getProperty("server.host");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static int getPortForSocket() {
        try (InputStream input = PropertyReader.class.getClassLoader()
                .getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");

            }

            prop.load(input);

            return Integer.parseInt(prop.getProperty("server.port"));
        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return 0;
    }
}
