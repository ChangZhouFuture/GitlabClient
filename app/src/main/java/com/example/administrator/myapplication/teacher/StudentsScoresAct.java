package com.example.administrator.myapplication.teacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.common.MyAdapter;
import com.example.administrator.myapplication.data.StudentScoreInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/24.
 */

public class StudentsScoresAct extends AppCompatActivity {


    private ListView list_content;
    private ArrayList<Object> mData = null;
    private MyAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        TextView txtTopbar = (TextView)findViewById(R.id.txt_topbar);
        txtTopbar.setText("学生得分情况");

        list_content = (ListView) findViewById(R.id.lv_main);
        mData = new ArrayList<Object>();

        String studentsJson = getIntent().getStringExtra("studentsJson");
        JsonArray ja = new JsonParser().parse(studentsJson).getAsJsonArray();
        StudentScoreInfo si;
        for (int i = 0; i < ja.size(); i++) {
            si = new StudentScoreInfo(ja.get(i).getAsJsonObject().get("studentId").getAsInt(),
                    ja.get(i).getAsJsonObject().get("studentName").getAsString(),ja.get(i).getAsJsonObject().get("studentNumber").getAsString(),
                    ja.get(i).getAsJsonObject().get("score").getAsInt(),
                    ja.get(i).getAsJsonObject().get("scored").getAsBoolean());
            mData.add(si);
        }

        //Adapter初始化
        myAdapter = new MyAdapter<StudentScoreInfo>((ArrayList)mData,R.layout.item_student_score) {
            @Override
            public void bindView(ViewHolder holder, StudentScoreInfo si) {
                holder.setText(R.id.student_id,si.getStudentId()+"");
                holder.setText(R.id.student_name, si.getStudentName());
                holder.setText(R.id.student_score, si.getScore()+"");
            }
        };

        list_content.setAdapter(myAdapter);

    }


}
