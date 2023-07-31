package com.todoapp.Fragment.Home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.todoapp.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    View v;
    ArrayList<String> aa;

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
        aa = new ArrayList<>();
        for(int i=0;i<3;i++){
            aa.add("task"+i);
        }
    }
}