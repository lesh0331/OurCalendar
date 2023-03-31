package com.example.ourcalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// RecyclerView.Adapter 상속
class CalendarAdapter extends RecyclerView.Adapter<CalendarViewHolder>
{
    private final ArrayList<String> daysOfMonth; // 날짜 정보를 저장하는 ArrayList
    private final OnItemListener onItemListener; // RecyclerView 아이템 클릭 시 이벤트 처리를 위한 인터페이스

    // 생성자
    public CalendarAdapter(ArrayList<String> daysOfMonth, OnItemListener onItemListener)
    {
        this.daysOfMonth = daysOfMonth;
        this.onItemListener = onItemListener;
    }

    // 새로운 ViewHolder 생성
    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar_cell, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666); // 아이템 높이 지정
        return new CalendarViewHolder(view, onItemListener);
    }

    // ViewHolder와 데이터를 연결
    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position)
    {
        holder.dayOfMonth.setText(daysOfMonth.get(position)); // 날짜 정보를 TextView에 설정
    }

    // 데이터 개수 반환
    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    // RecyclerView 아이템 클릭 시 이벤트 처리를 위한 인터페이스
    public interface  OnItemListener
    {
        void onItemClick(int position, String dayText);
    }
}