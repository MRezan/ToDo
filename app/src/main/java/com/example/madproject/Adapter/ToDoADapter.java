package com.example.madproject.Adapter;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.madproject.AddFragment;
import com.example.madproject.ListFragment;
import com.example.madproject.Model.ToDoModel;
import com.example.madproject.R;
import com.example.madproject.Utils.DatabaseHelper;

import java.util.List;

public class ToDoADapter extends RecyclerView.Adapter <ToDoADapter.MyViewHolder>{

    private List<ToDoModel> mList;
    public ListFragment listFragment;
    private DatabaseHelper myDb;

    public ToDoADapter(DatabaseHelper myDb, ListFragment listFragment)
    {
        this.listFragment =listFragment;
        this.myDb=myDb;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout,parent,false);
        return new MyViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
     final ToDoModel item= mList.get(position);
     holder.checkBox.setText(item.getTask());
     holder.checkBox.setChecked(toBoolean(item.getStatus()));
     holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
         @Override
         public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
            if(b)
            {
                myDb.updateStatus(item.getId(),1);

            }
            else
            {
                myDb.updateStatus(item.getId(),0);
            }

         }
     });
    }

    public boolean toBoolean(int num)
    {
        return num!=0;
    }

    public ListFragment getContext()
    {
        return listFragment;
    }

    public void setTask(List<ToDoModel> mList)
    {
        this.mList = mList;
        notifyDataSetChanged();
    }

//    public void deleteTask(int positon)
//    {
//        ToDoModel item = mList.get(positon);
//        myDb.deleteTask(item.getId());
//        mList.remove(positon);
//        notifyItemRemoved(positon);
//    }

//    public void editItem(int position)
//    {
//        ToDoModel item = mList.get(position);
//
//        Bundle bundle = new Bundle();
//        bundle.putInt("id",item.getId());
//        bundle.putString("task",item.getTask());
//
//        AddFragment task = new  AddFragment();
//        task.setArguments(bundle);
//
//
//
//    }






    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox =itemView.findViewById(R.id.checkBoxId);

        }
    }
}
