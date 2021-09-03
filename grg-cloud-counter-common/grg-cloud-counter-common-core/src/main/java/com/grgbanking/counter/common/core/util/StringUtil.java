package com.grgbanking.counter.common.core.util;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    /**
     * 获取字符串长度，若有中文，每个中文字符计为2位
     *
     * @param str 待计算字符串
     * @return 字符串长度
     */
    public static int length(String str, String isoCode) throws Exception {
        byte[] strBytes = null;
        strBytes = str.getBytes(isoCode);
        return strBytes.length;
    }

    /**
     * 去掉空格，回车，水平制表符，换行
     *
     * @return
     */
    public static String replaceBlank(String str) throws Exception {
        String dest = "";
        if (str != null) {
            Pattern p = Pattern.compile("\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }

    /**
     * 功能描述：截取字符串str中指定字符strStart、startEnd之间字符串
     *
     * @param
     * @return
     *
     */
    public static String subString(String str, String strStart, String strEnd) {
        /** 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.indexOf(strEnd);

        /** index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            strStartIndex = 0;
        }
        if (strEndIndex < 0) {
            strEndIndex = str.length();
        }
        /** 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }

    /**
     * 功能描述：将map通过指定的分隔符转为String
     *
     * @param
     * @return
     *
     */
    public static String map2StringBySeparator(String sep, Map<Integer, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            stringBuffer.append(entry.getValue());
            stringBuffer.append(sep);
        }
        stringBuffer = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    /**
     * 功能描述：输出指定treeMap中指定数量的字符串集合
     *
     * @param
     * @return
     *
     */
    public static String map2StringBySeparator(String sep, int count, TreeMap<Integer, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        int out = 0;
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (out != count) {
                stringBuffer.append(entry.getValue());
                stringBuffer.append(sep);
                out++;
            } else {
                break;
            }

        }
        stringBuffer = stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    /**
     * 功能描述：拼接字符串
     *
     * @param
     * @return
     *
     */
    public static synchronized String getJointString(String separator, String... str) {
        StringBuffer sb = new StringBuffer();
        for (String src : str) {
            sb.append(src);
            sb.append(separator);
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static int getFromIndex(String str, String modelStr, Integer count) {
        //对子字符串进行匹配
        Matcher slashMatcher = Pattern.compile(modelStr).matcher(str);
        int index = 0;
        //matcher.find();尝试查找与该模式匹配的输入序列的下一个子序列
        while (slashMatcher.find()) {
            index++;
            //当modelStr字符第count次出现的位置
            if (index == count) {
                break;
            }
        }
        return slashMatcher.start();
    }

    public static String subString(String orignal, int count) {
        if (orignal != null && !"".equals(orignal))
            if (isChineseChar(orignal)) {
                if (orignal.length() > count) {
                    return orignal.substring(0, count / 2);
                } else {
                    return orignal;
                }

            } else {
                if (orignal.length() > count) {
                    return orignal.substring(0, count);
                } else {
                    return orignal;
                }
            }
        else {
            return orignal;
        }
    }

    /**
     * 检查字符串中是否是中文
     *
     * @param name 需要检查的字符串
     * @return true：是; false：不是
     */

    public static Boolean isChineseChar(String name) {
        Boolean flag = false;
        try {
            int n = Integer.parseInt(name);
            flag = true;
        } catch (Exception e) {
            char[] cs = name.toCharArray();
            for (char c : cs) {
                // 判断是否为汉字。0-126之间的字符都是单字节..
                if ((int) c <= 0 || (int) c >= 126) {
                    flag = true;
                    break;
                }
            }
        }
        return flag;
    }

    /**
     * 将秒转成 天、时、分、秒
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static String getTimeString(Date startTime, Date endTime) {
        if (startTime != null && endTime != null) {
            // 计算开始时间和结束时间
            long start = startTime.getTime();
            long end = endTime.getTime();
            long bet = (end - start) / 1000;// 将毫秒转换成秒
            return getTimeString(bet);
        }
        return "";
    }

    /**
     * 将秒转成 天、时、分、秒
     *
     * @param seconds
     * @return
     */
    public static String getTimeString(long seconds) {
        long day = seconds / (24 * 3600);// 获得日
        long level1 = seconds % (24 * 3600);// 得到天数后剩下的时间
        long hour = level1 / 3600;
        long level2 = level1 % 3600;
        long minute = level2 / 60;
        long second = level2 % 60;
        StringBuilder sb = new StringBuilder();
        if (day > 0) {
            sb.append(day).append("天");
        }
        if (hour > 0) {
            sb.append(hour).append("时");
        }
        if (minute > 0) {
            sb.append(minute).append("分");
        }
        if (second > 0) {
            sb.append(second).append("秒");
        }
        return sb.toString();
    }


    /**
     * 大写加下划线转为小写驼峰写法
     * @param input
     * @return
     */
    public static String slashToFirstLetterUpper(String input) {
        String spliter = "_";
        StringBuffer output = new StringBuffer();
        String[] words = input.toLowerCase().split(spliter);
        for (int i = 0; i < words.length; i++) {
            if (i != 0) {
                output.append(fistLetterToUpper(words[i]));
            } else {
                output.append(words[i]);
            }
        }
        return output.toString();
    }

    private static String fistLetterToUpper(String input) {
        if (input == null)
            return "";
        if (input.length() <= 0)
            return "";

        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }

    /**
     * 把首字符转为大写，其余不变
     * @param str
     * @return
     */
    public static String transFirstToUpperCase(String str){
        char[] c = new char[1];
        c[0] = str.charAt(0);
        String first = new String(c);
        return str.replaceFirst(first, first.toUpperCase());
    }

}
