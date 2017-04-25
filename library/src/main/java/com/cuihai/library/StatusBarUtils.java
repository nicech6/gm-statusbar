package com.cuihai.library;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * description:
 * 适用于 状态栏透明和设置颜色
 *
 * @auther cuihai
 * @since 2017/4/25.
 */

public class StatusBarUtils {
    /**
     * 获取布局文件中的LinearLayout，把前面生成状态栏高度的View放进去
     * 适用于Fragment页面中，在onViewCreated方法里面初始化
     *
     * @param view     布局文件
     * @param activity 上下文环境
     * @param layoutId 布局文件中LinearLayout(容器)的Id
     */
    public static void init(View view, Activity activity, int layoutId) {
        LinearLayout decorView = (LinearLayout) view.findViewById(layoutId);
        decorView.addView(StatusBarUtils.createStatusView(activity, Color.parseColor("#02C080")));
    }

    /**
     * 适用于Activity,在onCreate方法里面初始化
     */
    public static void init(Activity activity, int layoutId) {
        LinearLayout decorView = (LinearLayout) activity.findViewById(layoutId);
        decorView.addView(StatusBarUtils.createStatusView(activity, Color.parseColor("#02C080")));
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param activity 需要设置的activity
     * @param color    状态栏颜色值
     * @return 状态栏矩形条
     */
    public static View createStatusView(Activity activity, int color) {
        // 获得状态栏高度
        int resourceId = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = activity.getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(activity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;

    }

    /**
     * 修改状态栏为全透明
     *
     * @param activity
     */
    @TargetApi(19)
    public static void transparencyBar(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = activity.getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = activity.getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
