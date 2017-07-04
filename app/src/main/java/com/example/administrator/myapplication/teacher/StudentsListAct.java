package com.example.administrator.myapplication.teacher;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.common.HttpUtility;
import com.example.administrator.myapplication.common.MyAdapter;
import com.example.administrator.myapplication.data.UserInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/15.
 */

public class StudentsListAct extends AppCompatActivity{


    private ListView list_content;
    private ArrayList<Object> mData = null;
    private MyAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        TextView txtTopbar = (TextView)findViewById(R.id.txt_topbar);
        txtTopbar.setText("学生列表");

        list_content = (ListView) findViewById(R.id.lv_main);
        mData = new ArrayList<Object>();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String[] res = data.getStringArray("res");

                String classInfojson = res[1];
                JsonArray ja = new JsonParser().parse(classInfojson).getAsJsonArray();
                UserInfo si;
                for (int i = 0; i < ja.size(); i++) {
                    si = new UserInfo(ja.get(i).getAsJsonObject().get("id").getAsInt(),
                            ja.get(i).getAsJsonObject().get("username").getAsString(),ja.get(i).getAsJsonObject().get("name").getAsString(),
                            ja.get(i).getAsJsonObject().get("type").getAsString(),
                            ja.get(i).getAsJsonObject().get("gender").getAsString(),ja.get(i).getAsJsonObject().get("email").getAsString(),
                            ja.get(i).getAsJsonObject().get("gitId")+"",ja.get(i).getAsJsonObject().get("schoolId").getAsInt(),
                            ja.get(i).getAsJsonObject().get("gitUsername").getAsString(),ja.get(i).getAsJsonObject().get("number").getAsString(),
                            ja.get(i).getAsJsonObject().get("groupId").getAsString());
                    mData.add(si);
                }

                //Adapter初始化
                myAdapter = new MyAdapter<UserInfo>((ArrayList)mData,R.layout.item_student) {
                    @Override
                    public void bindView(ViewHolder holder, UserInfo si) {
                        holder.setText(R.id.student_name,si.getName());
                        holder.setText(R.id.student_gender, si.getGender());
                    }
                };

                list_content.setAdapter(myAdapter);

            }
        };

        new Thread(){
            @Override
            public void run() {
                String[] res = HttpUtility.sendGet("group/"+StudentsListAct.this.getIntent().getStringExtra("groupid")+"/students", "");
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putStringArray("res", res);
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }.start();

    }

}
