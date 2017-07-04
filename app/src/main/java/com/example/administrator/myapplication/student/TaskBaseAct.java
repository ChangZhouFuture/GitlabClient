package com.example.administrator.myapplication.student;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.common.MyAdapter;
import com.example.administrator.myapplication.teacher.ClassAct;
import com.example.administrator.myapplication.teacher.ExamAct;
import com.example.administrator.myapplication.teacher.ExerciseAct;
import com.example.administrator.myapplication.teacher.HomeworkAct;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/24.
 */

public class TaskBaseAct extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{


    private RadioGroup rg_tab_bar;
    private RadioButton rb_task;

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
        rb_task = (RadioButton) findViewById(R.id.rb_task);
        rb_task.setChecked(true);
        TextView txtTopbar = (TextView)findViewById(R.id.txt_topbar);
        txtTopbar.setText("任务");
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_analysis:
                Intent intent = new Intent(TaskBaseAct.this, AnalysisAct.class);
                startActivity(intent);
                finish();
                break;
            case R.id.rb_task:
                showTaskList();
                break;
            case R.id.rb_readme:
                Intent intent2 = new Intent(TaskBaseAct.this, com.example.administrator.myapplication.student.ReadmeAct.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    private void showTaskList(){
        list_content = (ListView) findViewById(R.id.lv_main);

        mData = new ArrayList<Object>();
        mData.add("作业");
        mData.add("练习");
        mData.add("考试");
        myAdapter = new MyAdapter<String>((ArrayList)mData,R.layout.item_tasktype) {
            @Override
            public void bindView(ViewHolder holder, String text) {
                holder.setText(R.id.tasktype,text);
            }
        };
        list_content.setAdapter(myAdapter);
        list_content.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent;
                switch (position){
                    case 0:
                        intent = new Intent(TaskBaseAct.this, HomeworkAct.class);
                        startActivity(intent);
                        break;
                    case 1:
                        intent = new Intent(TaskBaseAct.this, ExerciseAct.class);
                        startActivity(intent);
                        break;
                    case 2:
                        intent = new Intent(TaskBaseAct.this, ExamAct.class);
                        startActivity(intent);
                        break;
                }

            }
        });
    }


}
