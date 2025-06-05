package top.redstarmc.plugin.redstarlib.manager;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <h1>配置管理器</h1>
 * 抽象类，提供了 {@link YamlConfiguration} 相关的操作代码，以免重复编写 IO流 相关操作。
 * 使用方法：
 * 1.直接使用，new 出实例即可。
 * 2.创建子类，super 然后 new 子类即可。
 */
public abstract class ConfigurationManager {

    /**
     * <h2>初始化方法</h2>
     * 若继承，则需使用该实例。
     */
    public abstract void init();

    private final File configFile;

    private final YamlConfiguration config;

    public ConfigurationManager(File configFile, YamlConfiguration config) {
        this.configFile = configFile;
        this.config = config;
    }

    public ConfigurationManager(ConfigurationManager manager){
        this.configFile = manager.getConfigFile();
        this.config = manager.getConfig();
    }

    /**
     * <h2>创建文件</h2>
     * 如果没有这个文件就自动创建，并返回 {@link YamlConfiguration} 配置文件，方便操作
     * @return 读取到的 {@link YamlConfiguration} 配置文件
     */
    public YamlConfiguration initFile(){
        if(!configFile.exists()){
            try {
                configFile.getParentFile().mkdirs();
                configFile.createNewFile();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        return YamlConfiguration.loadConfiguration(configFile);
    }

    /**
     * <h2>保存 {@link Map} 类型的配置文件</h2>
     * @param configMap {@link Map} 类型的配置文件
     */
    public void saveMapConfig(Map<String, Object> configMap){
        if (!configFile.exists()) return;

        configMap.forEach(config::set);

        save();
    }

    public void saveJarConfig(String fileName){


    }

    /**
     * <h2>从内存中保存 {@link YamlConfiguration} 格式文件</h2>
     */
    public void save(){
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public File getConfigFile() {
        return configFile;
    }

    public YamlConfiguration getConfig() {
        return config;
    }
}
