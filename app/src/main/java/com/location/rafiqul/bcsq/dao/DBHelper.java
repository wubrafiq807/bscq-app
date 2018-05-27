package com.location.rafiqul.bcsq.dao;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.location.rafiqul.bcsq.model.Answer;
import com.location.rafiqul.bcsq.model.Category;
import com.location.rafiqul.bcsq.model.Question;
import com.location.rafiqul.bcsq.model.Subcategory;

public class DBHelper extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
   public static final String DATABASE_NAME = "BCS.db";
   public static final String CONTACTS_TABLE_NAME = "contacts";
   public static final String CATEGORY_TABLE_NAME = "category";
   public static final String SUBCATEGORY_TABLE_NAME = "subcategory";
   public static final String QUESTION_TABLE_NAME = "question";
   public static final String ANSWER_TABLE_NAME = "answer";
   //CONTACT TABLE COLUMN
   public static final String CONTACTS_COLUMN_ID = "id";
   public static final String CONTACTS_COLUMN_NAME = "name";
   public static final String CONTACTS_COLUMN_EMAIL = "email";
   public static final String CONTACTS_COLUMN_STREET = "street";
   public static final String CONTACTS_COLUMN_CITY = "place";
   public static final String CONTACTS_COLUMN_PHONE = "phone";
   //CATEGORY TABLE COLUMN
   public static final String CATEGORY_COLUMN_ID_AI = "id";
   public static final String CATEGORY_COLUMN_ID = "categoryid";
   public static final String CATEGORY_COLUMN_NAME = "name";
   public static final String CATEGORY_COLUMN_DESCRIPTION = "description";
   public static final String CATEGORY_COLUMN_STATUS = "status";
   //SUBCATEGORY TABLE COLUMN
   public static final String SUBCATEGORY_COLUMN_ID_AI = "id";
   public static final String SUBCATEGORY_COLUMN_CATEGORYID = "categoryid";
   public static final String SUBCATEGORY_COLUMN_SUBCATEGORYID = "subcategoryid";
   public static final String SUBCATEGORY_COLUMN_NAME = "name";
   public static final String SUBCATEGORY_COLUMN_DESCRIPTION = "description";
   public static final String SUBCATEGORY_COLUMN_STATUS = "status";

   //QUESTION TABLE COLUMN
   public static final String QUESTION_COLUMN_ID_AI = "id";
   public static final String QUESTION_COLUMN_QUESTIONID = "questionid";
   public static final String QUESTION_COLUMN_SUBCATEGORYID = "subcategoryid";
   public static final String QUESTION_COLUMN_QUESTION = "question";
   public static final String QUESTION_COLUMN_ANSWER = "answer";
   public static final String QUESTION_COLUMN_DESCRIPTION = "description";
   public static final String QUESTION_COLUMN_IS_MULTIPLEANS = "isMultipleAns";
   public static final String QUESTION_COLUMN_STATUS = "status";
   //ANSWER TABLE COLUMN
   public static final String ANSWER_COLUMN_ID_AI = "id";
   public static final String ANSWER_COLUMN_QUESTIONID = "questionid";
   public static final String ANSWER_COLUMN_ANSWERID = "inswerid";
   public static final String ANSWER_COLUMN_ANSWER1 = "answer1";
   public static final String ANSWER_COLUMN_ANSWER2 = "answer2";
   public static final String ANSWER_COLUMN_ANSWER3 = "answer3";
   public static final String ANSWER_COLUMN_ANSWER4 = "answer4";
   public static final String ANSWER_COLUMN_CUR_ANS = "curAnswer";


   private HashMap hp;

   public DBHelper(Context context) {
      super(context, DATABASE_NAME, null, DATABASE_VERSION);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {

      db.execSQL(
              "create table "+CATEGORY_TABLE_NAME+" " +
                      "(id integer primary key autoincrement, "+CATEGORY_COLUMN_ID+" integer,"+CATEGORY_COLUMN_NAME+" text,"+CATEGORY_COLUMN_DESCRIPTION+" text, "+CATEGORY_COLUMN_STATUS+" integer)"
      );
      db.execSQL(
              "create table "+SUBCATEGORY_TABLE_NAME+" " +
                      "(id integer primary key autoincrement,"+SUBCATEGORY_COLUMN_CATEGORYID+" integer, "+SUBCATEGORY_COLUMN_SUBCATEGORYID+" integer,"+SUBCATEGORY_COLUMN_NAME+" text,"+SUBCATEGORY_COLUMN_DESCRIPTION+" text, "+SUBCATEGORY_COLUMN_STATUS+" integer)"
      );
      db.execSQL(
              "create table "+QUESTION_TABLE_NAME+" " +
                      "(id integer primary key autoincrement,"+QUESTION_COLUMN_QUESTIONID+" integer, "+QUESTION_COLUMN_SUBCATEGORYID+" integer,"+QUESTION_COLUMN_QUESTION+" text,"+QUESTION_COLUMN_DESCRIPTION+" text,"+QUESTION_COLUMN_ANSWER+" text, "+QUESTION_COLUMN_STATUS+" integer,"+QUESTION_COLUMN_IS_MULTIPLEANS+" integer)"
      );
      db.execSQL(
              "create table "+ANSWER_TABLE_NAME+" " +
                      "(id integer primary key autoincrement,"+ANSWER_COLUMN_ANSWERID+" integer, "+ANSWER_COLUMN_QUESTIONID+" integer,"+ANSWER_COLUMN_ANSWER1+" text,"+ANSWER_COLUMN_ANSWER2+" text,"+ANSWER_COLUMN_ANSWER3+" text,"+ANSWER_COLUMN_ANSWER4+" text, "+ANSWER_COLUMN_CUR_ANS+" integer)"
      );
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub
      //db.execSQL("DROP TABLE IF EXISTS contacts");
      db.execSQL("DROP TABLE IF EXISTS "+CATEGORY_TABLE_NAME);
      db.execSQL("DROP TABLE IF EXISTS "+SUBCATEGORY_TABLE_NAME);
      db.execSQL("DROP TABLE IF EXISTS "+QUESTION_TABLE_NAME);
      db.execSQL("DROP TABLE IF EXISTS "+ANSWER_TABLE_NAME);
      onCreate(db);
   }
    public long createCategory(Category category) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CATEGORY_COLUMN_ID,category.getId());
        values.put(CATEGORY_COLUMN_NAME,category.getName());
        values.put(CATEGORY_COLUMN_DESCRIPTION,category.getDescription());
        values.put(CATEGORY_COLUMN_STATUS,category.getSatus());


        // insert row
        long todo_id = db.insert(CATEGORY_TABLE_NAME, null, values);


        return todo_id;
    }

    public long createSubCategory(Subcategory subcategory) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
       values.put(SUBCATEGORY_COLUMN_CATEGORYID,subcategory.getCategoryId());
       values.put(SUBCATEGORY_COLUMN_SUBCATEGORYID,subcategory.getId());
       values.put(SUBCATEGORY_COLUMN_NAME,subcategory.getName());
       values.put(SUBCATEGORY_COLUMN_DESCRIPTION,subcategory.getDescription());
       values.put(SUBCATEGORY_COLUMN_STATUS,subcategory.getSatus());


        // insert row
        long todo_id = db.insert(SUBCATEGORY_TABLE_NAME, null, values);


        return todo_id;
    }
    public long createQuestion(Question question) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(QUESTION_COLUMN_QUESTIONID,question.getId());
        values.put(QUESTION_COLUMN_SUBCATEGORYID,question.getSubCategoryId());
        values.put(QUESTION_COLUMN_QUESTION,question.getQuestion());
        values.put(QUESTION_COLUMN_ANSWER,question.getAnswer());
        values.put(QUESTION_COLUMN_DESCRIPTION,question.getDescription());
        values.put(QUESTION_COLUMN_IS_MULTIPLEANS,question.getIsMultipleAns());
        values.put(QUESTION_COLUMN_STATUS,question.getSatus());



        // insert row
        long todo_id = db.insert(QUESTION_TABLE_NAME, null, values);


        return todo_id;
    }

    public long createAnswer(Answer answer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ANSWER_COLUMN_QUESTIONID,answer.getQuestionId());
        values.put(ANSWER_COLUMN_ANSWERID,answer.getId());
        values.put(ANSWER_COLUMN_ANSWER1,answer.getAnswer1());
        values.put(ANSWER_COLUMN_ANSWER2,answer.getAnswer2());
        values.put(ANSWER_COLUMN_ANSWER3,answer.getAnswer3());
        values.put(ANSWER_COLUMN_ANSWER4,answer.getAnswer4());
        values.put(ANSWER_COLUMN_CUR_ANS,answer.getCurAnswer());




        // insert row
        long todo_id = db.insert(ANSWER_TABLE_NAME, null, values);


        return todo_id;
    }


   public boolean insertContact(String name, String phone, String email, String street, String place) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("name", name);
      contentValues.put("phone", phone);
      contentValues.put("email", email);
      contentValues.put("street", street);
      contentValues.put("place", place);
      db.insert("contacts", null, contentValues);
      return true;
   }

   public Cursor getData(String tablename,String coulnName,String id) {
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res = db.rawQuery("select * from "+tablename+" where "+coulnName+"='" + id + "'", null);
      return res;
   }
    public Category getCategoryById(int todo_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + CATEGORY_TABLE_NAME + " WHERE "
                + CATEGORY_COLUMN_ID + " = " + todo_id;

        Log.d("Response: ", selectQuery.toString());

        Cursor c = db.rawQuery(selectQuery, null);
        Category category = new Category();
        if(c != null && c.moveToFirst()) {



            category.setName(c.getString(c.getColumnIndex(CATEGORY_COLUMN_NAME)));
            category.setDescription(c.getString(c.getColumnIndex(CATEGORY_COLUMN_DESCRIPTION)));
            category.setId(c.getInt(c.getColumnIndex(CATEGORY_COLUMN_ID)));
            category.setSatus(c.getInt(c.getColumnIndex(CATEGORY_COLUMN_STATUS)));
        }
        return category;
    }

    public Subcategory getSubCategoryById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + SUBCATEGORY_TABLE_NAME + " WHERE "
                + SUBCATEGORY_COLUMN_SUBCATEGORYID + " = " + id;

        Log.d("Response: ", selectQuery.toString());

        Cursor c = db.rawQuery(selectQuery, null);
        Subcategory subcategory = new Subcategory();
        if(c != null && c.moveToFirst()) {
            subcategory.setCategoryId(c.getInt(c.getColumnIndex(SUBCATEGORY_COLUMN_CATEGORYID)));
            subcategory.setId(c.getInt(c.getColumnIndex(SUBCATEGORY_COLUMN_SUBCATEGORYID)));
            subcategory.setDescription(c.getString(c.getColumnIndex(SUBCATEGORY_COLUMN_DESCRIPTION)));
            subcategory.setName(c.getString(c.getColumnIndex(SUBCATEGORY_COLUMN_NAME)));
            subcategory.setSatus(c.getInt(c.getColumnIndex(SUBCATEGORY_COLUMN_STATUS)));
        }
        return subcategory;
    }

    public Question getQuestionById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + QUESTION_TABLE_NAME + " WHERE "
                + QUESTION_COLUMN_QUESTIONID + " = " + id;

        Log.d("Response: ", selectQuery.toString());

        Cursor c = db.rawQuery(selectQuery, null);
        Question question = new Question();
        if(c != null && c.moveToFirst()) {

            question.setId(c.getInt(c.getColumnIndex(QUESTION_COLUMN_QUESTIONID)));
            question.setSubCategoryId(c.getInt(c.getColumnIndex(QUESTION_COLUMN_SUBCATEGORYID)));
            question.setQuestion(c.getString(c.getColumnIndex(QUESTION_COLUMN_QUESTION)));
            question.setAnswer(c.getString(c.getColumnIndex(QUESTION_COLUMN_ANSWER)));
            question.setDescription(c.getString(c.getColumnIndex(QUESTION_COLUMN_DESCRIPTION)));
            question.setIsMultipleAns(c.getInt(c.getColumnIndex(QUESTION_COLUMN_IS_MULTIPLEANS)));
            question.setSatus(c.getInt(c.getColumnIndex(QUESTION_COLUMN_STATUS)));

        }
        return question;
    }

    public Answer getAnswerById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + ANSWER_TABLE_NAME + " WHERE "
                + ANSWER_COLUMN_ANSWERID + " = " + id;

        Log.d("Response: ", selectQuery.toString());

        Cursor c = db.rawQuery(selectQuery, null);

        Answer answer = new Answer();
        if(c != null && c.moveToFirst()) {
            answer.setQuestionId(c.getInt(c.getColumnIndex(ANSWER_COLUMN_QUESTIONID)));
            answer.setId(c.getInt(c.getColumnIndex(ANSWER_COLUMN_ANSWERID)));
            answer.setAnswer1(c.getString(c.getColumnIndex(ANSWER_COLUMN_ANSWER1)));
            answer.setAnswer2(c.getString(c.getColumnIndex(ANSWER_COLUMN_ANSWER2)));
            answer.setAnswer3(c.getString(c.getColumnIndex(ANSWER_COLUMN_ANSWER3)));
            answer.setAnswer4(c.getString(c.getColumnIndex(ANSWER_COLUMN_ANSWER4)));
            answer.setCurAnswer(c.getInt(c.getColumnIndex(ANSWER_COLUMN_CUR_ANS)));

        }



        return answer;
    }




   public ArrayList<Category> getAllCategory() {
      ArrayList<Category> array_list = new ArrayList<Category>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor c = db.rawQuery("select * from " + CATEGORY_TABLE_NAME + "", null);
       if (c.moveToFirst()) {
           do {
               Category category = new Category();
               category.setName(c.getString(c.getColumnIndex(CATEGORY_COLUMN_NAME)));
               category.setDescription(c.getString(c.getColumnIndex(CATEGORY_COLUMN_DESCRIPTION)));
               category.setId(c.getInt(c.getColumnIndex(CATEGORY_COLUMN_ID)));
               category.setSatus(c.getInt(c.getColumnIndex(CATEGORY_COLUMN_STATUS)));
               array_list.add(category);
           } while (c.moveToNext());
       }
      return array_list;
   }

   public ArrayList<Subcategory> getAllSubCategory() {
      ArrayList<Subcategory> array_list = new ArrayList<Subcategory>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor c = db.rawQuery("select * from " + SUBCATEGORY_TABLE_NAME + "", null);
       if (c.moveToFirst()) {
           do {
               Subcategory subcategory = new Subcategory();
             subcategory.setCategoryId(c.getInt(c.getColumnIndex(SUBCATEGORY_COLUMN_CATEGORYID)));
             subcategory.setId(c.getInt(c.getColumnIndex(SUBCATEGORY_COLUMN_SUBCATEGORYID)));
             subcategory.setDescription(c.getString(c.getColumnIndex(SUBCATEGORY_COLUMN_DESCRIPTION)));
             subcategory.setName(c.getString(c.getColumnIndex(SUBCATEGORY_COLUMN_NAME)));
             subcategory.setSatus(c.getInt(c.getColumnIndex(SUBCATEGORY_COLUMN_STATUS)));
               array_list.add(subcategory);
           } while (c.moveToNext());
       }
      return array_list;
   }
    public ArrayList<Subcategory> getAllSubCategoryOneColumn(String columnName,String columnvalue) {
        ArrayList<Subcategory> array_list = new ArrayList<Subcategory>();

        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("select * from " + SUBCATEGORY_TABLE_NAME + " where "+columnName+"='"+columnvalue+"'", null);
        if (c.moveToFirst()) {
            do {
                Subcategory subcategory = new Subcategory();
                subcategory.setCategoryId(c.getInt(c.getColumnIndex(SUBCATEGORY_COLUMN_CATEGORYID)));
                subcategory.setId(c.getInt(c.getColumnIndex(SUBCATEGORY_COLUMN_SUBCATEGORYID)));
                subcategory.setDescription(c.getString(c.getColumnIndex(SUBCATEGORY_COLUMN_DESCRIPTION)));
                subcategory.setName(c.getString(c.getColumnIndex(SUBCATEGORY_COLUMN_NAME)));
                subcategory.setSatus(c.getInt(c.getColumnIndex(SUBCATEGORY_COLUMN_STATUS)));
                array_list.add(subcategory);
            } while (c.moveToNext());
        }
        return array_list;
    }
   public ArrayList<Question> getAllQuestion() {
      ArrayList<Question> array_list = new ArrayList<Question>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor c = db.rawQuery("select * from " + QUESTION_TABLE_NAME + "", null);
       if (c.moveToFirst()) {
           do {
               Question question = new Question();
             question.setId(c.getInt(c.getColumnIndex(QUESTION_COLUMN_QUESTIONID)));
             question.setSubCategoryId(c.getInt(c.getColumnIndex(QUESTION_COLUMN_SUBCATEGORYID)));
             question.setQuestion(c.getString(c.getColumnIndex(QUESTION_COLUMN_QUESTION)));
             question.setAnswer(c.getString(c.getColumnIndex(QUESTION_COLUMN_ANSWER)));
             question.setDescription(c.getString(c.getColumnIndex(QUESTION_COLUMN_DESCRIPTION)));
             question.setIsMultipleAns(c.getInt(c.getColumnIndex(QUESTION_COLUMN_IS_MULTIPLEANS)));
             question.setSatus(c.getInt(c.getColumnIndex(QUESTION_COLUMN_STATUS)));
             array_list.add(question);
           } while (c.moveToNext());
       }
      return array_list;
   }

   public ArrayList<Question> getAllQuestionByColumnName(String columnName, String columnValue) {
      ArrayList<Question> array_list = new ArrayList<Question>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor c = db.rawQuery("select * from " + QUESTION_TABLE_NAME + " where " + columnName + "=" + columnValue + "", null);
       if (c.moveToFirst()) {
           do {
               Question question = new Question();
               question.setId(c.getInt(c.getColumnIndex(QUESTION_COLUMN_QUESTIONID)));
               question.setSubCategoryId(c.getInt(c.getColumnIndex(QUESTION_COLUMN_SUBCATEGORYID)));
               question.setQuestion(c.getString(c.getColumnIndex(QUESTION_COLUMN_QUESTION)));
               question.setAnswer(c.getString(c.getColumnIndex(QUESTION_COLUMN_ANSWER)));
               question.setDescription(c.getString(c.getColumnIndex(QUESTION_COLUMN_DESCRIPTION)));
               question.setIsMultipleAns(c.getInt(c.getColumnIndex(QUESTION_COLUMN_IS_MULTIPLEANS)));
               question.setSatus(c.getInt(c.getColumnIndex(QUESTION_COLUMN_STATUS)));
               array_list.add(question);
           } while (c.moveToNext());
       }

      return array_list;
   }

   public ArrayList<Answer> getAllAnswer() {
      ArrayList<Answer> array_list = new ArrayList<Answer>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor c = db.rawQuery("select * from " + ANSWER_TABLE_NAME + "", null);

       if (c.moveToFirst()) {
           do {
               Answer answer = new Answer();
               answer.setQuestionId(c.getInt(c.getColumnIndex(ANSWER_COLUMN_QUESTIONID)));
               answer.setId(c.getInt(c.getColumnIndex(ANSWER_COLUMN_ANSWERID)));
               answer.setAnswer1(c.getString(c.getColumnIndex(ANSWER_COLUMN_ANSWER1)));
               answer.setAnswer2(c.getString(c.getColumnIndex(ANSWER_COLUMN_ANSWER2)));
               answer.setAnswer3(c.getString(c.getColumnIndex(ANSWER_COLUMN_ANSWER3)));
               answer.setAnswer4(c.getString(c.getColumnIndex(ANSWER_COLUMN_ANSWER4)));
               answer.setCurAnswer(c.getInt(c.getColumnIndex(ANSWER_COLUMN_CUR_ANS)));
               array_list.add(answer);

           } while (c.moveToNext());
       }

      return array_list;
   }

   public void delete(String tableName, String cloumnName, String columnValue) {
      SQLiteDatabase db = this.getReadableDatabase();
      db.execSQL("delete from " + tableName + " where " + cloumnName + "='" + columnValue + "'");
   }

}