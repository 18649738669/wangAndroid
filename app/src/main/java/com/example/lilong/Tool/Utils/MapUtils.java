package com.example.lilong.Tool.Utils;

import android.text.TextUtils;

import java.util.Map;

/**
 * Created by long on 2018-4-12.
 */
public class MapUtils {

    /**
     * 向Map添加参数,key和value不能为空,如果为空则自动略过
     *
     * @param map
     * @param key
     * @param value
     */
    public static void add(Map map, String key, Object value) {

        String strValue = CastUtils.castString(value);

        if (TextUtils.isEmpty(strValue)) {
            return;
        }

        if (TextUtils.isEmpty(key)) {
            return;
        }

        map.put(key, strValue);
    }


}
