package com.location.rafiqul.bcsq.dao;
import java.util.ArrayList;
import java.util.HashMap;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;

import com.location.rafiqul.bcsq.model.Answer;
import com.location.rafiqul.bcsq.model.Category;
import com.location.rafiqul.bcsq.model.Question;
import com.location.rafiqul.bcsq.model.Subcategory;

public class DBHelper extends SQLiteOpenHelper {

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
      super(context, DATABASE_NAME, null, 1);
   }

   @Override
   public void onCreate(SQLiteDatabase db) {
      // TODO Auto-generated method stub
      db.execSQL(
              "create table contacts " +
                      "(id integer primary key, name text,phone text,email text, street text,place text)"
      );
      db.execSQL(
              "create table "+CATEGORY_TABLE_NAME+" " +
                      "(id integer primary key autoincrement, categoryid integer,name text,description text, status integer)"
      );
      db.execSQL(
              "create table "+SUBCATEGORY_TABLE_NAME+" " +
                      "(id integer primary key autoincrement,categoryid integer, subcategoryid integer,name text,description text, status integer)"
      );
      db.execSQL(
              "create table "+QUESTION_TABLE_NAME+" " +
                      "(id integer primary key autoincrement,questionid integer, subcategoryid integer,question text,description text,answer text, status integer,isMultipleAns integer)"
      );
      db.execSQL(
              "create table "+ANSWER_TABLE_NAME+" " +
                      "(id integer primary key autoincrement,inswerid integer, questionid integer,answer1 text,answer2 text,answer3 text,answer4 text, curAnswer integer)"
      );
   }

   @Override
   public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      // TODO Auto-generated method stub
      db.execSQL("DROP TABLE IF EXISTS contacts");
      db.execSQL("DROP TABLE IF EXISTS category");
      db.execSQL("DROP TABLE IF EXISTS subcategory");
      db.execSQL("DROP TABLE IF EXISTS question");
      db.execSQL("DROP TABLE IF EXISTS answer");
      onCreate(db);
   }

   public boolean insertCategory(Integer categoryid, String name, String description, int status) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(CATEGORY_COLUMN_ID, categoryid);
      contentValues.put(CATEGORY_COLUMN_NAME, name);
      contentValues.put(CATEGORY_COLUMN_DESCRIPTION, description);
      contentValues.put(CATEGORY_COLUMN_STATUS, status);

      db.insert(CATEGORY_TABLE_NAME, null, contentValues);
      return true;
   }

   public boolean insertSubCategory(Integer categoryid, Integer subcategoryId, String name, String description, int status) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(SUBCATEGORY_COLUMN_CATEGORYID, categoryid);
      contentValues.put(SUBCATEGORY_COLUMN_SUBCATEGORYID, subcategoryId);
      contentValues.put(SUBCATEGORY_COLUMN_NAME, name);
      contentValues.put(SUBCATEGORY_COLUMN_DESCRIPTION, description);
      contentValues.put(SUBCATEGORY_COLUMN_STATUS, status);
      db.insert(SUBCATEGORY_TABLE_NAME, null, contentValues);
      return true;
   }

   public boolean insertQuestion(Integer questionid, Integer subcategoryId, String question, String description, String answer, Integer isMultiple, Integer status) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(QUESTION_COLUMN_QUESTIONID, questionid);
      contentValues.put(QUESTION_COLUMN_QUESTION, question);
      contentValues.put(QUESTION_COLUMN_DESCRIPTION, description);
      contentValues.put(QUESTION_COLUMN_SUBCATEGORYID, subcategoryId);
      contentValues.put(QUESTION_COLUMN_ANSWER, answer);
      contentValues.put(QUESTION_COLUMN_IS_MULTIPLEANS, isMultiple);
      contentValues.put(QUESTION_COLUMN_STATUS, status);
    long i=  db.insert(QUESTION_TABLE_NAME, null, contentValues);
    if((int)i>0){
       return true;
    }else{
       return  false;
    }

   }

   public boolean insertMultipleAnswer(Integer questionid, Integer answerid, String answer1, String answer2, String answer3, String answer4, Integer curAnswer) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put(ANSWER_COLUMN_QUESTIONID, questionid);
      contentValues.put(ANSWER_COLUMN_ANSWERID, answerid);
      contentValues.put(ANSWER_COLUMN_ANSWER1, answer1);
      contentValues.put(ANSWER_COLUMN_ANSWER2, answer2);
      contentValues.put(ANSWER_COLUMN_ANSWER3, answer3);
      contentValues.put(ANSWER_COLUMN_ANSWER4, answer4);
      contentValues.put(ANSWER_COLUMN_CUR_ANS, curAnswer);
      db.insert(ANSWER_TABLE_NAME.trim(), null, contentValues);
      return true;
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

   public int numberOfRows() {
      SQLiteDatabase db = this.getReadableDatabase();
      int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
      return numRows;
   }

   public boolean updateContact(Integer id, String name, String phone, String email, String street, String place) {
      SQLiteDatabase db = this.getWritableDatabase();
      ContentValues contentValues = new ContentValues();
      contentValues.put("name", name);
      contentValues.put("phone", phone);
      contentValues.put("email", email);
      contentValues.put("street", street);
      contentValues.put("place", place);
      db.update("contacts", contentValues, "id = ? ", new String[]{Integer.toString(id)});
      return true;
   }

   public Integer deleteContact(Integer id) {
      SQLiteDatabase db = this.getWritableDatabase();
      return db.delete("contacts",
              "id = ? ",
              new String[]{Integer.toString(id)});
   }

   public ArrayList<String> getAllCotacts() {
      ArrayList<String> array_list = new ArrayList<String>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res = db.rawQuery("select * from contacts", null);
      res.moveToFirst();

      while (res.isAfterLast() == false) {
         array_list.add(res.getString(res.getColumnIndex(CONTACTS_COLUMN_NAME)));
         res.moveToNext();
      }
      return array_list;
   }

   public ArrayList<Category> getAllCategory() {
      ArrayList<Category> array_list = new ArrayList<Category>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res = db.rawQuery("select * from " + CATEGORY_TABLE_NAME + "", null);
      res.moveToFirst();

      while (res.isAfterLast() == false) {
         Category category = new Category();
         category.setDescription(res.getString(res.getColumnIndex(CATEGORY_COLUMN_DESCRIPTION)));
         category.setName(res.getString(res.getColumnIndex(CATEGORY_COLUMN_NAME)));
         category.setSatus(res.getInt(res.getColumnIndex(CATEGORY_COLUMN_STATUS)));
         category.setId(res.getInt(res.getColumnIndex(CATEGORY_COLUMN_ID)));
         array_list.add(category);

         res.moveToNext();
      }
      return array_list;
   }

   public ArrayList<Subcategory> getAllSubCategory() {
      ArrayList<Subcategory> array_list = new ArrayList<Subcategory>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res = db.rawQuery("select * from " + SUBCATEGORY_TABLE_NAME + "", null);
      res.moveToFirst();

      while (res.isAfterLast() == false) {
         Subcategory subcategory = new Subcategory();
         subcategory.setDescription(res.getString(res.getColumnIndex(SUBCATEGORY_COLUMN_DESCRIPTION)));
         subcategory.setName(res.getString(res.getColumnIndex(SUBCATEGORY_COLUMN_NAME)));
         subcategory.setId(res.getInt(res.getColumnIndex(SUBCATEGORY_COLUMN_SUBCATEGORYID)));
         subcategory.setCategoryId(res.getInt(res.getColumnIndex(SUBCATEGORY_COLUMN_CATEGORYID)));
         subcategory.setSatus(res.getInt(res.getColumnIndex(SUBCATEGORY_COLUMN_STATUS)));

         array_list.add(subcategory);

         res.moveToNext();
      }
      return array_list;
   }

   public ArrayList<Question> getAllQuestion() {
      ArrayList<Question> array_list = new ArrayList<Question>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res = db.rawQuery("select * from " + QUESTION_TABLE_NAME + "", null);
      res.moveToFirst();

      while (res.isAfterLast() == false) {
         Question question = new Question();
         question.setId(res.getInt(res.getColumnIndex(QUESTION_COLUMN_QUESTIONID)));
         question.setQuestion(res.getString(res.getColumnIndex(QUESTION_COLUMN_QUESTION)));
         question.setDescription(res.getString(res.getColumnIndex(QUESTION_COLUMN_DESCRIPTION)));
         question.setSubCategoryId(res.getInt(res.getColumnIndex(QUESTION_COLUMN_SUBCATEGORYID)));
         question.setIsMultipleAns(res.getInt(res.getColumnIndex(QUESTION_COLUMN_IS_MULTIPLEANS)));
         question.setSatus(res.getInt(res.getColumnIndex(QUESTION_COLUMN_STATUS)));

         array_list.add(question);

         res.moveToNext();
      }
      return array_list;
   }

   public ArrayList<Question> getAllQuestionByColumnName(String columnName, String columnValue) {
      ArrayList<Question> array_list = new ArrayList<Question>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res = db.rawQuery("select * from " + QUESTION_TABLE_NAME + " where " + columnName + "=" + columnValue + "", null);
      res.moveToFirst();

      while (res.isAfterLast() == false) {
         Question question = new Question();
         question.setId(res.getInt(res.getColumnIndex(QUESTION_COLUMN_QUESTIONID)));
         question.setQuestion(res.getString(res.getColumnIndex(QUESTION_COLUMN_QUESTION)));
         question.setDescription(res.getString(res.getColumnIndex(QUESTION_COLUMN_DESCRIPTION)));
         question.setSubCategoryId(res.getInt(res.getColumnIndex(QUESTION_COLUMN_SUBCATEGORYID)));
         question.setIsMultipleAns(res.getInt(res.getColumnIndex(QUESTION_COLUMN_IS_MULTIPLEANS)));
         question.setSatus(res.getInt(res.getColumnIndex(QUESTION_COLUMN_STATUS)));

         array_list.add(question);

         res.moveToNext();
      }
      return array_list;
   }

   public ArrayList<Answer> getAllAnswer() {
      ArrayList<Answer> array_list = new ArrayList<Answer>();

      //hp = new HashMap();
      SQLiteDatabase db = this.getReadableDatabase();
      Cursor res = db.rawQuery("select * from " + ANSWER_TABLE_NAME + "", null);
      res.moveToFirst();

      while (res.isAfterLast() == false) {
         Answer answer = new Answer();
         answer.setId(res.getInt(res.getColumnIndex(ANSWER_COLUMN_ANSWERID)));
         answer.setQuestionId(res.getInt(res.getColumnIndex(ANSWER_COLUMN_QUESTIONID)));
         answer.setAnswer1(res.getString(res.getColumnIndex(ANSWER_COLUMN_ANSWER1)));
         answer.setAnswer2(res.getString(res.getColumnIndex(ANSWER_COLUMN_ANSWER2)));
         answer.setAnswer3(res.getString(res.getColumnIndex(ANSWER_COLUMN_ANSWER3)));
         answer.setAnswer4(res.getString(res.getColumnIndex(ANSWER_COLUMN_ANSWER4)));
         answer.setCurAnswer(res.getInt(res.getColumnIndex(ANSWER_COLUMN_CUR_ANS)));

         array_list.add(answer);

         res.moveToNext();
      }
      return array_list;
   }

   public void delete(String tableName, String cloumnName, String columnValue) {
      SQLiteDatabase db = this.getReadableDatabase();
      db.execSQL("delete from " + tableName + " where " + cloumnName + "='" + columnValue + "'");
   }

}