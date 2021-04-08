package com.special.PengCheng.tools;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * 类描述：屏幕分辨率
 */

public class DensityUtil {


  private DensityUtil() {
    throw new UnsupportedOperationException("cannot be instantiated");
  }


  public static int dp2px(Context context, float dpVal) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            dpVal, context.getResources().getDisplayMetrics());
  }

  public static int sp2px(Context context, float spVal) {
    return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
            spVal, context.getResources().getDisplayMetrics());
  }

  public static float px2dp(Context context, float pxVal) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (pxVal / scale);
  }

  public static float px2sp(Context context, float pxVal) {
    return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
  }

  /**
   * 获取状态栏高度
   * */
  public static int getStatusBarHeight(Context context) {
    int result = 0;
    int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
    if (resourceId > 0) {
      result = context.getResources().getDimensionPixelSize(resourceId);
    }
    return result;
  }


  /**
   * 获取屏幕宽度
   * */
//  public static int getScreenWidth(Context context){
//    final Resources resources = context.getResources();
//    final DisplayMetrics metrics = resources.getDisplayMetrics();
//    return metrics.widthPixels;
//  }


  /**
   * 获取应用屏幕宽度
   */
  public static int getScreenWidth(Context context) {
    Display display = getDisplay(context);
    if (display == null) {
      return 0;
    }
    Point point = new Point();
    display.getSize(point);
    return point.x;
  }



  /**
   * 获取屏幕高度
   * */
  public static int getScreenHeight(Context context){
    final Resources resources =context.getResources();
    final DisplayMetrics metrics = resources.getDisplayMetrics();
    return metrics.heightPixels;
  }


  /**
   * 获取字体高度
   */
  public static float getTextHeight(Paint p) {
    Paint.FontMetrics fm = p.getFontMetrics();// 获取字体高度
    return (float) ((Math.ceil(fm.descent - fm.top) + 2) / 2);
  }

  public static int getTextWidth(String str, Paint paint) {
    Rect bounds = new Rect();
    paint.getTextBounds(str, 0, str.length(), bounds);
    return bounds.width();
  }

  public static int getTextWidth(String str, TextView tvText) {
    Rect bounds = new Rect();
    TextPaint paint = tvText.getPaint();
    paint.getTextBounds(str, 0, str.length(), bounds);
    return bounds.width();
  }



  /**
   * Get Display
   *
   * @param context Context for get WindowManager
   * @return Display
   */
  private static Display getDisplay(Context context) {
    WindowManager wm;
    if (context instanceof Activity) {
      Activity activity = (Activity) context;
      wm = activity.getWindowManager();
    } else {
      wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
    }
    if (wm != null) {
      return wm.getDefaultDisplay();
    }
    return null;
  }



  //----------------------------
  public static int pixelToDp(Context context, int pixel) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return pixel < 0 ? pixel : Math.round(pixel / displayMetrics.density);
  }
  public static int dpToPixel(Context context, int dp) {
    DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
    return dp < 0 ? dp : Math.round(dp * displayMetrics.density);
  }




  private static long lastTime;
  /**
   * 防快速点击
   *
   * @return true 可用
   */
  public static Boolean isFastClick() {
    if ((System.currentTimeMillis() - lastTime) < 1000) {
      lastTime = System.currentTimeMillis();
      return false;
    } else {
      return true;
    }
  }



  /**
   * 获取完整的域名
   *
   * @param text 的text文本
   */
  public static Boolean checkIfUrl(String text) {
    if(text==null||"".equals(text)){
      return false;
    }
    String reg = "^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
    return text.matches(reg);
  }





}
