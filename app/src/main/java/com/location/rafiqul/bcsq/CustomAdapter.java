package com.location.rafiqul.bcsq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.location.rafiqul.bcsq.model.Question;

import java.util.ArrayList;


/**
 * Created by anupamchugh on 09/02/16.
 */
public class CustomAdapter extends ArrayAdapter<Question> implements View.OnClickListener{

    private ArrayList<Question> dataSet;
    Context mContext;

    // View lookup cache
    private static class ViewHolder {
        TextView question;
        TextView answer;

    }



    public CustomAdapter(ArrayList<Question> data, Context context) {
        super(context, R.layout.single_items_row, data);
        this.dataSet = data;
        this.mContext=context;

    }


    @Override
    public void onClick(View v) {


        int position=(Integer) v.getTag();
        Object object= getItem(position);
        Question dataModel=(Question) object;

        switch (v.getId())
        {


        }


    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Question dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {


            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.single_items_row, parent, false);
            viewHolder.question = (TextView) convertView.findViewById(R.id.name);
            viewHolder.answer = (TextView) convertView.findViewById(R.id.type);
            /*viewHolder.txtVersion = (TextView) convertView.findViewById(R.id.version_number);
            viewHolder.info = (ImageView) convertView.findViewById(R.id.item_info);*/

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;


        viewHolder.question.setText(dataModel.getQuestion());
        viewHolder.answer.setText(dataModel.getAnswer());
       /* viewHolder.txtVersion.setText(dataModel.getVersion_number());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);*/
        // Return the completed view to render on screen
        return convertView;
    }


}
