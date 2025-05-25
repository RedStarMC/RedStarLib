package top.redstarmc.plugin.redstarlib.manager;

import org.bukkit.Bukkit;

public abstract class ServerManager {

    public String INFO_PREFIX;

    public ServerManager(String INFO_PREFIX) {
        this.INFO_PREFIX = INFO_PREFIX;
    }

    /**
     * <h2>向服务器发送聊天栏消息</h2>
     * @param messages 信息字符串
     */
    public void broadcast(String... messages) {
        for (String message : messages) {
            Bukkit.broadcastMessage(message);
        }
    }

    /**
     * <h2>发送带前缀的聊天栏消息</h2>
     * @param messages 信息字符串
     */
    public void broadcastPrefix(String... messages) {
        if (messages == null) return;
        for (String message : messages) {
            if (message == null) continue;
            broadcast(INFO_PREFIX + message + "§r");
        }
    }







}
