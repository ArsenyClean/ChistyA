package store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Settings {

    private final Properties properties;

    private Logger LOG = LoggerFactory.getLogger(Settings.class);

    public Settings() {
        this.properties = new Properties();
        this.load();
    }

    private void load() {
        String way = "src/main/resources/properties/application.properties";
        try (FileInputStream fileInputStream1 = new FileInputStream(way)) {
            this.properties.load(fileInputStream1);
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public String getValue(final String key) {
        return this.properties.getProperty(key);
    }
}