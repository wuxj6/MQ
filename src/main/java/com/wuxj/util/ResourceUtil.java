package com.wuxj.util;

import java.util.ResourceBundle;

/**
 * @author wuxj6
 * @version 1.0.0
 * @ClassName ResourceUtil.java
 * @Description TODO
 * @createTime 2022/1/10 9:39
 */
public class ResourceUtil {
    private static final ResourceBundle resourceBundle;

    static {
        resourceBundle = ResourceBundle.getBundle("config");
    }

    public static String getKey(String key) {
        return resourceBundle.getString(key);
    }
}
