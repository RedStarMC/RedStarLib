package top.redstarmc.plugin.redstarlib;

import org.bukkit.plugin.java.JavaPlugin;
import top.redstarmc.plugin.redstarlib.impl.ImplConfigManager;
import top.redstarmc.plugin.redstarlib.impl.ImplLoggerManager;
import top.redstarmc.plugin.redstarlib.impl.ImplServerManager;
import top.redstarmc.plugin.redstarlib.manager.ConfigurationManager;
import top.redstarmc.plugin.redstarlib.manager.LoggerManager;
import top.redstarmc.plugin.redstarlib.manager.ServerManager;

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

        loggerManager.info("a");

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void loadManager() {
        configManager = new ImplConfigManager();
        configManager.init();

        loggerManager = new ImplLoggerManager(INFO_PREFIX);

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
