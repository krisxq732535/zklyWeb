package cn.stylefeng.guns.core.util;

import java.util.UUID;

public class StringUtil2 {


    public static String getUserWorkTableNameFromDate(String dateStr) {
        String tableName = "work_user_work";
        String yyyy = dateStr.substring(0, 4);
        String MM = dateStr.substring(5, 7);
        //String DD = dateStr.substring(8, 10);
        return tableName + "_" + yyyy + "_" + MM;//+ "_" + DD;
    }

    public static String getWorkFileTableNameFromDate(String dateStr) {
        String tableName = "ff_work_file";
        String yyyy = dateStr.substring(0, 4);
        String MM = dateStr.substring(5, 7);
        return tableName + "_" + yyyy + "_" + MM;
    }

    public static boolean isEmpty(String str) {
        if ("".equals(str) || str == null) {

            return true;
        } else {
            return false;
        }

    }

    public static boolean isNotEmpty(String str) {
        if (!"".equals(str) && str != null) {

            return true;
        } else {
            return false;
        }
    }

    /**
     * 得到小寫的UUID
     *
     * @return
     */
    public static String getLowUUID() {
        String s = UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
        return s;
    }


}
