package com.zxy.tobean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 文件描述：使用反射解析服务器返回数据
 * 作者：Created by jackyZuo on 2017/11/22.
 */

public class Json2BeanUtil {

    private static class Json2BeanUtilImpl {
        public static Json2BeanUtil instance = new Json2BeanUtil();
    }

    public static Json2BeanUtil getInstance(){
        return Json2BeanUtilImpl.instance;
    }

    public Object tobean(String json, Class clazz) {
        log("json == "+ json);
        if (clazz == null) {
            return null;
        }
        // 获取实体类的所有属性，返回Field数组
        Field[] fields = clazz.getFields();
        Object obj = null;
        try {
            obj = clazz.newInstance();
            JSONObject value = new JSONObject(json);
            for (Field field : fields) {
                String fieldName = field.getName();
                if (!value.has(fieldName)){
                    log("字段 "+fieldName+" 没有值");
                    continue;
                }
                field.setAccessible(true);
                String name = field.getType().getName();
                log(name + " : "+fieldName);
                switch (name){
                    case "java.lang.String":
                        //字段需要public 修饰
                        field.set(obj,value.getString(fieldName));
                        break;
                    case "int":
                    case "java.lang.Integer":
                        field.setInt(obj,value.getInt(fieldName));
                        break;
                    case "boolean":
                    case "java.lang.Boolean":
                        field.setBoolean(obj,value.getBoolean(fieldName));
                        break;
                    case "long":
                    case "java.lang.Long":
                        field.setLong(obj,value.getLong(fieldName));
                        break;
                    case "double":
                    case "java.lang.Double":
                        field.setDouble(obj,value.getDouble(fieldName));
                        break;
                    case "java.util.ArrayList":
                        JSONArray array = value.getJSONArray(fieldName);//数据
                        Type type = field.getGenericType();//取得field的type

                        // 如果不为空并且是泛型参数的类型
                        if (type != null && type instanceof ParameterizedType) {
                            ParameterizedType pt = (ParameterizedType) type;
                            Class typeCls = (Class) pt.getActualTypeArguments()[0];
                            log("ArrayList typeCls  == "+typeCls);
                            ArrayList list = new ArrayList<>();
                            for (int i = 0; i < array.length(); i++) {
                                Object objt = tobean(array.getString(i),typeCls);
                                list.add(objt);
                            }
                            field.set(obj,list);
                        }
                        break;
                    //TODO 需要的继续加
                    default:
                        //字段是个对象，递归
                        if (field.getType() instanceof Object){
                            Object objfield = tobean(value.getString(fieldName), field.getType());
                            field.set(obj,objfield);
                        }
                }
            }
        } catch (JSONException e) {
            logerror(e.toString());
        } catch (InstantiationException e) {
            logerror(e.toString());
        } catch (IllegalAccessException e) {
            logerror(e.toString());
        }
        return obj;
    }



    private static void log(String msg) {
        logger(Level.WARNING,msg);
    }

    private static void logerror(String msg){
        logger(Level.OFF,msg);
    }

    private static void logger(Level level,String msg){
        Logger.getLogger("jackyZuo").log(level,msg);
    }
}
