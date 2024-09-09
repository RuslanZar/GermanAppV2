package RuslanCode.germanAppV2.utilities;

import java.io.IOException;
import java.util.Properties;

public class ProjectDataReader {
    static Properties props = new Properties();

    public static String getVersion() throws IOException {
        props.load(ProjectDataReader.class.getResourceAsStream("/version.properties"));
        return props.getProperty("version");
    }

}