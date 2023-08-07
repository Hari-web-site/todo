package com.todoapp.Fragment.Home;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.todoapp.DBHelper;
import com.todoapp.Fragment.Home.adpter.Adpter_TodoList;
import com.todoapp.Main.MainActivity;
import com.todoapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements Adpter_TodoList.TodoOnclickListener {

    RecyclerView recyclerView;
    View v;
    ArrayList<String> tsk_name;
    AppCompatImageView addTodo;
    AppCompatButton submitAddTodo;
    TextInputEditText edtAddTodo;
    DBHelper dbHelper;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        tsk_name = new ArrayList<>();
        for(int i=0;i<2;i++){
            tsk_name.add("Task "+i);
        }
        findById();
        setAdapter(tsk_name);

        return v;
    }

    void findById(){
        recyclerView = v.findViewById(R.id.recycleView);
        addTodo = v.findViewById(R.id.addTodo);

        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                opnDialog();
            }
        });
    }

    void opnDialog(){
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.add_todo_list);
        submitAddTodo = dialog.findViewById(R.id.submitAddTodo);
        edtAddTodo = dialog.findViewById(R.id.edtAddTodo);
        submitAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper = new DBHelper(getContext());
                dbHelper.addTodoList(edtAddTodo.getText()+"");
                dialog.hide();
            }
        });
        dialog.show();
    }

    void setAdapter(ArrayList<String> tsk_name){
        Adpter_TodoList adpter_todoList = new Adpter_TodoList(getContext(),tsk_name,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adpter_todoList);
    }

    @Override
    public void TodoOnclick(int position) {
        Toast.makeText(getContext(), "position"+position, Toast.LENGTH_SHORT).show();
        tsk_name.remove(position);
        setAdapter(tsk_name);
    }
}