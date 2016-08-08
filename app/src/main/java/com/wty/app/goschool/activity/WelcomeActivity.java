package com.wty.app.goschool.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.wty.app.goschool.R;
import com.wty.app.library.activity.BaseActivity;
import com.wty.app.library.mvp.presenter.BasePresenter;
import com.wty.app.library.utils.CommonUtil;

import butterknife.Bind;

/**
 * @author wty
 * 欢迎页面
 */
public class WelcomeActivity extends BaseActivity {

    private static final float SCALE_END = 1.13F;

    @Bind(R.id.iv_welcome) ImageView mWelcomeImage;
    @Bind(R.id.tv_version) TextView mVersionText;

    private Handler mHandler;

    @Override
    public BasePresenter getPresenter() {
        return null;
    }

    @Override
    public void onInitView(Bundle savedInstanceState) {
        mHandler = new Handler();
        getLaunchImage();
    }

    @Override
    public int getLayoutResource() {
        return R.layout.activity_welcome;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /**
         * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/1122/3712.html
         * 在BaseActivity.java里：我们通过判断当前sdk_int大于4.4(kitkat),则通过代码的形式设置status bar为透明
         * (这里其实可以通过values-v19 的sytle.xml里设置windowTranslucentStatus属性为true来进行设置，但是在某些手机会不起效，所以采用代码的形式进行设置)。
         * 还需要注意的是我们这里的AppCompatAcitivity是android.support.v7.app.AppCompatActivity支持包中的AppCompatAcitivity,也是为了在低版本的android系统中兼容toolbar。
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Window window = getWindow();
                // Translucent status bar
                window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                        WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    private void getLaunchImage() {
        mVersionText.setText("V" + CommonUtil.getVersion(this) + "." + CommonUtil.getVersionCode(this));
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animateImage();
            }
        },1000);
    }

    private void animateImage() {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(mWelcomeImage, "scaleX", 1f, SCALE_END);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(mWelcomeImage, "scaleY", 1f, SCALE_END);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000).play(animatorX).with(animatorY);
        set.start();

        set.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation)
            {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                WelcomeActivity.this.finish();
            }
        });
    }

}
