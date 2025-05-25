package top.redstarmc.plugin.redstarlib;

import org.bukkit.plugin.java.JavaPlugin;
import top.redstarmc.plugin.redstarlib.impl.ImplConfigManager;
import top.redstarmc.plugin.redstarlib.impl.ImplServerManager;
import top.redstarmc.plugin.redstarlib.manager.ConfigurationManager;
import top.redstarmc.plugin.redstarlib.manager.ServerManager;

public final class RedStarLib extends JavaPlugin implements RedStarLibInterface{

    private static RedStarLib instance;

    private ConfigurationManager configManager;

    private ServerManager serverManager;

    @Override
    public void onEnable() {
        instance = this;
        loadManager();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void loadManager() {
        configManager = new ImplConfigManager();
        configManager.init();

        serverManager = new ImplServerManager();
    }

    public static RedStarLib getInstance() {
        return instance;
    }

    public ConfigurationManager getConfigManager() {
        return configManager;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }
}
