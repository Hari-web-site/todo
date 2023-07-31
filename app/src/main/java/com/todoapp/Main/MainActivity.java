package com.todoapp.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.todoapp.Fragment.Calendar.CalendarFragment;
import com.todoapp.Fragment.Home.HomeFragment;
import com.todoapp.Fragment.Setting.SettingFragment;
import com.todoapp.R;

public class MainActivity extends AppCompatActivity {

    AppCompatImageView imageView;
    BottomNavigationView bottomNavigationView;

    Fragment homefragment,calendarfragment,settingfragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        findViewById();
        init();
    }

    void findViewById(){
        bottomNavigationView = findViewById(R.id.bottom_nav);
        imageView = findViewById(R.id.pop_up_menu);
        homefragment = new HomeFragment();
        calendarfragment = new CalendarFragment();
        settingfragment = new SettingFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.framelayout,homefragment)
                .commit();

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup();
            }
        });
    }

    void init(){

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(R.id.home == menuItem.getItemId()){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.framelayout,homefragment)
                            .commit();
                }if(R.id.calendar == menuItem.getItemId()){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.framelayout,calendarfragment)
                            .commit();
                }if(R.id.setting == menuItem.getItemId()){
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.framelayout,settingfragment)
                            .commit();
                }
                return true;
            }
        });
    }
    void popup(){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this,imageView);
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu,popupMenu.getMenu());
        popupMenu.show();
    }
}