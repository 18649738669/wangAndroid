package com.example.lilong.Content.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lilong.Content.Dialog.RegisterDialog;
import com.example.lilong.Content.Net.LoginAPI;
import com.example.lilong.Content.Net.RegisterAPI;
import com.example.lilong.R;
import com.example.lilong.Tool.APP.Builds;
import com.example.lilong.Tool.Activity.BasicAct;
import com.example.lilong.Tool.DB.SPHelper;
import com.example.lilong.Tool.Utils.JSONUtils;
import com.example.lilong.Tool.Utils.LogUtils;
import com.example.lilong.Tool.Utils.TipUtils;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by long on 2018/06/07.
 * 登陆界面
 */

public class LoginAct extends BasicAct implements RegisterAPI.RegisterAPIListener,
        LoginAPI.LoginAPIListener{


    @BindView(R.id.login_et_user_name)
    EditText etUserName;
    @BindView(R.id.login_et_password)
    EditText etPassword;
    @BindView(R.id.login_tool_bar_btn_back)
    LinearLayout btnBack;
    @BindView(R.id.login_btn_login)
    TextView btnLogin;
    @BindView(R.id.login_btn_register)
    TextView btnRegister;

    RegisterDialog registerDialog;


    public LoginAct() {
        super(R.layout.act_login, R.string.title_activity_login, false, TOOLBAR_TYPE_NO_TOOLBAR);
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, LoginAct.class);
        context.startActivity(intent);
    }

    @Override
    public void initView() {

        registerDialog = new RegisterDialog(this);
        Window window = registerDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(0xfafafa));
        window.setDimAmount(0);
        registerDialog.setRegisterListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str[] = registerDialog.getRegister();
                submitRegister(str);

            }
        });
    }

    public void submitRegister( String str[]){
        new RegisterAPI(this,str[0],str[1],str[2]);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.login_tool_bar_btn_back, R.id.login_btn_login, R.id.login_btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_tool_bar_btn_back:
                finish();
                break;
            case R.id.login_btn_login:
                new LoginAPI(this,etUserName.getText().toString(),etPassword.getText().toString());
                break;
            case R.id.login_btn_register:
                registerDialog.show();
                break;
        }
    }

    /**
     * 注册接口
     * @param content
     */
    @Override
    public void registerSuccess(JSONObject content) {

        String email = JSONUtils.getString(content,"email");
        String icon = JSONUtils.getString(content,"icon");
        int id = JSONUtils.getInt(content,"id");
        int type = JSONUtils.getInt(content,"type");
        String password = JSONUtils.getString(content,"password");
        String username = JSONUtils.getString(content,"username");
        SPHelper.save(Builds.SP_USER,"email",email);
        SPHelper.save(Builds.SP_USER,"icon",icon);
        SPHelper.save(Builds.SP_USER,"password",password);
        SPHelper.save(Builds.SP_USER,"username",username);
        SPHelper.save(Builds.SP_USER,"id",id);
        SPHelper.save(Builds.SP_USER,"type",type);
        registerDialog.dismiss();
        new LoginAPI(this,username,password);
        TipUtils.showTip("注册成功！");

    }

    @Override
    public void registerError(long code, String msg) {

        TipUtils.showTip(msg);
    }

    /**
     * 登陆接口
     * @param content
     */
    @Override
    public void loginSuccess(JSONObject content) {
        String email = JSONUtils.getString(content,"email");
        String icon = JSONUtils.getString(content,"icon");
        int id = JSONUtils.getInt(content,"id");
        int type = JSONUtils.getInt(content,"type");
        String password = JSONUtils.getString(content,"password");
        String username = JSONUtils.getString(content,"username");
        SPHelper.save(Builds.SP_USER,"email",email);
        SPHelper.save(Builds.SP_USER,"icon",icon);
        SPHelper.save(Builds.SP_USER,"password",password);
        SPHelper.save(Builds.SP_USER,"username",username);
        SPHelper.save(Builds.SP_USER,"id",id);
        SPHelper.save(Builds.SP_USER,"type",type);
        TipUtils.showTip("登陆成功");
        finish();
    }

    @Override
    public void loginError(long code, String msg) {

        TipUtils.showTip(msg);
    }
}
