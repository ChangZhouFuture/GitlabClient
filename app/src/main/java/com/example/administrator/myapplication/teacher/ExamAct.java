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
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.common.HttpUtility;
import com.example.administrator.myapplication.common.MyAdapter;
import com.example.administrator.myapplication.data.TaskInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/23.
 */

public class ExamAct extends AppCompatActivity {


    private ListView list_content;
    private ArrayList<Object> mData = null;
    private MyAdapter myAdapter = null;

    private String[] questionsJson = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        TextView txtTopbar = (TextView)findViewById(R.id.txt_topbar);
        txtTopbar.setText("考试列表");
        showExamList();
    }

    private void showExamList(){
        list_content = (ListView) findViewById(R.id.lv_main);
        list_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                intent = new Intent(ExamAct.this, QuestionsAct.class);
                intent.putExtra("questions", questionsJson[position]);
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

                String examInfojson = res[1];
                JsonArray ja = new JsonParser().parse(examInfojson).getAsJsonArray();
                TaskInfo ei;
                questionsJson = new String[ja.size()];
                for (int i = 0; i < ja.size(); i++) {
                    questionsJson[i] = ja.get(i).getAsJsonObject().get("questions")+"";
                    ei = new TaskInfo(ja.get(i).getAsJsonObject().get("id").getAsInt(), ja.get(i).getAsJsonObject().get("title").getAsString(),
                            ja.get(i).getAsJsonObject().get("description")+"", ja.get(i).getAsJsonObject().get("startAt").getAsString(),
                            ja.get(i).getAsJsonObject().get("endAt").getAsString(), ja.get(i).getAsJsonObject().get("questions")+"",
                            ja.get(i).getAsJsonObject().get("course").getAsInt(), ja.get(i).getAsJsonObject().get("status").getAsString(),
                            ja.get(i).getAsJsonObject().get("currentTime").getAsString());
                    mData.add(ei);
                }

                //Adapter初始化
                myAdapter = new MyAdapter<TaskInfo>((ArrayList)mData,R.layout.item_task) {
                    @Override
                    public void bindView(ViewHolder holder, TaskInfo ei) {
                        holder.setText(R.id.task_title,ei.getTitle());
                        holder.setText(R.id.task_course, "课程："+ei.getCourse());
                        holder.setText(R.id.task_startAt, "开始日期:"+ei.getStartAt());
                        holder.setText(R.id.task_endAt, "结束日期:"+ei.getEndAt());
                        holder.setText(R.id.task_status, "状态："+ei.getStatus());
                    }
                };

                list_content.setAdapter(myAdapter);

            }
        };

        new Thread(){
            @Override
            public void run() {
                String[] res = HttpUtility.sendGet("course/2/exam", "");
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putStringArray("res", res);
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }.start();
    }


}
