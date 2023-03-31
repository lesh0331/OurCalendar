package com.example.ourcalendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ourcalendar.CalendarAdapter;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 초기화 함수 호출
        initWidgets();

        // 현재 날짜로 선택 날짜 설정
        selectedDate = LocalDate.now();

        // 달력 뷰 설정 함수 호출
        setMonthView();
    }

    // 뷰 초기화 함수
    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    // 달력 뷰 설정 함수
    private void setMonthView()
    {
        // 상단에 표시할 연도 및 월 텍스트 설정
        monthYearText.setText(monthYearFromDate(selectedDate));

        // 선택한 월의 날짜들을 ArrayList에 저장
        ArrayList<String> daysInMonth = daysInMonthArray(selectedDate);

        // RecyclerView 어댑터 생성
        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);

        // RecyclerView에 GridLayoutManager 설정
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);

        // RecyclerView에 어댑터 설정
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    // 선택한 월의 날짜들을 ArrayList로 반환하는 함수
    private ArrayList<String> daysInMonthArray(LocalDate date)
    {
        ArrayList<String> daysInMonthArray = new ArrayList<>();

        // YearMonth 클래스를 사용해 해당 월의 일 수 구하기
        YearMonth yearMonth = YearMonth.from(date);
        int daysInMonth = yearMonth.lengthOfMonth();

        // 해당 월의 1일 구하기
        LocalDate firstOfMonth = selectedDate.withDayOfMonth(1);

        // 해당 월의 1일이 무슨 요일인지 구하기 (1: 월요일, 2: 화요일, ...)
        int dayOfWeek = firstOfMonth.getDayOfWeek().getValue();

        // 1주차부터 6주차까지 날짜를 ArrayList에 저장
        for(int i = 1; i <= 42; i++)
        {
            // 날짜가 해당 월의 범위를 벗어나면 빈 문자열을 추가
            if(i <= dayOfWeek || i > daysInMonth + dayOfWeek)
            {
                daysInMonthArray.add("");
            }
            // 그 외의 경우, 날짜를 문자열로 변환하여 추가
            else
            {
                daysInMonthArray.add(String.valueOf(i - dayOfWeek));
            }
        }

        return  daysInMonthArray;
    }

    // LocalDate 객체에서 월과 연도를 추출하여 문자열로 반환하는 함수
    private String monthYearFromDate(LocalDate date)
    {
        // "MMMM yyyy" 포맷의 문자열로 변환
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }

    public void previousMonthAction(View view)
    {
        // 이전 달로 설정하고 월-년 텍스트와 리사이클러뷰 업데이트
        selectedDate = selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        // 다음 달로 설정하고 월-년 텍스트와 리사이클러뷰 업데이트
        selectedDate = selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText)
    {
        // 빈 날짜가 아닌 경우 해당 날짜 정보를 토스트 메시지로 출력
        if(!dayText.equals(""))
        {
            String message = "Selected Date " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}
