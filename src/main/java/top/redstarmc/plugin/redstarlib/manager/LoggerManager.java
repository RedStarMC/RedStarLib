package top.redstarmc.plugin.redstarlib.manager;

import org.bukkit.Bukkit;
import top.redstarmc.plugin.redstarlib.utils.toStrings;

/**
 * <h1>日志管理器</h1>
 * 抽象类，提供了日志相关的操作代码，以免重复编写发送日志的操作。
 * 使用方法：
 * 1.直接使用，new 出实例即可。
 * 2.创建子类，super 然后 new 子类即可。
 */
public abstract class LoggerManager {

    public String INFO_PREFIX;

    public boolean debugMode;

    public LoggerManager(String INFO_PREFIX, boolean debugMode) {
        this.INFO_PREFIX = INFO_PREFIX;
        this.debugMode = debugMode;
    }


    /**
     * <h2>发送插件普通信息</h2>
     * @param messages 字符串
     */
    public void info(String... messages) {
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
    public void info(String messages, Object... objects) {
        if (messages == null) return;
        Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§a[INFO] §r" + toStrings.format(messages,objects) + "§r");
    }

    /**
     * <h2>发送插件警告信息</h2>
     * @param messages 字符串
     */
    public void warn(String... messages) {
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
    public void error(String... messages) {
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
    public void debug(String... messages) {
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
    public void debug(Throwable e) {
        if (e == null) return;
        if (isDebugMode())
            e.printStackTrace();
    }

    /**
     * <h2>同时发送插件debug信息和堆栈</h2>
     * @param e 堆栈
     * @param msg 字符串
     */
    public void debug(String msg, Throwable e) {
        if (msg == null || e == null) return;
        if (isDebugMode()) {
            debug(msg);
            debug(e);
        }
    }

    /**
     * <h2>发送插件 数据库 debug信息</h2>
     * @param messages 字符串
     */
    public void debugDataBase(String messages, Object... objects) {
        if (messages == null) return;
        if (isDebugMode()) {
            Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§6[DEBUG DB] §r" + toStrings.format(messages,objects) + "§r");
        }
    }

    /**
     * 抛出错误堆栈和错误信息
     * @param throwable 堆栈
     * @param messages 信息
     */
    public void crash(Throwable throwable, String... messages){
        for (String message : messages) {
            if (message == null) continue;
            Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§c[ERROR] §r" + message + "§r");
        }
        Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§c[ERROR] §r" + "抛出错误信息 ->" + "§r");
        Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§c[ERROR] §r" + throwable.getMessage() + "§r");
        Bukkit.getConsoleSender().sendMessage(INFO_PREFIX + "§c[ERROR] §r" + "抛出错误堆栈 ->" + "§r");
        throwable.printStackTrace();
    }

    public String getINFO_PREFIX() {
        return INFO_PREFIX;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

}
