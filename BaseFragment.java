package cn.lxw.us.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import cn.lxw.us.BaseActivity;

/**
 * Created by Lianxw on 2015/8/1.
 *
 */
public class BaseFragment extends Fragment {

    protected void showToast(CharSequence msg) {
        Toast.makeText(getActivity(),msg, Toast.LENGTH_SHORT).show();
    }

    protected void showProgress() {
        BaseActivity activity = getBaseActivity();
        if (activity!=null) {
            activity.showProgress();
        }
    }

    protected void hideProgress() {
        BaseActivity activity = getBaseActivity();
        if (activity!=null) {
            activity.hideProgress();
        }
    }

    private BaseActivity getBaseActivity() {
        Activity activity = getActivity();
        if (activity!=null && activity instanceof BaseActivity) {
            return (BaseActivity)activity;
        }
        return null;
    }
}
