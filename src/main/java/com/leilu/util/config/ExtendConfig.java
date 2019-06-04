package com.leilu.util.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 对于数据库模型的补充配置
 * Created by leilu on 2018/3/29.
 */
public class ExtendConfig extends HashMap<String,Map<String,Object>>{

    public Map<String,Object> getByTableName(String tableName){
        return super.get(tableName);
    }

    public ExtendConfig putTableExtend(String tableName,Map<String,Object> value){
        super.put(tableName,value);
        return this;
    }
}
