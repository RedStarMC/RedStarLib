package top.redstarmc.plugin.redstarlib.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigMapBuilder<Key, Value> {
    private final Map<Key, Value> map = new LinkedHashMap<>();

    public static <Key, Value> ConfigMapBuilder<Key, Value> of(Class<Key> keyType, Class<Value> valueType){
        return new ConfigMapBuilder<>();
    }

    public ConfigMapBuilder<Key, Value> set(Key key, Value value){
        map.put(key,value);
        return this;
    }

    public Map<Key, Value> toMap(){
        return new LinkedHashMap<>(map);
    }
    public Map<Key, Value> build() {
        return map;
    }
}
