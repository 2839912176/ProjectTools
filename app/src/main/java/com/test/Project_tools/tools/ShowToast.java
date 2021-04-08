package com.special.PengCheng.tools;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by dyh on 2018/12/10.
 *  小米
 */

public class ShowToast {

    public Context mContext;

    public static void ShowShorttoast(Context context, String text){
        Toast mToast = null;
        if (mToast == null) {
            mToast= Toast.makeText(context,null, Toast.LENGTH_SHORT);
            mToast.setText(text);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }
        mToast.show();
    }

    public static void ShowLongtoast(Context context, String text){
        Toast mToast = null;
        if (mToast == null) {
            mToast= Toast.makeText(context,null, Toast.LENGTH_LONG);
            mToast.setText(text);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }
        mToast.show();
    }
    public static void ShowShorttoast(Context context, int textid){
        Toast mToast = null;
        String text = context.getResources().getString(textid);
        if (mToast == null) {
            mToast= Toast.makeText(context,null, Toast.LENGTH_SHORT);
            mToast.setText(text);
        } else {
            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
            mToast.setText(text);
        }
        mToast.show();
    }

//    public static void ShowLongtoast(Context context,String text){
//        Toast mToast = null;
//        if (mToast == null) {
//            mToast=Toast.makeText(context,null,Toast.LENGTH_LONG);
//            mToast.setText(text);
//        } else {
//            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
//            mToast.setText(text);
//        }
//        mToast.show();
//    }
//
//    public static void ShowLongtoast(Context context,int textid){
//        Toast mToast = null;
//        String text = context.getResources().getString(textid);
//        if (mToast == null) {
//            mToast=Toast.makeText(context,null,Toast.LENGTH_LONG);
//            mToast.setText(text);
//        } else {
//            //如果当前Toast没有消失， 直接显示内容，不需要重新设置
//            mToast.setText(text);
//        }
//        mToast.show();
//    }



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
     * 防快速点击
     *
     * @return true 可用
     */
    public static Boolean isFastClick2() {
        if ((System.currentTimeMillis() - lastTime) < 2000) {
            lastTime = System.currentTimeMillis();
            return false;
        } else {
            return true;
        }
    }






    /**
     * 设置edittext只能输入小数点后两位
     */
    public static void afterDotTwo(final EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int MAX_LENGTH = 9;

                if (s.toString().contains(".")) {

                    if (s.toString().indexOf(".") > MAX_LENGTH) {

                        s = s.toString().subSequence(0, MAX_LENGTH) + s.toString().substring(s.toString().indexOf("."));

                        editText.setText(s);

                        editText.setSelection(MAX_LENGTH);

                    }

                } else {

                    if (s.toString().length() > MAX_LENGTH) {

                        s = s.toString().subSequence(0, MAX_LENGTH);

                        editText.setText(s);

                        editText.setSelection(MAX_LENGTH);

                    }

                }

                // 判断小数点后只能输入两位

                if (s.toString().contains(".")) {

                    if (s.length() - 1 - s.toString().indexOf(".") > 2) {

                        s = s.toString().subSequence(0,

                                s.toString().indexOf(".") + 3);

                        editText.setText(s);

                        editText.setSelection(s.length());

                    }

                }

                //如果第一个数字为0，第二个不为点，就不允许输入
                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {

                    if (!s.toString().substring(1, 2).equals(".")) {

                        editText.setText(s.subSequence(0, 1));

                        editText.setSelection(1);

                        return;
                    }
                }

            }

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override

            public void afterTextChanged(Editable s) {

                if (!TextUtils.isEmpty(editText.getText()) && !editText.getText().toString().trim().equals("")) {

                    if (editText.getText().toString().trim().substring(0, 1).equals(".")) {

                        editText.setText("0" + editText.getText().toString().trim());

                        editText.setSelection(2);

                        editText.requestFocus();

                    }

                }

            }

        });

    }





    /**
     * 设置edittext只能输入小数点后三位
     */
    public static void afterDotThree(final EditText editText) {

        editText.addTextChangedListener(new TextWatcher() {

            @Override

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                int MAX_LENGTH = 9;

                if (s.toString().contains(".")) {

                    if (s.toString().indexOf(".") > MAX_LENGTH) {

                        s = s.toString().subSequence(0, MAX_LENGTH) + s.toString().substring(s.toString().indexOf("."));

                        editText.setText(s);

                        editText.setSelection(MAX_LENGTH);

                    }

                } else {

                    if (s.toString().length() > MAX_LENGTH) {

                        s = s.toString().subSequence(0, MAX_LENGTH);

                        editText.setText(s);

                        editText.setSelection(MAX_LENGTH);

                    }

                }

                // 判断小数点后只能输入两位

                if (s.toString().contains(".")) {

                    if (s.length() - 1 - s.toString().indexOf(".") > 3) {

                        s = s.toString().subSequence(0,

                                s.toString().indexOf(".") + 4);

                        editText.setText(s);

                        editText.setSelection(s.length());

                    }

                }

                //如果第一个数字为0，第二个不为点，就不允许输入
                if (s.toString().startsWith("0") && s.toString().trim().length() > 1) {

                    if (!s.toString().substring(1, 2).equals(".")) {

                        editText.setText(s.subSequence(0, 1));

                        editText.setSelection(1);

                        return;
                    }
                }

            }

            @Override

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override

            public void afterTextChanged(Editable s) {

                if (!TextUtils.isEmpty(editText.getText()) && !editText.getText().toString().trim().equals("")) {

                    if (editText.getText().toString().trim().substring(0, 1).equals(".")) {

                        editText.setText("0" + editText.getText().toString().trim());

                        editText.setSelection(2);

                        editText.requestFocus();

                    }

                }

            }

        });

    }




    /**
     * 取APP版本号
     *
     * @return
     */
    public static int getAppVersionCode(Context context) {
        try {
            PackageManager mPackageManager = context.getPackageManager();
            PackageInfo _info = mPackageManager.getPackageInfo(context.getPackageName(), 0);
            return _info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            return 0;
        }
    }




}
