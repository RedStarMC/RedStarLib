package top.redstarmc.plugin.redstarlib.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigMapBuilder<X,Y> {
    private final Map<X, Y> map = new LinkedHashMap<>();

    public static <X, Y> ConfigMapBuilder<X, Y> of(Class<X> keyType, Class<Y> valueType){
        return new ConfigMapBuilder<>();
    }

    public ConfigMapBuilder<X, Y> set(X key, Y value){
        map.put(key,value);
        return this;
    }

    public Map<X, Y> toMap(){
        return new LinkedHashMap<>(map);
    }
    public Map<X, Y> build() {
        return map;
    }
}
