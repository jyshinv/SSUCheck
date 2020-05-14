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
import org.playpang.ssucheck.CBA_MyCheckSubject1;
import org.playpang.ssucheck.CBA_MyCheckSubject2;
import org.playpang.ssucheck.CBA_MyCheckSubject3;
import org.playpang.ssucheck.CBA_MyCheckSubject4;
import org.playpang.ssucheck.CBA_MyCheckSubject5;
import org.playpang.ssucheck.R;
import org.playpang.ssucheck.data.SubjectNameItem;

import java.util.ArrayList;

public class CB_RealTimeCheckAdapter extends BaseAdapter {//BaseAdapter상속 후 alt+enter후 implement method 누르면 자동 생성됨

    //C_Menu에서 받은 이름에 학번을 붙인 이름
    String fullname;

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    //여기서 <>안에는 data패키지 안 get,set 하는 클래스
    ArrayList<SubjectNameItem> listViewItemList = new ArrayList<SubjectNameItem>();

    //생성자
    public CB_RealTimeCheckAdapter(){ }

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

                String kor = listViewItemList.get(pos).getKorSubject();
                String eng = listViewItemList.get(pos).getEngSubject();
                if(eng.equals("Database")){
                    Intent intent = new Intent(v.getContext(), CBA_MyCheckSubject1.class);
                    intent.putExtra("kor",kor);
                    intent.putExtra("eng",eng);
                    intent.putExtra("name",fullname);
                    context.startActivity(intent);
                }else if(eng.equals("Computer Architecture")){
                    Intent intent = new Intent(v.getContext(), CBA_MyCheckSubject2.class);
                    intent.putExtra("kor",kor);
                    intent.putExtra("eng",eng);
                    intent.putExtra("name",fullname);
                    context.startActivity(intent);

                }else if(eng.equals("Digital Media Principles and practice")){
                    Intent intent = new Intent(v.getContext(), CBA_MyCheckSubject3.class);
                    intent.putExtra("kor",kor);
                    intent.putExtra("eng",eng);
                    intent.putExtra("name",fullname);
                    context.startActivity(intent);

                }else if(eng.equals("Computer Programming")){
                    Intent intent = new Intent(v.getContext(), CBA_MyCheckSubject4.class);
                    intent.putExtra("kor",kor);
                    intent.putExtra("eng",eng);
                    intent.putExtra("name",fullname);
                    context.startActivity(intent);

                }else if(eng.equals("Thesis Seminar")){
                    Intent intent = new Intent(v.getContext(), CBA_MyCheckSubject5.class);
                    intent.putExtra("kor",kor);
                    intent.putExtra("eng",eng);
                    intent.putExtra("name",fullname);
                    context.startActivity(intent);

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

    //이전 창에서 보낸 Name정보를 이 함수를 호출해 Adapter로 전달 후 Adapter에서 위에 onClick부분에서 putExtra로 Name보내기
    public void addName(String name){
        fullname = name + " 20152020";
    }





}
