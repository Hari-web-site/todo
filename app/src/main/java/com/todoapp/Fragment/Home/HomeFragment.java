package com.todoapp.Fragment.Home;

import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
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
import com.todoapp.Fragment.Home.model.AddTodoModel;
import com.todoapp.Main.MainActivity;
import com.todoapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements Adpter_TodoList.TodoOnclickListener {

    RecyclerView recyclerView;
    View v;
    ArrayList<AddTodoModel> addTodoName;
    AppCompatImageView addTodo;
    AppCompatButton submitAddTodo;
    TextInputEditText edtAddTodo;
    DBHelper dbHelper;
    AppCompatTextView txt_n;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        config();
        findById();
//        if(addTodoName.size() >0){
            setAdapter(addTodoName);
//        }
//        else {
//            txt_n = v.findViewById(R.id.txt_n);
//            txt_n.setVisibility(View.VISIBLE);
//        }
        return v;
    }
    void config(){
        addTodoName = new ArrayList<>();
        dbHelper = new DBHelper(getContext());
        addTodoName =dbHelper.readTodoList();
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
                dbHelper.addTodoList(edtAddTodo.getText()+"");
                config();
                setAdapter(addTodoName);
                dialog.hide();
            }
        });
        dialog.show();
    }

    void setAdapter(ArrayList<AddTodoModel> addTodoName){
        Adpter_TodoList adpter_todoList = new Adpter_TodoList(getContext(),addTodoName,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adpter_todoList);
    }

    @Override
    public void TodoOnclick(int position) {
        Toast.makeText(getContext(), "position"+position, Toast.LENGTH_SHORT).show();
        addTodoName.remove(position);
        setAdapter(addTodoName);
    }
}