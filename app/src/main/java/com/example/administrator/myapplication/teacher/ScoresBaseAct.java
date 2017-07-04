package com.example.administrator.myapplication.teacher;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.common.HttpUtility;
import com.example.administrator.myapplication.common.MyAdapter;
import com.example.administrator.myapplication.data.QuestionSimpleInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/15.
 */

public class ScoresBaseAct  extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup rg_tab_bar;
    private RadioButton rb_score;

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
        rb_score = (RadioButton) findViewById(R.id.rb_score);
        rb_score.setChecked(true);
        TextView txtTopbar = (TextView)findViewById(R.id.txt_topbar);
        txtTopbar.setText("分数");
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_class:
                Intent intent = new Intent(ScoresBaseAct.this, ClassAct.class);
                startActivity(intent);
                finish();
                break;
            case R.id.rb_task:
                Intent intent2 = new Intent(ScoresBaseAct.this, TaskBaseAct.class);
                startActivity(intent2);
                finish();
                break;
            case R.id.rb_score:
                showScores();
                break;
        }
    }

    private void showScores(){
        list_content = (ListView) findViewById(R.id.lv_main);
        list_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final QuestionSimpleInfo qsi = (QuestionSimpleInfo)myAdapter.getItem(position);
                Intent intent;
                intent = new Intent(ScoresBaseAct.this, StudentsScoresAct.class);
                intent.putExtra("studentsJson", qsi.getStudents());
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
                JsonObject jo = new JsonParser().parse(classInfojson).getAsJsonObject();
                JsonArray ja = jo.get("questions").getAsJsonArray();

                QuestionSimpleInfo qsi;
                for (int i = 0; i < ja.size(); i++) {
                    qsi = new QuestionSimpleInfo(ja.get(i).getAsJsonObject().get("questionInfo").getAsJsonObject().get("id").getAsInt(),
                            ja.get(i).getAsJsonObject().get("questionInfo").getAsJsonObject().get("title").getAsString(),
                            ja.get(i).getAsJsonObject().get("questionInfo").getAsJsonObject().get("description").getAsString(),
                            ja.get(i).getAsJsonObject().get("questionInfo").getAsJsonObject().get("type").getAsString(),
                            ja.get(i).getAsJsonObject().get("students")+"");
                    mData.add(qsi);
                }

                //Adapter初始化
                myAdapter = new MyAdapter<QuestionSimpleInfo>((ArrayList)mData,R.layout.item_question) {
                    @Override
                    public void bindView(ViewHolder holder, QuestionSimpleInfo qsi) {
                        holder.setText(R.id.question_title,qsi.getTitle());
                    }
                };

                list_content.setAdapter(myAdapter);

            }
        };

        new Thread(){
            @Override
            public void run() {
                String[] res = HttpUtility.sendGet("assignment/38/score", "");
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putStringArray("res", res);
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }.start();
    }

}
