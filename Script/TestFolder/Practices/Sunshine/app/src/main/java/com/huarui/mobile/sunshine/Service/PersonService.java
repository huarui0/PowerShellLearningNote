package com.huarui.mobile.sunshine.Service;

/**
 * Created by wanglai on 1/4/2017.
 */

import java.util.List;
import java.util.Map;

/**
 * 定义好增删改查接口
 * @author xukunhui
 *
 */
public interface PersonService {

    public boolean addPersion(Object[] params);

    public boolean deletePerson(Object[] params);

    public boolean updatePerson(Object[] params);

    //使用 Map<String, String> 做一个封装，比如说查询数据库的时候返回的单条记录
    public Map<String, String> viewPerson(String[] selectionArgs);

    //使用 List<Map<String, String>> 做一个封装，比如说查询数据库的时候返回的多条记录
    public List<Map<String, String>> listPersonMaps(String[] selectionArgs);
}