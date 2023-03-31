package com.example.ourcalendar;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    public final TextView dayOfMonth;
    private final CalendarAdapter.OnItemListener onItemListener;

    // CalendarViewHolder 생성자
    public CalendarViewHolder(@NonNull View itemView, CalendarAdapter.OnItemListener onItemListener) {
        super(itemView);
        dayOfMonth = itemView.findViewById(R.id.cellDayText);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    // 클릭 이벤트 처리
    @Override
    public void onClick(View view) {
        // OnItemListener를 통해 MainActivity에 onItemClick 호출하여 클릭한 날짜 정보 전달
        onItemListener.onItemClick(getAdapterPosition(), (String) dayOfMonth.getText());
    }
}
