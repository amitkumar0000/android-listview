package com.android.listview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.listview.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ArrayAdapterClass extends ArrayAdapter<String> {

    List<String> list;
    Context context;
    TextView textView;

    public ArrayAdapterClass(@NonNull Context context, int resource,int id, List<String> list) {
        super(context, resource);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.listrow,parent,false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(list.get(position));
        return convertView;
    }

    @Override
    public String getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public void notifyDataSet(ArrayList<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    class ViewHolder{
        TextView textView;
        ViewHolder(View view){
            textView = view.findViewById(R.id.listText);
        }
    }
}
