package top.redstarmc.plugin.redstarlib.manager;

import cc.carm.lib.easysql.api.SQLManager;
import cc.carm.lib.easysql.api.function.SQLDebugHandler;
import cc.carm.lib.easysql.hikari.HikariConfig;
import cc.carm.lib.easysql.hikari.HikariDataSource;
import cc.carm.lib.easysql.manager.SQLManagerImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Properties;

public abstract class DataBaseManager<T extends SQLManager,U extends SQLDebugHandler> {

    SQLManager sqlManager;

    public DataBaseManager(@NotNull Properties properties){
        create(new HikariDataSource(new HikariConfig(properties)));
        sqlManager.setDebugMode(false);
    }

    public DataBaseManager(@NotNull HikariConfig config){
        create(new HikariDataSource(config));
        sqlManager.setDebugMode(false);
    }

    public DataBaseManager(boolean debugMode, @NotNull Properties properties){
        create(new HikariDataSource(new HikariConfig(properties)));
        sqlManager.setDebugMode(debugMode);
    }


    public DataBaseManager(boolean debugMode,@NotNull HikariConfig config){
        create(new HikariDataSource(config));
        sqlManager.setDebugMode(debugMode);
    }

    public DataBaseManager(boolean debugMode, U debugHandler, @NotNull Properties properties){
        create(new HikariDataSource(new HikariConfig(properties)));
        sqlManager.setDebugMode(debugMode);
        sqlManager.setDebugHandler(debugHandler);
    }

    public DataBaseManager(boolean debugMode, U debugHandler,@NotNull HikariConfig config){
        create(new HikariDataSource(config));
        sqlManager.setDebugMode(debugMode);
        sqlManager.setDebugHandler(debugHandler);
    }

    public DataBaseManager(boolean debugMode, U debugHandler, HikariDataSource config){
        sqlManager = new SQLManagerImpl(config);
        sqlManager.setDebugMode(debugMode);
        sqlManager.setDebugHandler(debugHandler);
    }

    private void create(@NotNull HikariDataSource hikariDataSource){
        sqlManager = new SQLManagerImpl(hikariDataSource);
    }

    public SQLManager getSqlManager() {
        return sqlManager;
    }

    public SQLManager getSM() {
        return sqlManager;
    }
}
