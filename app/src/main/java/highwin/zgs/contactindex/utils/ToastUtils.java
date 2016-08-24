package highwin.zgs.contactindex.utils;

import android.content.Context;
import android.support.annotation.IntDef;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import highwin.zgs.contactindex.R;

/**
 * User: zgsHighwin
 * Email: 799174081@qq.com Or 799174081@gmail.com
 * Description:
 * Create-Time: 2016/8/24 9:51
 */
public class ToastUtils {

    public static Toast mToast;
    public static final int LENGTH_SHORT = 0;

    public static final int LENGTH_LONG = 1;

    @IntDef({LENGTH_SHORT, LENGTH_LONG})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Duration {
    }

    public static void showToast(Context mContext, String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }
        mToast.setText(msg);
        mToast.show();
    }

    /**
     * 自定义的土司
     *
     * @param text 显示的内容
     */
    public static void showCustomToast(Context context, String text) {
        showCustomToast(context, text, Gravity.CENTER);
    }

    public static void showCustomToast(Context context, String text, int gravity) {
        showCustomToast(context, text, gravity, 0, 0);
    }

    public static void showCustomToast(Context context, String text, int gravity, int xOffset, int yOffset) {
        showCustomToast(context, text, gravity, xOffset, yOffset, ToastUtils.LENGTH_SHORT);
    }

    /**
     * show customToast
     */
    public static void showCustomToast(Context context, String text, int gravity, int xOffset, int yOffset, @Duration int duration) {
        if (mToast == null) {
            mToast = new Toast(context);
        }
        View view = View.inflate(context, R.layout.mytoast, null);
        TextView tv = (TextView) view.findViewById(R.id.tv);
        //设置显示内容
        tv.setText(text);
        mToast.setView(view);
        //设置Toast显示的时长。0表示短，1表示常
        mToast.setDuration(duration);
        //设置Toast显示在窗体中的位置（这里是显示在窗体顶部的中央）
        mToast.setGravity(gravity, xOffset, yOffset);
        //将Toast显示出来
        mToast.show();
    }
}
