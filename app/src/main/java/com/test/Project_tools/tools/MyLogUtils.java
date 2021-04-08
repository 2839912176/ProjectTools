package com.special.PengCheng.tools;

import android.util.Log;

import java.text.SimpleDateFormat;


/**
 * 将信息记录到控制台的LogCat，显示调用方法及所在的文件、行号，方便开发时调试查错。
 * 注意：在Debug状态下开启，在Release状态下关闭，敏感信息不宜打印，否则被非法之徒抓取贻害无穷。
 *
 *
 * MyLogUtils.debug("TAG", "---------");
 * System.out.println("----enterpriseId: " + enterpriseId );
 *
 */
public final class MyLogUtils {
    private static final int MIN_STACK_OFFSET = 3;// starts at this class after two native calls
    private static final int MAX_STACK_TRACE_SIZE = 131071; //128 KB - 1
    private static final int METHOD_COUNT = 2; // show method count in trace
    private static boolean isDebug = true;// 是否调试模式
    private static String debugTag = "TAG";// LogCat的标记


    public static boolean isDebug() {
        return isDebug;
    }


    /**
     * Debug.
     *
     * @param message the message
     */
    public static void debug(String message) {
        debug("", message);
    }


    /**
     * 记录“debug”级别的信息
     *
     * @param tag the tag
     * @param msg the msg
     *
     *   使用：
     *  MyLogUtils.debug("getNewVersion", jsonObject.toString());
     *
     */
    public static void debug(String tag, String msg) {
        if (isDebug) {
            tag = debugTag + ((tag == null || tag.trim().length() == 0) ? "" : "-") + tag;
            msg = msg + getTraceElement();
            Log.d(tag, msg);
        }
    }


    /**
     * 可显示调用方法所在的文件行号，在AndroidStudio的logcat处可点击定位。
     * 此方法参考：https://github.com/orhanobut/logger
     */
    private static String getTraceElement() {
        try {
            int methodCount = METHOD_COUNT;
            StackTraceElement[] trace = Thread.currentThread().getStackTrace();
            int stackOffset = _getStackOffset(trace);

            //corresponding method count with the current stack may exceeds the stack trace. Trims the count
            if (methodCount + stackOffset > trace.length) {
                methodCount = trace.length - stackOffset - 1;
            }

            String level = "    ";
            StringBuilder builder = new StringBuilder();
            for (int i = methodCount; i > 0; i--) {
                int stackIndex = i + stackOffset;
                if (stackIndex >= trace.length) {
                    continue;
                }
                builder.append("\n")
                        .append(level)
                        .append(_getSimpleClassName(trace[stackIndex].getClassName()))
                        .append(".")
                        .append(trace[stackIndex].getMethodName())
                        .append(" ")
                        .append("(")
                        .append(trace[stackIndex].getFileName())
                        .append(":")
                        .append(trace[stackIndex].getLineNumber())
                        .append(")");
                level += "    ";
            }
            return builder.toString();
        } catch (Exception e) {
            Log.w(debugTag, e);
            return "";
        }
    }

    /**
     * Determines the starting index of the stack trace, after method calls made by this class.
     *
     * @param trace the stack trace
     * @return the stack offset
     */
    private static int _getStackOffset(StackTraceElement[] trace) {
        for (int i = MIN_STACK_OFFSET; i < trace.length; i++) {
            StackTraceElement e = trace[i];
            String name = e.getClassName();
            if (!name.equals(MyLogUtils.class.getName())) {
                return --i;
            }
        }
        return -1;
    }

    private static String _getSimpleClassName(String name) {
        int lastIndex = name.lastIndexOf(".");
        return name.substring(lastIndex + 1);
    }


    /**
     * 截断输出日志
     *
     * @param msg
     */
    public static void e(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }



    //========================= time ===========================
    /**
     *  java
     *  字符串型的   时间戳 转化成 时间
     * @param strTime : 时间戳   到【秒】
     * @param formatType  ： yyyy-MM-dd 、  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String transForDate1(String strTime, String formatType){
        long ms = Long.parseLong(strTime);
        String str = "";
        long msl=(long)ms*1000;
        SimpleDateFormat sdf=new SimpleDateFormat(formatType);  // yyyy-MM-dd HH:mm:ss

        try {
            str=sdf.format(msl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }









}
