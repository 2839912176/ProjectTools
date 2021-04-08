package com.special.PengCheng.tools;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.special.PengCheng.R;

import androidx.appcompat.app.AlertDialog;


/**
 * 权限提示框
 */

public class PermissionDialog implements View.OnClickListener {

    private AlertDialog mDialog;
    private Activity mActivity;

    public PermissionDialog(Activity activity){
        this.mActivity = activity;
        mDialog = new AlertDialog.Builder(activity).create();
    }

    public static PermissionDialog create(Activity activity){
        return new PermissionDialog(activity);
    }

    public void beginPermissionDialog(){
//        mDialog = new AlertDialog.Builder(mActivity).create();
        mDialog.show();
        final Window window = mDialog.getWindow();
        if (window != null) {
            window.setContentView(R.layout.dialog_hint);
            window.setGravity(Gravity.CENTER);

//            window.setBackgroundDrawable(new ShapeDrawable(R.drawable.white_solid_radius));
            //设置属性
            final WindowManager.LayoutParams params = window.getAttributes();
            int screenWidth = DensityUtil.getScreenWidth(mActivity);
            params.width = screenWidth/7*5;
            params.gravity = Gravity.CENTER;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.5f;
            window.setAttributes(params);
            //window.setBackgroundDrawableResource(R.drawable.draw_white_6);
            window.setBackgroundDrawableResource(R.color.colorWhite);
            window.findViewById(R.id.tv_dialog_hint_cancel).setOnClickListener(this);
            window.findViewById(R.id.tv_dialog_hint_sure).setOnClickListener(this);
//            window.findViewById(R.id.camera_dialog_photo).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_dialog_hint_cancel:
                mDialog.cancel();
                break;
            case R.id.tv_dialog_hint_sure:
                openAPpSetting();
                mDialog.cancel();
                break;
            default:
                break;
        }
    }

    //打开权限管理
    private void openAPpSetting() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + mActivity.getPackageName()));
        mActivity.startActivity(intent);
    }
}
