package com.location.rafiqul.bcsq;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.location.rafiqul.bcsq.dao.DBHelper;
import com.location.rafiqul.bcsq.model.Subcategory;

import java.util.ArrayList;
import java.util.List;

public class SubCategoryActivity extends AppCompatActivity {

    DBHelper dbHelper;
    List<String>nameList=new ArrayList<String>();
    List<Integer>idList=new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);
        dbHelper=new DBHelper(getApplicationContext());
        List<Subcategory> subcategoryList=dbHelper.getAllSubCategoryOneColumn(DBHelper.SUBCATEGORY_COLUMN_CATEGORYID,getIntent().getStringExtra("categoryId").toString());

        for (Subcategory subcategory:subcategoryList){
            nameList.add(subcategory.getName());
            idList.add(subcategory.getId());
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                R.layout.subcategory_listview, nameList);

        ListView listView = (ListView) findViewById(R.id.subCategoryList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(SubCategoryActivity.this,DetailsActivity.class);
                startActivity(intent);
            }
        });
    }

}
