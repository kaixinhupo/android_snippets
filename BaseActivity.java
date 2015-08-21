package cn.lxw.us;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Lianxw on 2015/7/31.
 *
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onResume() {
        super.onResume();
        JPushInterface.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        JPushInterface.onPause(this);
    }

    public void showProgress() {
        FrameLayout content = (FrameLayout)findViewById(android.R.id.content);
        if(content.findViewById(R.id.pb_loading)==null) {
            ProgressBar progressBar = new ProgressBar(this);
            progressBar.setId(R.id.pb_loading);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            progressBar.setLayoutParams(params);
            content.addView(progressBar);
        }
    }

    public void hideProgress() {
        FrameLayout content = (FrameLayout)findViewById(android.R.id.content);
        View progress = content.findViewById(R.id.pb_loading);
        if (progress!=null) {
            content.removeView(progress);
        }
    }

    protected void showToast(CharSequence text) {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show();
    }
}
