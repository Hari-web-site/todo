package com.todoapp.Fragment.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.todoapp.Fragment.Home.adpter.Adpter_TodoList;
import com.todoapp.Main.MainActivity;
import com.todoapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    View v;
    ArrayList<String> tsk_name;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_home, container, false);

        findById();
        setAdapter();

        return v;
    }

    void findById(){
        recyclerView = v.findViewById(R.id.recycleView);
    }

    void setAdapter(){
        tsk_name = new ArrayList<>();
        for(int i=0;i<10;i++){
            tsk_name.add("task"+i);
        }

        Adpter_TodoList adpter_todoList = new Adpter_TodoList(getContext(),tsk_name);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adpter_todoList);
    }
}