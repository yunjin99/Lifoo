package com.example.lifoo.src.AlertFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lifoo.R;
import com.example.lifoo.src.MainActivity.MainActivity;

import java.util.ArrayList;

public class AlertFragment extends Fragment {

    MainActivity mainActivity;
    ViewGroup viewGroup;

    AlertAdapter alertAdapter;
    RecyclerView recyclerView;
    ArrayList alert_list;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mainActivity = null;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_alert,container,false);

        recyclerView = viewGroup.findViewById(R.id.alert_rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        alert_list = new ArrayList<AlertItem>();

        // 더미 데이터 넣기
        Drawable dummy = getResources().getDrawable(R.drawable.frame_2);
        Drawable dummy_2 = getResources().getDrawable(R.drawable.frame_3);

        AlertItem alertItem = new AlertItem("박수", "뜨거운 물만두님이 박수 리액션을 보냈습니다", "1시간 전", dummy);
        AlertItem alertItem_1 = new AlertItem("박수", "뜨거운 물만두님이 박수 리액션을 보냈습니다", "1시간 전", dummy_2);
        AlertItem alertItem_2 = new AlertItem("박수", "뜨거운 물만두님이 박수 리액션을 보냈습니다", "1시간 전", dummy);


        alert_list.add(alertItem);
        alert_list.add(alertItem_1);
        alert_list.add(alertItem_2);



        // 어댑터
        alertAdapter = new AlertAdapter(alert_list);
        recyclerView.setAdapter(alertAdapter);

        alertAdapter.setOnItemClickListener(new AlertAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {

            }
        });

        alertAdapter.notifyDataSetChanged();
        return viewGroup;
    }

}
