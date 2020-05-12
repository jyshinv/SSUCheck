package org.playpang.ssucheck.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.AlteredCharSequence;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.playpang.ssucheck.CAA_RealTimeCheck2;
import org.playpang.ssucheck.CA_RealTimeCheck;
import org.playpang.ssucheck.R;
import org.playpang.ssucheck.data.SubjectNameItem;

import java.util.ArrayList;

public class CA_RealTimeCheckAdapter extends BaseAdapter {//BaseAdapter상속 후 alt+enter후 implement method 누르면 자동 생성됨

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    //여기서 <>안에는 data패키지 안 get,set 하는 클래스
    ArrayList<SubjectNameItem> listViewItemList = new ArrayList<SubjectNameItem>();

    //생성자
    public CA_RealTimeCheckAdapter(){ }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_subject_name_list, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView tv1 = convertView.findViewById(R.id.item_korsubject);
        TextView tv2 = convertView.findViewById(R.id.item_engsubject);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        SubjectNameItem subitem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        tv1.setText(subitem.getKorSubject());
        tv2.setText(subitem.getEngSubject());

        LinearLayout clickArea= convertView.findViewById(R.id.item_clickable_ll);
        clickArea.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){

                //눌렀을 때 item 정보 가게끔
                String kor = listViewItemList.get(pos).getKorSubject();
                String eng = listViewItemList.get(pos).getEngSubject();
                if(eng.equals("Database")){
                    Intent intent = new Intent(v.getContext(), CAA_RealTimeCheck2.class);
                    intent.putExtra("kor",kor);
                    intent.putExtra("eng",eng);
                    context.startActivity(intent);
                }else{
                    NotTime(v);
                }

            }
        });

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
    public void addItem(String kor, String eng) {
       SubjectNameItem item = new SubjectNameItem();

       item.setKorSubject(kor);
       item.setEngSubject(eng);
       listViewItemList.add(item);
    }

    //현재 진행중인 강의가 아니면 알림창 뜨는 함수
    public void NotTime(View v){
        new AlertDialog.Builder(v.getContext())
                .setTitle("강의").setMessage("현재 진행중인 강의가 아닙니다.")
                .setNegativeButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).show();
    }




}
