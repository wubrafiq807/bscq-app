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
import com.location.rafiqul.bcsq.model.Answer;
import com.location.rafiqul.bcsq.model.Category;
import com.location.rafiqul.bcsq.model.CustomRequest;
import com.location.rafiqul.bcsq.model.Question;
import com.location.rafiqul.bcsq.model.Subcategory;
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
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.showText);

        updateDBCategory();
        // updateDBSubCategory();
        //updateDBQuestion();
        //updateDBAnswer();

    }

    private void checkdata() {
        DBHelper db = new DBHelper(this);
        List<Question> list = db.getAllQuestion();
        String res;
    }

    private void updateDBAnswer() {

        dbHelper = new DBHelper(getApplicationContext());


        String url = "" + Constant.IP_ADDRESS + "/bcsqs/api";
        Map<String, String> params = new HashMap<String, String>();
        params.put("methodType", "GET");
        params.put("actionTypp", "getAllAns");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {

                textView.setText(response.toString());
                Log.d("Response: ", response.toString());
                Gson gson = new Gson();
                Type type = new TypeToken<List<AnswerBean>>() {
                }.getType();
                List<AnswerBean> answerBeanList = gson.fromJson(response.toString(), type);


                for (AnswerBean answerBean : answerBeanList) {

                    Answer answer = dbHelper.getAnswerById(Integer.parseInt(answerBean.id));
                    if (answer.getId() == null) {
                        Answer answer1 = new Answer(Integer.parseInt(answerBean.question_id), answerBean.answer1, answerBean.answer2, answerBean.answer3, answerBean.answer4, Integer.parseInt(answerBean.cur_answer), Integer.parseInt(answerBean.id), 0);
                        long i = dbHelper.createAnswer(answer1);
                        Log.d("id: ", "" + i + "");
                    }
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("Response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsObjRequest);

    }

    private void updateDBSubCategory() {
        dbHelper = new DBHelper(getApplicationContext());

        String url = "" + Constant.IP_ADDRESS + "/bcsqs/api";
        Map<String, String> params = new HashMap<String, String>();
        params.put("methodType", "GET");
        params.put("actionTypp", "getAllSubCat");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                textView.setText(response.toString());
                Log.d("Response: ", response.toString());
                Gson gson = new Gson();
                Type type = new TypeToken<List<CategoryBean>>() {
                }.getType();
                List<SubCategoryBean> subCategoryBeanList = gson.fromJson(response.toString(), type);
                for (SubCategoryBean subCategoryBean : subCategoryBeanList) {
                    Subcategory subcategory = dbHelper.getSubCategoryById(Integer.parseInt(subCategoryBean.id));
                    if (subcategory.getId() == null) {
                        Subcategory subcategory1 = new Subcategory(Integer.parseInt(subCategoryBean.status), Integer.parseInt(subCategoryBean.id), subCategoryBean.name, subCategoryBean.description, Integer.parseInt(subCategoryBean.category_id));

                        dbHelper.createSubCategory(subcategory1);
                    }
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("Response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsObjRequest);

    }

    private void updateDBCategory() {
        dbHelper = new DBHelper(getApplicationContext());
        //  String url="http://10.44.22.99/bcsqs/api";


        String url = "" + Constant.IP_ADDRESS + "/bcsqs/api";
        Map<String, String> params = new HashMap<String, String>();
        params.put("methodType", "GET");
        params.put("actionTypp", "getallcategory");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                textView.setText(response.toString());
                Log.d("Response: ", response.toString());
                Gson gson = new Gson();
                Type type = new TypeToken<List<CategoryBean>>() {
                }.getType();
                List<CategoryBean> categoryBeanList = gson.fromJson(response.toString(), type);
                for (CategoryBean categoryBean : categoryBeanList) {
                    Category category = dbHelper.getCategoryById(Integer.parseInt(categoryBean.id));
                    if (category.getId() == null) {
                        Category category1 = new Category(categoryBean.name, categoryBean.description, Integer.parseInt(categoryBean.id), Integer.parseInt(categoryBean.status));
                        dbHelper.createCategory(category1);
                    }
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("Response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsObjRequest);

    }

    private void updateDBQuestion() {
        dbHelper = new DBHelper(getApplicationContext());


        String url = "" + Constant.IP_ADDRESS + "/bcsqs/api";
        Map<String, String> params = new HashMap<String, String>();
        params.put("methodType", "GET");
        params.put("actionTypp", "getAllQue");

        CustomRequest jsObjRequest = new CustomRequest(Request.Method.POST, url, params, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                textView.setText(response.toString());
                Log.d("Response: ", response.toString());
                Gson gson = new Gson();
                Type type = new TypeToken<List<QuestionBean>>() {
                }.getType();
                List<QuestionBean> contactList = gson.fromJson(response.toString(), type);
                for (QuestionBean questionBean : contactList) {
                    Question question = dbHelper.getQuestionById(Integer.parseInt(questionBean.id));
                    if (question.getId() == null) {
                        question.setSatus(Integer.parseInt(questionBean.status));
                        question.setIsMultipleAns(Integer.parseInt(questionBean.is_multiple_ans));
                        question.setSubCategoryId(Integer.parseInt(questionBean.sub_category_id));
                        question.setQuestion(questionBean.question);
                        question.setAnswer(questionBean.answer);
                        question.setDescription(questionBean.description);
                        question.setId(Integer.parseInt(questionBean.id));
                        dbHelper.createQuestion(question);
                    }
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError response) {
                Log.d("Response: ", response.toString());
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(jsObjRequest);

    }
}
