package top.redstarmc.plugin.redstarlib.manager;

import org.bukkit.Bukkit;
import top.redstarmc.plugin.redstarlib.utils.toStrings;

public abstract class LoggerManager {

    public String INFO_PREFIX;

    public LoggerManager(String INFO_PREFIX) {
        this.INFO_PREFIX = INFO_PREFIX;
    }

    public boolean debugMode;

    /**
     * <h2>发送插件普通信息</h2>
     * @param messages 字符串
     */
    public final void info(String... messages) {
        if (messages == null) return;
        for (String message : messages) {
            if (message == null) continue;
            Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§a[INFO] §r" + message + "§r");
        }
    }

    /**
     * <h2>发送插件格式化信息</h2>
     * @param messages 字符串
     * @param objects 传入的格式化内容
     */
    public final void info(String messages,Object... objects) {
        if (messages == null) return;
        Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§a[INFO] §r" + toStrings.format(messages,objects) + "§r");
    }

    /**
     * <h2>发送插件警告信息</h2>
     * @param messages 字符串
     */
    public final void warn(String... messages) {
        if (messages == null) return;
        for (String message : messages) {
            if (message == null) continue;
            Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§e[WARN] §r" + message + "§r");
        }
    }

    /**
     * <h2>发送插件错误信息</h2>
     * @param messages 字符串
     */
    public final void error(String... messages) {
        if (messages == null) return;
        for (String message : messages) {
            if (message == null) continue;
            Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§c[ERROR] §r" + message + "§r");
        }
    }

    /**
     * <h2>发送插件debug信息</h2>
     * @param messages 字符串
     */
    public final void debug(String... messages) {
        if (messages == null) return;
        if (isDebugMode()) {
            for (String message : messages) {
                if (message == null) continue;
                Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§6[DEBUG] §r" + message + "§r");
            }
        }
    }

    /**
     * <h2>发送插件debug堆栈</h2>
     * @param e 堆栈
     */
    public final void debug(Throwable e) {
        if (e == null) return;
        if (isDebugMode())
            e.printStackTrace();
    }

    /**
     * <h2>同时发送插件debug信息和堆栈</h2>
     * @param e 堆栈
     * @param msg 字符串
     */
    public final void debug(String msg, Throwable e) {
        if (msg == null || e == null) return;
        if (isDebugMode()) {
            debug(msg);
            debug(e);
        }
    }




    public String getINFO_PREFIX() {
        return INFO_PREFIX;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

}
