package com.example.administrator.myapplication.teacher;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.common.MyAdapter;
import com.example.administrator.myapplication.data.QuestionInfo;
import com.example.administrator.myapplication.data.UserInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/24.
 */

public class QuestionsAct extends AppCompatActivity{


    private ListView list_content;
    private ArrayList<Object> mData = null;
    private MyAdapter myAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_layout);
        TextView txtTopbar = (TextView)findViewById(R.id.txt_topbar);
        txtTopbar.setText("问题列表");
        showQuestionList();
    }

    private void showQuestionList(){
        list_content = (ListView) findViewById(R.id.lv_main);

        mData = new ArrayList<Object>();

        String questionsJson = getIntent().getStringExtra("questions");
        JsonArray ja = new JsonParser().parse(questionsJson).getAsJsonArray();
        QuestionInfo qi;
        for (int i = 0; i < ja.size(); i++) {
            JsonObject jo = ja.get(i).getAsJsonObject().get("creator").getAsJsonObject();
            UserInfo ui = new UserInfo(jo.get("id").getAsInt(), jo.get("username").getAsString(), jo.get("name").getAsString(),
                    jo.get("type").getAsString(), jo.get("avatar")+"", jo.get("gender").getAsString(), jo.get("email").getAsString(),
                    jo.get("gitId")+"", jo.get("schoolId").getAsInt(), jo.get("gitUsername")+"", jo.get("number")+"",
                    jo.get("groupId")+"");
            qi = new QuestionInfo(ja.get(i).getAsJsonObject().get("id").getAsInt(), ja.get(i).getAsJsonObject().get("title").getAsString(),
                    ja.get(i).getAsJsonObject().get("description")+"", ja.get(i).getAsJsonObject().get("difficulty")+"",
                    ja.get(i).getAsJsonObject().get("gitUrl")+"", ja.get(i).getAsJsonObject().get("type").getAsString(),ui
                    );
            mData.add(qi);
        }

        //Adapter初始化
        myAdapter = new MyAdapter<QuestionInfo>((ArrayList)mData,R.layout.item_question) {
            @Override
            public void bindView(ViewHolder holder, QuestionInfo qi) {
                holder.setText(R.id.question_title,qi.getTitle());
                holder.setText(R.id.question_difficulty, "难度："+qi.getDifficulty());
                holder.setText(R.id.question_creator, "创建者："+qi.getUi().getName());
            }
        };

        list_content.setAdapter(myAdapter);
    }

}
