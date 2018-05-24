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
import com.location.rafiqul.bcsq.Bean.AnswerBean;
import com.location.rafiqul.bcsq.Bean.CategoryBean;
import com.location.rafiqul.bcsq.Bean.QuestionBean;
import com.location.rafiqul.bcsq.Bean.SubCategoryBean;
import com.location.rafiqul.bcsq.dao.DBHelper;
import com.location.rafiqul.bcsq.model.CustomRequest;
import com.location.rafiqul.bcsq.model.Question;
import com.location.rafiqul.bcsq.util.Constant;

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

        updateDBCategory();
        updateDBSubCategory();
        updateDBQuestion();
        updateDBAnswer();

    }
      private void checkdata(){
        DBHelper db=new DBHelper(this);
        List<Question> list=db.getAllQuestion();
        String res;
     }
    private void updateDBAnswer(){
        final DBHelper dbHelper=new DBHelper(this);
        //  String url="http://10.44.22.99/bcsqs/api";



        String url = ""+Constant.IP_ADDRESS+"/bcsqs/api";
        Map<String, String> params = new HashMap<String, String>();
        params.put("methodType", "GET");
        params.put("actionTypp", "getAllAns");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                textView.setText(response.toString());
                Log.d("Response: ", response.toString());
                Gson gson = new Gson();
                Type type = new TypeToken<List<AnswerBean>>(){}.getType();
                List<AnswerBean> answerBeanList = gson.fromJson(response.toString(), type);
                for (AnswerBean answerBean:answerBeanList) {
                    Cursor cursor=dbHelper.getData(DBHelper.ANSWER_TABLE_NAME,DBHelper.ANSWER_COLUMN_ANSWERID,answerBean.id);
                    if(cursor==null){

                    }

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
    private void updateDBSubCategory(){
        final DBHelper dbHelper=new DBHelper(this);
        //  String url="http://10.44.22.99/bcsqs/api";



        String url = ""+Constant.IP_ADDRESS+"/bcsqs/api";
        Map<String, String> params = new HashMap<String, String>();
        params.put("methodType", "GET");
        params.put("actionTypp", "getAllSubCat");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                textView.setText(response.toString());
                Log.d("Response: ", response.toString());
                Gson gson = new Gson();
                Type type = new TypeToken<List<CategoryBean>>(){}.getType();
                List<SubCategoryBean> subCategoryBeanList = gson.fromJson(response.toString(), type);
                for (SubCategoryBean subCategoryBean:subCategoryBeanList) {
                    Cursor cursor=dbHelper.getData(DBHelper.SUBCATEGORY_TABLE_NAME,DBHelper.QUESTION_COLUMN_SUBCATEGORYID,subCategoryBean.id);
                    if(cursor==null){

                        }
                    if(cursor!=null &&(Integer.parseInt(subCategoryBean.status)==2)){
                        dbHelper.delete(DBHelper.SUBCATEGORY_TABLE_NAME,DBHelper.SUBCATEGORY_COLUMN_SUBCATEGORYID,subCategoryBean.id);
                    }
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

    private void updateDBCategory(){
        final DBHelper dbHelper=new DBHelper(this);
        //  String url="http://10.44.22.99/bcsqs/api";



        String url = ""+ Constant.IP_ADDRESS+"/bcsqs/api";
        Map<String, String> params = new HashMap<String, String>();
        params.put("methodType", "GET");
        params.put("actionTypp", "getallcategory");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                textView.setText(response.toString());
                Log.d("Response: ", response.toString());
                Gson gson = new Gson();
                Type type = new TypeToken<List<CategoryBean>>(){}.getType();
                List<CategoryBean> categoryBeanList = gson.fromJson(response.toString(), type);
                for (CategoryBean categoryBean:categoryBeanList) {
                    Cursor cursor=dbHelper.getData(DBHelper.CATEGORY_TABLE_NAME,DBHelper.CATEGORY_COLUMN_ID,categoryBean.id);
                    if(cursor==null){

                          }
                    if(cursor!=null &&(Integer.parseInt(categoryBean.status)==2)){
                        dbHelper.delete(DBHelper.CATEGORY_TABLE_NAME,DBHelper.CATEGORY_COLUMN_ID,categoryBean.id);
                    }
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

    private void updateDBQuestion(){
        final DBHelper dbHelper=new DBHelper(this);
      //  String url="http://10.44.22.99/bcsqs/api";



        String url = ""+Constant.IP_ADDRESS+"/bcsqs/api";
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
