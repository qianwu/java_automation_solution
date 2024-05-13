package charlotte.automation.configurations;

import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class ConfigManager {
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.yaml";

    public static Map<String,Object> getConfigurations(){
        Yaml yaml = new Yaml();
        try{
            InputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
            Map<String, Object> config = yaml.load(inputStream);
            inputStream.close();
            return config;
        } catch (FileNotFoundException e){
            e.printStackTrace();

        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    //enable get configuration by yaml path as key, e.g. "api.base_url"
    public static Object getConfigurations(String key){
        Map<String, Object> config = getConfigurations();
        if (config == null) {
            return null;
        }
        String[] keys = key.split("\\.");
        Map<String, Object> current = config;
        for (String k : keys) {
            if (current.containsKey(k)) {
                Object value = current.get(k);
                if (value instanceof Map) {
                    current = (Map<String, Object>) value;
                } else {
                    return value;
                }
            } else {
                return null;
            }
        }
        return current;
    }

    // enable get configuration map by yaml path as key, e.g. "api.headers"
    public static Map<String, Object> getConfigurationsMap(String key){
        Map<String, Object> config = getConfigurations();
        if (config == null) {
            return null;
        }
        String[] keys = key.split("\\.");
        Map<String, Object> current = config;
        for (String k : keys) {
            if (current.containsKey(k)) {
                Object value = current.get(k);
                if (value instanceof Map) {
                    current = (Map<String, Object>) value;
                } else {
                    return null;
                }
            } else {
                return null;
            }
        }
        return current;
    }
}
