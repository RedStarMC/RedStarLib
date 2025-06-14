package top.redstarmc.plugin.redstarlib.impl;

import org.bukkit.configuration.file.YamlConfiguration;
import top.redstarmc.plugin.redstarlib.RedStarLib;
import top.redstarmc.plugin.redstarlib.manager.ConfigurationManager;
import top.redstarmc.plugin.redstarlib.utils.ConfigMapBuilder;

import java.io.File;
import java.util.Map;

public class ImplConfigManager extends ConfigurationManager {

    public static final Map<String, Object> default_config = ConfigMapBuilder.of(String.class, Object.class)
            .set("Debug", false)
            .toMap();


    private static final File config_file = new File(RedStarLib.getInstance().getDataFolder(),"plugin.yml");

    private static final String versioning = "0.0.0";

    public ImplConfigManager(){
        super(config_file);
    }

    @Override
    public void init() {

        YamlConfiguration config = getConfig();

        saveJarConfig("plugin.yml", RedStarLib.getInstance());

//        if (!Objects.equals(config.getString("Versioning"), versioning)) {
//
//            config = new YamlConfiguration();
//
//            saveMapConfig(default_config);
//            config.set("Versioning", versioning);
//            save();
//        }
    }

}
