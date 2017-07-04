package com.example.administrator.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.administrator.myapplication.common.HttpUtility;
import com.example.administrator.myapplication.student.TaskBaseAct;
import com.example.administrator.myapplication.teacher.ClassAct;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Created by Administrator on 2017/6/11.
 */

public class LoginAct extends AppCompatActivity implements View.OnClickListener{
    private Button bt_username_clear;
    private Button bt_pswd_clear;
    private Button bt_login;
    private EditText et_username;
    private EditText et_pswd;

    public static String name = "";
    public static String password = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        et_username = (EditText) findViewById(R.id.username);
        et_pswd = (EditText) findViewById(R.id.password);
        bt_pswd_clear = (Button)findViewById(R.id.bt_pwd_clear);
        bt_username_clear = (Button)findViewById(R.id.bt_username_clear);
        bt_login = (Button)findViewById(R.id.login);

        bt_username_clear.setOnClickListener(this);
        bt_pswd_clear.setOnClickListener(this);
        bt_login.setOnClickListener(this);

        et_username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0)
                    bt_username_clear.setVisibility(View.VISIBLE);
                else
                    bt_username_clear.setVisibility(View.INVISIBLE);
            }
        });

        et_pswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.toString().length() > 0)
                    bt_pswd_clear.setVisibility(View.VISIBLE);
                else
                    bt_pswd_clear.setVisibility(View.INVISIBLE);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_username_clear:
                et_username.setText("");
                break;
            case R.id.bt_pwd_clear:
                et_pswd.setText("");
                break;
            case R.id.login:
                final Handler handler = new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        Bundle data = msg.getData();
                        String[] res = data.getStringArray("res");

                        if(res.length == 2 && res[0] != null && res[1] != null & res[0].equals("success")){
                            name = et_username.getText().toString();
                            password = et_pswd.getText().toString();

                            String userInfo = res[1];
                            JsonObject jo = new JsonParser().parse(userInfo).getAsJsonObject();
                            String type = jo.get("type").getAsString();
                            Intent intent;
                            if (type.equals("student")) {
                                intent = new Intent(LoginAct.this, TaskBaseAct.class);
                            } else {
                                intent = new Intent(LoginAct.this, ClassAct.class);
                            }
                            startActivity(intent);
                        } else {
                            Log.d("fail", "loginfail");
                        }
                    }
                };

                        new Thread(){
                            @Override
                            public void run() {
                                String[] res = HttpUtility.sendPost("user/auth", "{\"username\":\"" + et_username.getText() + "\", \"password\":\""
                                        + et_pswd.getText() + "\"}");
                                Message msg = new Message();
                                Bundle data = new Bundle();
                                data.putStringArray("res", res);
                                msg.setData(data);
                                handler.sendMessage(msg);
                    }
                }.start();
                break;
        }
    }


}
