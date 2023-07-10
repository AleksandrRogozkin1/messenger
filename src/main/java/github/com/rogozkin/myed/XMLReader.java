package github.com.rogozkin.myed;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class XMLReader {
    private static final String PATH_CONFIG_FILE = "src/main/resources/config.xml";

    public static int PORT;
    public static String HOST_IP;
    public static String SERVER_PASSWORD;

    static {
        Properties properties = new Properties();
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(PATH_CONFIG_FILE);
            properties.loadFromXML(fis);

            PORT = Integer.parseInt(properties.getProperty("server.port"));
            HOST_IP = properties.getProperty("server.host");
            SERVER_PASSWORD=properties.getProperty("server.password");

        } catch (FileNotFoundException e) {
            System.err.println("File not found");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Read/write error");
            e.printStackTrace();
        } finally {
            try {
                fis.close();
            } catch (IOException e) {
                System.err.println("fis closing error");
                e.printStackTrace();
            }
        }

    }
}
