package com.gxa.until;

import java.util.HashMap;
import java.util.Map;

/**
 * 将给前端返回的数据进行封装
 * @author bill
 * @date 2022/5/6 15:27
 */
public class JsonResult {
    /**
     * 1. 声明容器
     */
    private Map<String,Object> resultMap = new HashMap<>();

    public void setCode(String code){
        resultMap.put("code",code);
    }

    public void setMsg(String msg){
        resultMap.put("msg",msg);
    }

    public void setData(Object data){
        resultMap.put("data",data);
    }

    public void set(String name,Object value){
        resultMap.put(name,value);
    }

    public Map<String,Object> getMap(){
        return resultMap;
    }

}
