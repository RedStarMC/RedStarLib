package top.redstarmc.plugin.redstarlib.database;

import cc.carm.lib.easysql.api.SQLAction;
import cc.carm.lib.easysql.api.SQLQuery;
import cc.carm.lib.easysql.api.function.SQLDebugHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.redstarmc.plugin.redstarlib.manager.LoggerManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <h1>EasySQL 的 DebugHandler 预设模块</h1>
 * 重写了该模块，以便通过插件输出。
 * 可直接使用本类，或继承后使用
 * {@link cc.carm.lib.easysql.api.SQLManager#setDebugHandler(SQLDebugHandler)}
 *
 * 将 "SQLDebugHandler" 替换为 "CustomDebugHandler"
 */
public abstract class CustomDebugHandler implements SQLDebugHandler {

    public CustomDebugHandler(LoggerManager loggerManager){
        this.loggerManager = loggerManager;
    }

    LoggerManager loggerManager;

    @Override
    public void beforeExecute(@NotNull SQLAction<?> action, @NotNull List<@Nullable Object[]> params) {
        loggerManager.debugDataBase("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        loggerManager.debugDataBase("┣# ActionUUID: {}", action.getActionUUID());
        loggerManager.debugDataBase("┣# ActionType: " + action.getClass().getSimpleName());
        if (action.getSQLContents().size() == 1) {
            loggerManager.debugDataBase("┣# SQLContent: " + action.getSQLContents().get(0));
        } else {
            loggerManager.debugDataBase("┣# SQLContents: ");
            int i = 0;
            for (String sqlContent : action.getSQLContents()) {
                loggerManager.debugDataBase("┃ - [{}] {}", ++i, sqlContent);
            }
        }
        if (params.size() == 1) {
            Object[] param = params.get(0);
            if (param != null) {
                loggerManager.debugDataBase("┣# SQLParam: " + parseParams(param));
            }
        } else if (params.size() > 1) {
            loggerManager.debugDataBase("┣# SQLParams: ");
            int i = 0;
            for (Object[] param : params) {
                loggerManager.debugDataBase("┃ - [{}] {}", ++i, parseParams(param));
            }
        }
        loggerManager.debugDataBase("┣# CreateTime: " + action.getCreateTime(TimeUnit.MILLISECONDS));
        loggerManager.debugDataBase("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    @Override
    public void afterQuery(@NotNull SQLQuery query, long executeNanoTime, long closeNanoTime) {
        loggerManager.debugDataBase("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        loggerManager.debugDataBase("┣# ActionUUID: {}", query.getAction().getActionUUID());
        loggerManager.debugDataBase("┣# SQLContent: " + query.getSQLContent());
        loggerManager.debugDataBase("┣# CloseTime: {}  (cost {} ms)",
                TimeUnit.NANOSECONDS.toMillis(closeNanoTime),
                ((double) (closeNanoTime - executeNanoTime) / 1000000)
        );
        loggerManager.debugDataBase("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

}
