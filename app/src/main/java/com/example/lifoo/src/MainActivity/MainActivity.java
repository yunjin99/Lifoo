package com.example.lifoo.src.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lifoo.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigation;
    Menu menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigation = findViewById(R.id.bottom_navigation);
        menu = bottomNavigation.getMenu();

        bottomNavigation.setOnNavigationItemSelectedListener(new ItemSelectedListener());
        bottomNavigation.setSelectedItemId(R.id.home);
    }
    class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch(menuItem.getItemId())
            {
                case R.id.home:
                    menuItem.setIcon(R.drawable.icon_home_clicked);    // 선택된 이미지로 변경
                    menu.findItem(R.id.ranking).setIcon(R.drawable.icon_crown);
                    menu.findItem(R.id.alarm).setIcon(R.drawable.icon_alarm);
                    menu.findItem(R.id.user).setIcon(R.drawable.icon_user);
                    break;

                case R.id.ranking:
                    menuItem.setIcon(R.drawable.icon_crown_clicked);    // 선택된 이미지로 변경
                    menu.findItem(R.id.home).setIcon(R.drawable.icon_home);
                    menu.findItem(R.id.alarm).setIcon(R.drawable.icon_alarm);
                    menu.findItem(R.id.user).setIcon(R.drawable.icon_user);
                    break;

                case R.id.alarm:
                    menuItem.setIcon(R.drawable.icon_alarm_clicked);    // 선택된 이미지로 변경
                    menu.findItem(R.id.home).setIcon(R.drawable.icon_home);
                    menu.findItem(R.id.ranking).setIcon(R.drawable.icon_crown);
                    menu.findItem(R.id.user).setIcon(R.drawable.icon_user);
                    break;

                case R.id.user:
                    menuItem.setIcon(R.drawable.icon_user_clicked);    // 선택된 이미지로 변경
                    menu.findItem(R.id.home).setIcon(R.drawable.icon_home);
                    menu.findItem(R.id.ranking).setIcon(R.drawable.icon_crown);
                    menu.findItem(R.id.alarm).setIcon(R.drawable.icon_alarm);
                    break;
            }
            return true;
        }
    }

}
