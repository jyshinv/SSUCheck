package org.playpang.ssucheck.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.playpang.ssucheck.CBA_MyCheckSubject1;
import org.playpang.ssucheck.CBA_MyCheckSubject2;
import org.playpang.ssucheck.CBA_MyCheckSubject3;
import org.playpang.ssucheck.CBA_MyCheckSubject4;
import org.playpang.ssucheck.CBA_MyCheckSubject5;
import org.playpang.ssucheck.R;
import org.playpang.ssucheck.data.SubjectNameItem;
import org.playpang.ssucheck.data.jw_db_checkresult;

import java.util.ArrayList;

public class CBA_MyCheckSubject1Adapter extends BaseAdapter {

    //색상 변수
    String Green = "#1CC62F";
    String Orange = "#FFBA5C";
    String Red = "#FF1010";
    String DarkGray = "#47525E";

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    //여기서 <>안에는 data패키지 안 get,set 하는 클래스
    ArrayList<jw_db_checkresult> listViewItemList = new ArrayList<jw_db_checkresult>();

    //생성자
    public CBA_MyCheckSubject1Adapter(){ }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_databasecheckresult, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView tv1 = convertView.findViewById(R.id.item_date);
        TextView tv2 = convertView.findViewById(R.id.item_checkresult);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        jw_db_checkresult subitem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        tv1.setText(subitem.getcheckTime());

        //tv2에 출석결과 넣기
        if(subitem.getcheckResult().equals("attend")){
            tv2.setText("출석");
            tv2.setTextColor(Color.parseColor(Green));
        }else if(subitem.getcheckResult().equals("late")){
            tv2.setText("지각");
            tv2.setTextColor(Color.parseColor(Orange));
        }else if(subitem.getcheckResult().equals("absence")){
            tv2.setText("결석");
            tv2.setTextColor(Color.parseColor(Red));
        }

        return convertView;
    }

    //Adapter에 사용되는 데이터 개수를 리턴해준다.
    @Override
    public int getCount() {
        return listViewItemList.size();
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 만드는 함수임!
    public void addItem(String jw_time, String checkresult) {
        jw_db_checkresult item = new jw_db_checkresult();

        item.setcheckTime(jw_time);
        item.setcheckResult(checkresult);

        listViewItemList.add(item);
    }

}
