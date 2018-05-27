package com.location.rafiqul.bcsq;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.location.rafiqul.bcsq.dao.DBHelper;
import com.location.rafiqul.bcsq.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    DBHelper dbHelper;
    List<String>nameList=new ArrayList<String>();
    List<Integer>idList=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        dbHelper=new DBHelper(getApplicationContext());
        List<Category> categoryList=dbHelper.getAllCategory();
        for (Category category:categoryList){
            nameList.add(category.getName());
            idList.add(category.getId());
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.category_listview, nameList);

        ListView listView = (ListView) findViewById(R.id.categoryList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(CategoryActivity.this,SubCategoryActivity.class);
                intent.putExtra("categoryID",idList.get(position).toString());
                startActivity(intent);
               // Toast.makeText(getApplicationContext(),idList.get(position).toString(),Toast.LENGTH_LONG).show();
            }
        });
    }

}
