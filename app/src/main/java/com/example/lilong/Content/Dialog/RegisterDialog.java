package com.example.lilong.Content.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.lilong.Content.Net.RegisterAPI;
import com.example.lilong.R;

import org.json.JSONObject;

/**
 * Created by long on 2018/06/07.
 * 注册弹窗
 */

public class RegisterDialog extends Dialog{

    EditText userName;
    EditText passWord;
    EditText passWordTwo;
    Button register;
    Context context;

    public RegisterDialog( Context context) {
        super(context);
        this.context = context;
        initView();
    }

    private void initView() {
        setContentView(R.layout.dialog_register);
        userName = findViewById(R.id.register_et_user_name);
        passWord = findViewById(R.id.register_et_password);
        passWordTwo = findViewById(R.id.register_et_password_two);
        register = findViewById(R.id.register_btn_register);

    }

    public String[] getRegister(){
        return new String[]{userName.getText().toString(),passWord.getText().toString(),passWordTwo.getText().toString()};
    }


    /**
     * 注册按钮的回调接口
     * @param listener
     */
    public void setRegisterListener(View.OnClickListener listener){
        register.setOnClickListener(listener);
    }
}
