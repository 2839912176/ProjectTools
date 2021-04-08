package com.special.PengCheng.tools.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.special.PengCheng.R;
import com.special.PengCheng.tools.MyLogUtils;


/**
 * 首页 每天第一次打开
 */

public class HomeDialog implements View.OnClickListener {

    private AlertDialog mDialog;
    private IDialogListener mListener;
    private HomeDialog(Context context){
        mDialog = new AlertDialog.Builder(context).create();
        // 去掉默认的白色背景 ；  然后可以在 自定义布局中加适当范围的背景；
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

    public static HomeDialog create(Context context){
        return new HomeDialog(context);
    }

    public void beginShow(String imgUrl, Activity activity, IDialogListener listener){
        this.mListener = listener;
        mDialog.show();

        Window window = mDialog.getWindow();
        if(window != null){
            window.setContentView(R.layout.dialog_code);
            window.setWindowAnimations(R.style.dialog_scale_animation);
            window.setGravity(Gravity.CENTER);

            WindowManager.LayoutParams params = window.getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            params.dimAmount = 0.7f;

            window.setAttributes(params);

            ImageView imgCxt = window.findViewById(R.id.imgCode);
            ImageView imgClose = window.findViewById(R.id.imgClose);
            //TextView txtSure = window.findViewById(R.id.txtSure);

            MyLogUtils.debug("TAG", "-------------imgUrl: " + imgUrl );
            Glide.with(activity).load(imgUrl).placeholder(R.mipmap.iocn_default).error(R.mipmap.iocn_default)
                    .into(imgCxt);
            //GlideApp.with(activity).load(imgUrl).into(imgCxt);

            //txtSure.setOnClickListener(this);
            imgClose.setOnClickListener(this);

        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.txtSure:
//                mDialog.cancel();
//                if(mListener != null){
//                    mListener.onSure();
//                }
//                break;
            case R.id.imgClose:
                mDialog.cancel();
                break;
        }
    }
}
