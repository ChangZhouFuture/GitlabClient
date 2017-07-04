package com.example.administrator.myapplication.student;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.common.HttpUtility;
import com.example.administrator.myapplication.common.MyAdapter;
import com.example.administrator.myapplication.data.Readme;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/25.
 */

public class ReadmeAct extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{


    private RadioGroup rg_tab_bar;
    private RadioButton rb_readme;

    private ListView list_content;
    private ArrayList<Object> mData = null;
    private MyAdapter myAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_base_act);

        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_readme = (RadioButton) findViewById(R.id.rb_readme);
        rb_readme.setChecked(true);
        TextView txtTopbar = (TextView)findViewById(R.id.txt_topbar);
        txtTopbar.setText("Readme");
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_task:
                Intent intent = new Intent(ReadmeAct.this, com.example.administrator.myapplication.student.TaskBaseAct.class);
                startActivity(intent);
                finish();
                break;
            case R.id.rb_analysis:
                Intent intent2 = new Intent(ReadmeAct.this, com.example.administrator.myapplication.student.AnalysisAct.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.rb_readme:
                showReadme();
                break;
        }
    }

    private void showReadme(){
        list_content = (ListView) findViewById(R.id.lv_main);

        mData = new ArrayList<Object>();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String[] res = data.getStringArray("res");

                final String readmeInfo = res[1];
                JsonObject jo =  new JsonParser().parse(readmeInfo).getAsJsonObject();
                Readme readme = new Readme(jo.get("content").getAsString());
                mData.add(readme);

                //Adapter初始化
                myAdapter = new MyAdapter<Readme>((ArrayList)mData,R.layout.item_readme) {
                    @Override
                    public void bindView(ViewHolder holder, Readme readme) {
                        holder.setText(R.id.readme_content, readme.getContent());
                    }
                };

                list_content.setAdapter(myAdapter);

            }
        };

        new Thread(){
            @Override
            public void run() {
                String[] res = HttpUtility.sendGet("assignment/98/student/227/question/26", "");
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putStringArray("res", res);
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }.start();
    }



}
