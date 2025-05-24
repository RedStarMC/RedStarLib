package top.redstarmc.plugin.redstarlib.impl;

import org.bukkit.Bukkit;
import top.redstarmc.plugin.redstarlib.manager.ServerManager;

public class ImplServerManager extends ServerManager {

    @Override
    public void broadcast(String... messages) {
        for (String message : messages) {
            Bukkit.broadcastMessage(message);
        }
    }

}
