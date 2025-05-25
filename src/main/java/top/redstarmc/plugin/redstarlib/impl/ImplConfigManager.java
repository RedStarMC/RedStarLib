package top.redstarmc.plugin.redstarlib.impl;

import org.bukkit.configuration.file.YamlConfiguration;
import top.redstarmc.plugin.redstarlib.RedStarLib;
import top.redstarmc.plugin.redstarlib.manager.ConfigurationManager;
import top.redstarmc.plugin.redstarlib.utils.ConfigMapBuilder;

import java.io.File;
import java.util.Map;
import java.util.Objects;

public class ImplConfigManager extends ConfigurationManager {

    public static final Map<String, Object> default_config = ConfigMapBuilder.of(String.class, Object.class)
            .set("Debug", false)
            .toMap();


    private static final File config_file = new File(RedStarLib.getInstance().getDataFolder(),"config.yml");

    private static YamlConfiguration config;

    private static final String versioning = "${version}";

    @Override
    public void init() {

        config = initFile(config_file);

        if (!Objects.equals(config.getString("Versioning"), versioning)) {

            config = new YamlConfiguration();

            saveMapConfig(default_config, config ,config_file);
            config.set("Versioning", versioning);
            save(config, config_file);
        }
    }

    public static YamlConfiguration getConfig() {
        return config;
    }
}
