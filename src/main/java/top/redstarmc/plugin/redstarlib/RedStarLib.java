package top.redstarmc.plugin.redstarlib;

import org.bukkit.plugin.java.JavaPlugin;
import top.redstarmc.plugin.redstarlib.impl.ImplConfigManager;
import top.redstarmc.plugin.redstarlib.impl.ImplLoggerManager;
import top.redstarmc.plugin.redstarlib.impl.ImplServerManager;
import top.redstarmc.plugin.redstarlib.manager.ConfigurationManager;
import top.redstarmc.plugin.redstarlib.manager.LoggerManager;
import top.redstarmc.plugin.redstarlib.manager.ServerManager;

/**
 * <h1>RedStarLib 插件主类</h1>
 * 实现 {@link RedStarLibInterface} 接口，同时也可作为其他使用本插件开发的插件代码规范。 <br>
 * 内部维护了多个字段，不可被继承（ final 类）。
 */
public final class RedStarLib extends JavaPlugin implements RedStarLibInterface{

    private static RedStarLib instance;

    public String INFO_PREFIX = "[RedStarLib]";

    private ConfigurationManager configManager;

    private LoggerManager loggerManager;

    private ServerManager serverManager;

    @Override
    public void onEnable() {
        instance = this;
        loadManager();

        loggerManager.debugDataBase("a");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void loadManager() {
        configManager = new ImplConfigManager();
        configManager.init();

        loggerManager = new ImplLoggerManager(INFO_PREFIX, true);

        serverManager = new ImplServerManager(INFO_PREFIX);
    }

    public static RedStarLib getInstance() {
        return instance;
    }

    public ConfigurationManager getConfigManager() {
        return configManager;
    }

    public LoggerManager getLoggerManager() {
        return loggerManager;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }
}
