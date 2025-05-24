package top.redstarmc.plugin.redstarlib;

import org.bukkit.plugin.java.JavaPlugin;
import top.redstarmc.plugin.redstarlib.impl.ImplConfigManager;
import top.redstarmc.plugin.redstarlib.impl.ImplServerManager;
import top.redstarmc.plugin.redstarlib.manager.ConfigurationManager;
import top.redstarmc.plugin.redstarlib.manager.ServerManager;

public final class RedStarLib extends JavaPlugin implements RedStarLibInterface{

    private ConfigurationManager configManager;

    private ServerManager serverManager;

    @Override
    public void onEnable() {
        loadManager();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @Override
    public void loadManager() {
        configManager = new ImplConfigManager();
        serverManager = new ImplServerManager();
    }

    public ConfigurationManager getConfigManager() {
        return configManager;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }
}
