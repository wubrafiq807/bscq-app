package com.location.rafiqul.bcsq;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.location.rafiqul.bcsq.Bean.QuestionBean;
import com.location.rafiqul.bcsq.dao.DBHelper;
import com.location.rafiqul.bcsq.model.CustomRequest;
import com.location.rafiqul.bcsq.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=findViewById(R.id.showText);
        //updateDBInfo();
checkdata();
    }
private void checkdata(){
        DBHelper db=new DBHelper(this);
        List<Question> list=db.getAllQuestion();
        String res;
}
    private void updateDBInfo(){
        final DBHelper dbHelper=new DBHelper(this);
      //  String url="http://10.44.22.99/bcsqs/api";



        String url = "http://10.44.22.99/bcsqs/api";
        Map<String, String> params = new HashMap<String, String>();
        params.put("methodType", "GET");
        params.put("actionTypp", "getAllQue");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                textView.setText(response.toString());
                Log.d("Response: ", response.toString());
                Gson gson = new Gson();
                Type type = new TypeToken<List<QuestionBean>>(){}.getType();
                List<QuestionBean> contactList = gson.fromJson(response.toString(), type);
                for (QuestionBean questionBean : contactList){
                    Cursor cursor=dbHelper.getData(DBHelper.QUESTION_TABLE_NAME,DBHelper.QUESTION_COLUMN_QUESTIONID,questionBean.id);
                    if ((cursor == null)){
                        dbHelper.insertQuestion(Integer.parseInt(questionBean.id),Integer.parseInt(questionBean.sub_category_id),
                                questionBean.question.toString(),questionBean.description.toString(),questionBean.answer.toString(),
                                Integer.parseInt(questionBean.is_multiple_ans),Integer.parseInt(questionBean.status));
                    }if((cursor != null)&&(Integer.parseInt(questionBean.id)==2)){
                        dbHelper.delete(DBHelper.QUESTION_TABLE_NAME,DBHelper.QUESTION_COLUMN_QUESTIONID,questionBean.id.toString());
                    }
                    Log.i("Contact Details", questionBean.id + "-" + questionBean.answer + "-" + questionBean.created_date);
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("Response: ", response.toString());
            }
        });
      RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
      requestQueue.add(jsObjRequest);

    }
}
