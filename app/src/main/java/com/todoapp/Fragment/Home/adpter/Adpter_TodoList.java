package com.todoapp.Fragment.Home.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.todoapp.Fragment.Home.model.AddTodoModel;
import com.todoapp.R;

import java.util.ArrayList;

public class Adpter_TodoList extends RecyclerView.Adapter<Adpter_TodoList.ViewHolder> {

    Context context;
    TodoOnclickListener todoOnclickListener;
    ArrayList<AddTodoModel> addTodoName;


    public Adpter_TodoList(Context context, ArrayList<AddTodoModel> addTodoName, TodoOnclickListener todoOnclickListener) {
        this.context = context;
        this.addTodoName = addTodoName;
        this.todoOnclickListener = todoOnclickListener;
    }

    @NonNull
    @Override
    public Adpter_TodoList.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_resource_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull Adpter_TodoList.ViewHolder holder, int position) {
        holder.tsk_name.setText(addTodoName.get(position).getTitle());
        holder.linearLayoutCompat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                todoOnclickListener.TodoOnclick(position);
                Toast.makeText(context, "click"+position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return addTodoName.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        AppCompatTextView tsk_name;
        LinearLayoutCompat linearLayoutCompat;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tsk_name = itemView.findViewById(R.id.task_name);
            linearLayoutCompat = itemView.findViewById(R.id.lnLayout);
        }
    }

    public interface  TodoOnclickListener{
        void TodoOnclick(int position);
    }

}
