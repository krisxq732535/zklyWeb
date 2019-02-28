package cn.stylefeng.guns.core.util;


import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DateUtils {

    public static final String PATTERN_YMDHMS = "yyyy-MM-dd HH:mm:ss";

    public static String addTime(String beginTime, int min){
        String[] s = beginTime.split(":");
        int hours = Integer.parseInt(s[0]);
        int mins = Integer.parseInt(s[1]);
        return DateUtils.getBeforeTime(hours, mins, min);
    }

    public static String getBeforeTime(int hours, int min, int num){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        Calendar c = new GregorianCalendar();
        Date date = new Date(2017, 1, 1, hours, min);
        //System.out.println("系统当前时间      ："+df.format(date));
        c.setTime(date);//设置参数时间
        c.add(Calendar.SECOND,(num*60));//把日期往后增加SECOND 秒.整数往后推,负数往前移动
        date=c.getTime(); //这个时间就是日期往后推一天的结果
        String str = df.format(date);
        //System.out.println("系统前30分钟时间："+str);
        return str;
    }

    public static String checkDateString(String dateStr){
        String[] s = dateStr.split("-");
        if (s.length == 3){
            if (s[0].length() < 4 && s[0].length() == 2){
                s[0] = "20"+s[0];
            }
            if (s[1].length() == 1){
                s[1] = "0"+s[1];
            }
            if (s[2].length() == 1){
                s[2] = "0"+s[2];
            }
        }
        return s[0]+"-"+s[1]+"-"+s[2];
    }
    public static String getNextMonth(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, 1);
        Date time = cal.getTime();
        return sdf.format(time);
    }

    // 取时间差(单位:分)
    public static int getDateOffset(String endDate, String firstDate) {
        int mins = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long endTime = sdf.parse(endDate).getTime();
            long beforeTime = sdf.parse(firstDate).getTime();
            long diff = endTime - beforeTime;
            mins = (int) (diff / (1000 * 60));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mins;
    }

    public static int getDateOffset(String endDate, String firstDate, String pattern) {
        int mins = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            long endTime = sdf.parse(endDate).getTime();
            long beforeTime = sdf.parse(firstDate).getTime();
            long diff = endTime - beforeTime;
            mins = (int) (diff / (1000 * 60));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mins;
    }


    // 取时间差(单位:分)
    public static int getDateOffHours(String endDate, String firstDate) {
        return getDateOffset(endDate, firstDate, "yyyy-MM-dd HH:mm:ss");
    }

    // 取时间差(单位:天)
    public static int getDateDayOffset(String endDate, String firstDate) {
        int mins = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            long endTime = sdf.parse(endDate).getTime();
            long beforeTime = sdf.parse(firstDate).getTime();
            long diff = endTime - beforeTime;
            mins = (int) (diff / (1000 * 60 * 60 * 24));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return mins;
    }

    public static String getTodayString() {
        return getTodayString(PATTERN_YMDHMS);
    }

    /**
     * 得到今天的日期
     *
     * @param pattern
     * @return
     */
    public static String getTodayString(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date today = new Date();
        String format = sdf.format(today);
        return format;
    }

    /**
     * 得到今天的特殊日期
     *
     * @param pattern
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getTodayString2(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date today = new Date();
        /*if (today.getHours() < 2) {
            long time = today.getTime() - 24 * 60 * 60 * 1000;
            today.setTime(time);
        }*/
        String format = sdf.format(today);
        return format;
    }

    /**
     * 得到明天的日期
     *
     * @param pattern
     * @return
     */
    public static String getTomorrowString(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date today = new Date();
        // 通过秒获取下一天日期
        long time = (today.getTime() / 1000) + 60 * 60 * 24;// 秒
        today.setTime(time * 1000);// 毫秒
        String tomorrow = sdf.format(today);
        return tomorrow;
    }

    /**
     * 得到某一天的前几天或者后几天
     *
     * @param pattern
     * @param days    正数表示后几天，负数表示后几天
     * @return
     */
    public static String getSomeDayString(String dateStr, String pattern, int days) {
        String tomorrow = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date date = sdf.parse(dateStr);
            // 通过秒获取下一天日期
            long time = (date.getTime() / 1000) + days * 60 * 60 * 24;// 秒
            date.setTime(time * 1000);// 毫秒
            tomorrow = sdf.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tomorrow;
    }

    /**
     * 得到昨天的的日期
     *
     * @param pattern
     * @return
     */
    public static String getYestodayString(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date today = new Date();
        // 通过秒获取前一天的日期
        long time = (today.getTime() / 1000) - 60 * 60 * 24;// 秒
        today.setTime(time * 1000);// 毫秒
        String tomorrow = sdf.format(today);
        return tomorrow;
    }

    /**
     * 得到昨天的的日期
     *
     * @param pattern
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String getYestodayString2(String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        int day = 1;
        Date today = new Date();
        int hour = today.getHours();
        if (hour < 2) {
            day = 2;
        }
        // 通过秒获取前一天的日期
        long time = (today.getTime() / 1000) - day * 60 * 60 * 24;// 秒
        today.setTime(time * 1000);// 毫秒
        String tomorrow = sdf.format(today);
        return tomorrow;
    }

    /**
     * 得到某一天的第二天
     *
     * @param pattern
     * @return
     */
    public static String getTomorrowStringForSomeDay(String pattern, String day) {
        String tomorrow = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date today = sdf.parse(day);
            // 通过秒获取下一天日期
            long time = (today.getTime() / 1000) + 60 * 60 * 24;// 秒
            today.setTime(time * 1000);// 毫秒
            tomorrow = sdf.format(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return tomorrow;
    }

    // 比较日期大小
    public static int compareDate(Date date1, Date date2) {
        if (date1.getTime() > date2.getTime()) {
            // date1 > date2
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            // date1 < date2
            return -1;
        } else {
            // date1 == date2
            return 0;
        }
    }

    /**
     * 判断时间是否在时间段内
     *
     * @param date 当前时间 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static boolean isInDate(Date date, String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        String currentTime = format.substring(11, format.length());
        if (currentTime.compareTo(startTime) >= 0 && currentTime.compareTo(endTime) <= 0) {
            return true;
        }
        return false;
    }

    /**
     * 从时间得到秒数 0-2:00:00特殊计算如果
     *
     * @return
     */
    public static int getSecondFromTime(String time1) {
        int second = 0;
        if (StringUtil2.isEmpty(time1)){
            return 0;
        }
        String time = time1/*.substring(time1.length() - 8)*/;
        if (time1.length()>8){
            time = time1.substring(time1.length() - 8);
        }
        if (StringUtil2.isNotEmpty(time) && !time.startsWith("null")) {
            String[] split = time.split(":");
            if (split.length > 2){
                second = Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60 + Integer.parseInt(split[2]);
            }else {
                second = Integer.parseInt(split[0]) * 3600 + Integer.parseInt(split[1]) * 60;
            }
            if (second < 7200) {
                second += 24 * 60 * 60;
            }
        }
        return second;
    }

    /**
     * 得到几天前零点的时间戳 负数表示几天后的
     *
     * @param days
     * @return
     */
    public static String get0StrTimeForDays(int days, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        long currentTime = new Date().getTime();
        long fourDaysBeforeTime = currentTime - (currentTime % (24 * 60 * 60 * 1000)) - days * 24 * 60 * 60 * 1000
                - 8 * 60 * 60 * 1000;
        String format = sdf.format(new Date(fourDaysBeforeTime));
        return format;
    }

    /**
     * 得到几天前零点的毫秒值 负数表示几天后的
     *
     * @param days
     * @return
     */
    public static long get0LongTimeForDays(int days, String pattern) {
        long currentTime = new Date().getTime();
        long fourDaysBeforeTime = currentTime - (currentTime % (24 * 60 * 60 * 1000)) - days * 24 * 60 * 60 * 1000
                - 8 * 60 * 60 * 1000;
        return fourDaysBeforeTime;
    }

    /**
     * 得到某个时间段中每天的日期
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String[] getEveryDateStrFromDateSpan(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<String> dates = new ArrayList<>();
        try {
            Date startD = sdf.parse(startDate);
            Date endD = sdf.parse(endDate);
            long startTime = startD.getTime();
            long endTime = endD.getTime();
            long tempTime = startTime;
            String tempDateStr = "";
            while (tempTime <= endTime) {
                Date tempDate = new Date(tempTime);
                tempDateStr = sdf.format(tempDate).substring(5);
                dates.add(tempDateStr);
                tempTime += 24 * 60 * 60 * 1000;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] a = new String[dates.size()];
        return dates.toArray(a);
    }

    /**
     * 得到某一时间段的月份信息
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String[] getEveryMonthStrFromDateSpan(String startDate, String endDate) {
        List<String> dates = new ArrayList<>();
        try {
            Date d1 = new SimpleDateFormat("yyyy-MM").parse(startDate); //定义起始日期

            Date d2 = new SimpleDateFormat("yyyy-MM").parse(endDate);//定义结束日期

            Calendar dd = Calendar.getInstance();//定义日期实例

            dd.setTime(d1);//设置日期起始时间

            while (dd.getTime().compareTo(d2) <= 0) {//判断是否到结束日期

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

                String str = sdf.format(dd.getTime());

                dd.add(Calendar.MONTH, 1);//进行当前日期月份加1

                dates.add(str);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String[] a = new String[dates.size()];
        return dates.toArray(a);
    }



    /**
     * 根据时间段得到年份信息
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String[] getYearSpanFromDateSpan(String startDate, String endDate) {
        int startYear = Integer.parseInt(startDate.substring(0, 4));
        int endYear = Integer.parseInt(endDate.substring(0, 4));
        String[] yearStrs = new String[endYear - startYear + 1];
        int j = 0;
        for (int i = startYear; i <= endYear; i++) {
            yearStrs[j] = i + "";
            j++;
        }
        return yearStrs;
    }

    /**
     * 得到某个时间点的前几个小时
     *
     * @param beforeTime
     * @param hours
     * @return
     */
    public static String getBeforeTime(String beforeTime, double hours) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String year = "2017-01-02 ";
        String time = year + beforeTime;
        Date parse = null;
        try {
            parse = sdf.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long l = (long) (parse.getTime() - hours * 60 * 60 * 1000);
        String format = sdf.format(new Date(l));
        return format.substring(11);
    }

    public static Map<String, Object> getToDayWeek(){
        Map<String, Object> map = new HashMap<>();
        SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        System.out.println("现在时间："+simdf.format(cal.getTime()));
//分别获取年、月、日
        System.out.println("年："+cal.get(cal.YEAR));
        System.out.println("月："+(cal.get(cal.MONTH)+1));//老外把一月份整成了0，翻译成中国月份要加1
        System.out.println("日："+cal.get(cal.DATE));

        cal.set(cal.DAY_OF_WEEK, cal.MONDAY);
        String weekhand = simdf.format(cal.getTime());
        map.put("monday", weekhand);
        System.out.println("当前时间所在周周一日期："+weekhand);
//cal.set(cal.DAY_OF_WEEK, cal.SUNDAY);这个不符合中国人的时间观，老外把上周周日定为一周的开始。

        cal.set(Calendar.DATE, cal.get(cal.DATE) + 6);
        String weeklast = simdf.format(cal.getTime());
        map.put("sunday", weeklast);
        System.out.println("当前时间所在周周日日期："+weeklast);

        return map;
    }

    public static String thisSeasonEnd() {
        Calendar localTime = Calendar.getInstance();
        String dateString = "";
        int x = localTime.get(Calendar.YEAR);
        int y = localTime.get(Calendar.MONTH) + 1;
        if (y >= 1 && y <= 3) {
            dateString = x + "-" + "03" + "-" + "31";
        }
        if (y >= 4 && y <= 6) {
            dateString = x + "-" + "06" + "-" + "30";
        }
        if (y >= 7 && y <= 9) {
            dateString = x + "-" + "09" + "-" + "30";
        }
        if (y >= 10 && y <= 12) {
            dateString = x + "-" + "12" + "-" + "31";
        }
        return dateString;
    }

    public static String getPastDate(int past) {//获取当天以前的日期
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        //Log.e(null, result);
        return result;
    }

    public static String getPercentage(int num1, int num2){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(1);
        String result = numberFormat.format((float) num1 / (float) num2 * 100);
        return result + "%";
    }

    public static String getPastDate1(int past, String dateString) {//获取指定日期以前的日期
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime(format.parse(dateString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();

        String result = format.format(today);
        //Log.e(null, result);
        return result;
    }

    public static int compareTo(String str1, String str2) {
        int res=str1.compareTo(str2);
        if(res>0)
            System.out.println("str1>str2");
        else if(res==0)
            System.out.println("str1=str2");
        else
            System.out.println("str1<str2");
        return res;
    }

    public static void main(String[] args) {
        System.err.println(getTodayString());
    }

    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
}
