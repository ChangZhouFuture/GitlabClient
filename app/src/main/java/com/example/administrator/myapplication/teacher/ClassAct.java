package com.example.administrator.myapplication.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.common.HttpUtility;
import com.example.administrator.myapplication.common.MyAdapter;
import com.example.administrator.myapplication.data.ClassInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/15.
 */

public class ClassAct extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{

    private RadioGroup rg_tab_bar;
    private RadioButton rb_class;

    private ListView list_content;
    private ArrayList<Object> mData = null;
    private MyAdapter myAdapter = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_base_act);

        rg_tab_bar = (RadioGroup) findViewById(R.id.rg_tab_bar);
        rg_tab_bar.setOnCheckedChangeListener(this);
        //获取第一个单选按钮，并设置其为选中状态
        rb_class = (RadioButton) findViewById(R.id.rb_class);
        rb_class.setChecked(true);
        TextView txtTopbar = (TextView)findViewById(R.id.txt_topbar);
        txtTopbar.setText("班级列表");
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_class:
                showClassList();
                break;
            case R.id.rb_task:
                Intent intent = new Intent(ClassAct.this, TaskBaseAct.class);
                startActivity(intent);
                finish();
                break;
            case R.id.rb_score:
                Intent intent2 = new Intent(ClassAct.this, ScoresBaseAct.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    private void showClassList(){
        list_content = (ListView) findViewById(R.id.lv_main);
        list_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final ClassInfo ci = (ClassInfo)myAdapter.getItem(position);
                Intent intent;
                intent = new Intent(ClassAct.this, StudentsListAct.class);
                intent.putExtra("groupid", ci.getId()+"");
                startActivity(intent);
            }
        });

        mData = new ArrayList<Object>();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String[] res = data.getStringArray("res");

                String classInfojson = res[1];
                JsonArray ja = new JsonParser().parse(classInfojson).getAsJsonArray();
                ClassInfo ci;
                for (int i = 0; i < ja.size(); i++) {
                    ci = new ClassInfo(ja.get(i).getAsJsonObject().get("id").getAsInt(), ja.get(i).getAsJsonObject().get("name").getAsString());
                    mData.add(ci);
                }

                //Adapter初始化
                myAdapter = new MyAdapter<ClassInfo>((ArrayList)mData,R.layout.item_class) {
                    @Override
                    public void bindView(ViewHolder holder, ClassInfo ci) {
                        holder.setText(R.id.class_name,ci.getName());
                    }
                };

                list_content.setAdapter(myAdapter);

            }
        };

        new Thread(){
            @Override
            public void run() {
                String[] res = HttpUtility.sendGet("group", "");
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putStringArray("res", res);
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }.start();
    }

}
