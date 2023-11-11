package com.system.Services;

import java.util.Map;

public interface JsonSerializable<T> {

    Map<Long, T> loadFromJson();

    void saveToJson(Map<Long, T> map);
}
