package com.location.rafiqul.bcsq;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.location.rafiqul.bcsq.dao.DBHelper;
import com.location.rafiqul.bcsq.model.Question;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SingleAnsFragment extends Fragment {
    ArrayList<Question> dataModels;
    ListView listView;
    private static CustomAdapter adapter;
    DBHelper dbHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        final View view = inflater.inflate(R.layout.fragment_single_ans, container, false);
        listView=(ListView)view.findViewById(R.id.list);
        dataModels= new ArrayList<>();
        List<Question> questionList=this.dbHelper.getAllQuestion();

        for(Question question :questionList){
            if (question.getIsMultipleAns()==0){
                dataModels.add(question);
            }
        }

        adapter= new CustomAdapter(dataModels,getActivity());
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_single_ans, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_call) {
            Toast.makeText(getActivity(), "Clicked on " + item.getTitle(), Toast.LENGTH_SHORT)
                    .show();
        }
        return true;
    }
}
