package top.redstarmc.plugin.redstarlib.manager;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <h1>配置管理器</h1>
 * 抽象类，提供了 {@link YamlConfiguration} 相关的操作代码，以免重复编写 IO流 相关操作。
 * 使用方法：1.直接使用，new 出实例即可；2.创建子类，super 然后 new 子类即可。
 * 注意事项：new本类会自动读取配置文件，但并不会保存默认的配置文件，如果你想保存默认配置文件，使用 {@link #saveMapConfig} 或 {@link #saveJarConfig}
 */
public abstract class ConfigurationManager {

    /**
     * <h2>初始化方法</h2>
     * 若继承，则需使用该实例。
     */
    public abstract void init();

    private final File configFile;

    private YamlConfiguration config;

    public ConfigurationManager(File configFile) {
        this.configFile = configFile;

        config = YamlConfiguration.loadConfiguration(configFile);
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
        if (!configFile.exists()) initFile();

        configMap.forEach(config::set);

        save();
    }

    /**
     * <h2>从 Jar 包中保存配置文件</h2>
     * @param file 文件名，请务必和你创建 {@link File} 中的文件名保持一致
     * @param plugin 你插件的主类，若在主类中运行可以使用 this 传入。
     */
    public <T extends JavaPlugin> void saveJarConfig(String file, T plugin){
        plugin.saveResource(file,true);

        config = YamlConfiguration.loadConfiguration(configFile);
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
