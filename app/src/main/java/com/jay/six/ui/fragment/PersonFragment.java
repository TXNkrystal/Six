package com.jay.six.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jay.six.R;
import com.jay.six.common.BaseFragment;
import com.jay.six.common.Constants;
import com.jay.six.common.manager.PreferencesManager;
import com.jay.six.ui.activity.LoginActivity;
import com.jay.six.ui.activity.MoreInfoActivity;
import com.jay.six.ui.activity.SettingsActivity;
import com.jay.six.utils.picture.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2017/5/2 0002.
 */

public class PersonFragment extends BaseFragment {
    Unbinder unbinder;
    @BindView(R.id.img_photo)
    ImageView imgPhoto;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_uname)
    TextView tvUname;
    @BindView(R.id.img_like)
    ImageView imgLike;
    @BindView(R.id.img_ninght)
    ImageView imgNinght;
    @BindView(R.id.layout_feedback)
    LinearLayout layoutFeedback;
    @BindView(R.id.layout_setting)
    LinearLayout layoutSetting;
    @BindView(R.id.layout_about)
    LinearLayout layoutAbout;
    private View rootView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_person, null);
        setInflateView(rootView);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void initData() {
        if (PreferencesManager.getInstance(getActivity()).get(Constants.IS_LOGIN, false)) {
            imgPhoto.setVisibility(View.VISIBLE);
            tvUname.setVisibility(View.VISIBLE);
            btnLogin.setVisibility(View.GONE);
            loadUserInfo();
        } else {
            imgPhoto.setVisibility(View.GONE);
            tvUname.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
        }
    }

    //已经登录的话重新获取用户信息
    private void loadUserInfo() {
        String userPhoto = PreferencesManager.getInstance(getActivity()).get(Constants.USER_PHOTO);
        String userName = PreferencesManager.getInstance(getActivity()).get(Constants.USER_NAME);
        tvUname.setText(userName);
        ImageLoader.getInstance().displayImageTarget(imgPhoto, userPhoto);
    }

    @Override
    protected void initView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.layout_about,R.id.img_photo, R.id.btn_login, R.id.img_like, R.id.img_ninght, R.id.layout_feedback, R.id.layout_setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_photo:
                //TODO 修改用户信息
                startActivity(MoreInfoActivity.class);
                break;
            case R.id.btn_login:
                startActivity(LoginActivity.class);
                break;
            case R.id.img_like:
                //TODO 收藏

                break;
            case R.id.img_ninght:
                //TODO 夜间模式

                break;
            case R.id.layout_feedback:
                //TODO 用户反馈
                break;
            case R.id.layout_setting:
                // TODO: 2017/5/12 0012 用户设置
                startActivity(SettingsActivity.class);
                break;
            case R.id.layout_about:
                showAppInfo();
                break;
        }
    }

    private void showAppInfo() {
        AlertDialog.Builder builer = new AlertDialog.Builder(getActivity())
                .setTitle("关于我们")
                .setMessage("开发人:WinFred\n地址:https://github.com/Winfred1989/Six")
                .setPositiveButton("确定",null);
        builer.create().show();
    }

}
