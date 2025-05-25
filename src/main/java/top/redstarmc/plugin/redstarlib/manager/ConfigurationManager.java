package top.redstarmc.plugin.redstarlib.manager;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * <h1>配置管理器</h1>
 * 抽象类，提供了 {@link YamlConfiguration} 相关的操作代码，以免重复编写IO操作流。
 */
public abstract class ConfigurationManager {

    /**
     * <h2>初始化方法</h2>
     */
    public abstract void init();

    /**
     * <h2>初始化文件</h2>
     * 如果没有这个文件就自动创建，并返回 {@link YamlConfiguration} 配置文件，方便操作
     * @param configFile {@link File} 格式的文件，只需要指定路径和文件名称
     * @return 读取到的 {@link YamlConfiguration} 配置文件
     */
    public YamlConfiguration initFile(File configFile){
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
     * @param config {@link YamlConfiguration} 类型的配置文件，可以直接进行内存读写操作
     * @param configFile {@link File} 类型文件，用于将内存中的配置文件保存
     */
    public void saveMapConfig(Map<String, Object> configMap, YamlConfiguration config, File configFile){
        if (!configFile.exists()) return;

        configMap.forEach(config::set);

        save(config, configFile);
    }

    /**
     * <h2>从内存中保存 {@link YamlConfiguration} 格式文件</h2>
     * @param config {@link YamlConfiguration} 内存中配置文件
     * @param configFile {@link File} IO配置文件
     */
    public void save(YamlConfiguration config, File configFile){
        try {
            config.save(configFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
