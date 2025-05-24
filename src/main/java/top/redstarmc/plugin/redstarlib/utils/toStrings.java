package top.redstarmc.plugin.redstarlib.utils;

import org.slf4j.helpers.MessageFormatter;

public class toStrings {

    /**
     * 字符串按照 slf 方式进行格式化返回
     * @return 格式化后的字符串
     */
    public static String format(String format, Object... params) {
        return MessageFormatter.arrayFormat(format, params).getMessage();
    }


}
