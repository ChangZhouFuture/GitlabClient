package com.example.administrator.myapplication.student;

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
import com.example.administrator.myapplication.data.MetricData;
import com.example.administrator.myapplication.data.QuestionRes;
import com.example.administrator.myapplication.data.ScoreRes;
import com.example.administrator.myapplication.data.TestCase;
import com.example.administrator.myapplication.data.TestRes;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import junit.framework.Test;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/25.
 */

public class AnalysisAct extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {


    private RadioGroup rg_tab_bar;
    private RadioButton rb_analysis;

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
        rb_analysis = (RadioButton) findViewById(R.id.rb_analysis);
        rb_analysis.setChecked(true);
        TextView txtTopbar = (TextView)findViewById(R.id.txt_topbar);
        txtTopbar.setText("分析");
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rb_task:
                Intent intent = new Intent(AnalysisAct.this, com.example.administrator.myapplication.student.TaskBaseAct.class);
                startActivity(intent);
                finish();
                break;
            case R.id.rb_analysis:
                showAnalysis();
                break;
            case R.id.rb_readme:
                Intent intent2 = new Intent(AnalysisAct.this, com.example.administrator.myapplication.student.ReadmeAct.class);
                startActivity(intent2);
                finish();
                break;
        }
    }

    private void showAnalysis(){
        list_content = (ListView) findViewById(R.id.lv_main);

        mData = new ArrayList<Object>();

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();
                String[] res = data.getStringArray("res");

                String analysisInfoJson = res[1];
                JsonObject jo =  new JsonParser().parse(analysisInfoJson).getAsJsonObject();
                JsonArray ja = jo.get("questionResults").getAsJsonArray();
                QuestionRes qr;
                ScoreRes sr;
                TestRes tr;
                MetricData md;
                for (int i = 0; i < ja.size(); i++) {
                    JsonObject tmpobj = ja.get(i).getAsJsonObject().get("scoreResult").getAsJsonObject();
                    sr = new ScoreRes(tmpobj.get("git_url").getAsString(), tmpobj.get("score").getAsInt(), tmpobj.get("scored").getAsBoolean());
                    tmpobj = ja.get(i).getAsJsonObject().get("testResult").getAsJsonObject();
                    JsonArray tmpja = tmpobj.get("testcases").getAsJsonArray();
                    TestCase[] testCases = new TestCase[tmpja.size()];
                    for (int j =0; j < tmpja.size(); j++) {
                        testCases[j] = new TestCase(tmpja.get(j).getAsJsonObject().get("name").getAsString(), tmpja.get(j).getAsJsonObject().get("passed").getAsBoolean());;
                    }
                    tr = new TestRes(tmpobj.get("git_url").getAsString(), tmpobj.get("compile_succeeded").getAsBoolean(),
                            tmpobj.get("tested").getAsBoolean(), testCases);
                    tmpobj = ja.get(i).getAsJsonObject().get("metricData").getAsJsonObject();
                    md = new MetricData(tmpobj.get("git_url").getAsString(), tmpobj.get("measured").getAsBoolean(), tmpobj.get("total_line_count").getAsInt(),
                            tmpobj.get("comment_line_count").getAsInt(), tmpobj.get("field_count").getAsInt(), tmpobj.get("method_count").getAsInt(),
                            tmpobj.get("max_coc").getAsInt());
                    qr = new QuestionRes(ja.get(i).getAsJsonObject().get("questionId").getAsInt(),
                            ja.get(i).getAsJsonObject().get("questionTitle").getAsString(), sr, tr, md);
                    mData.add(qr);
                }

                //Adapter初始化
                myAdapter = new MyAdapter<QuestionRes>((ArrayList)mData,R.layout.item_question_res) {
                    @Override
                    public void bindView(ViewHolder holder, QuestionRes qr) {
                        holder.setText(R.id.question_title,qr.getQuestionTitle());
                        holder.setText(R.id.score_res, "分数："+qr.getScoreRes().getScore()+"");
                        TestCase[] testCases = qr.getTestRes().getTestCases();
                        String testRes = "";
                        for (int i = 0; i < testCases.length; i++) {
                            testRes += "样例名："+testCases[i].getName() + "  " + "通过：" + testCases[i].getPassed() + "\n";
                        }
                        holder.setText(R.id.test_res, testRes);
                        String metricData = "代码行数："+ qr.getMetricData().getTotalLine() + "\n注释行数：" + qr.getMetricData().getCommentLine() +
                                "\n域数：" + qr.getMetricData().getFieldCount() + "\n方法数：" + qr.getMetricData().getMethodCount();
                        holder.setText(R.id.metric_data, metricData);
                    }
                };

                list_content.setAdapter(myAdapter);

            }
        };

        new Thread(){
            @Override
            public void run() {
                String[] res = HttpUtility.sendGet("assignment/98/student/227/analysis", "");
                Message msg = new Message();
                Bundle data = new Bundle();
                data.putStringArray("res", res);
                msg.setData(data);
                handler.sendMessage(msg);
            }
        }.start();
    }


}
