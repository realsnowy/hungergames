package tf.justdisablevac.hungergames;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Configuration extends YamlConfiguration {
    private final File configFile;

    public Configuration(String configFile) {
        File pluginDataFolder = Main.plugin.getDataFolder();
        this.configFile = new File(pluginDataFolder, configFile);
        loadConfig();
    }

    public Configuration() {
        this("config.yml");
    }

    private void loadConfig() {
        try {
            if (!configFile.exists()) {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            }
            load(configFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void saveConfig() {
        try {
            save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void set(String path, Object value) {
        super.set(path, value);
        saveConfig(); // Automatically save changes when set() is called
    }
}
