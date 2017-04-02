package com.android.masiro.proj052;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by haeyoung on 2017-04-01.
 */

public class Fragment2 extends Fragment {
    TextView t2,t4,t6, t8, t10, t12;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fview2 = inflater.inflate(R.layout.fragment2, null);
        t2 = (TextView)fview2.findViewById(R.id.textView2);
        t4 = (TextView)fview2.findViewById(R.id.textView4);
        t6 = (TextView) fview2.findViewById(R.id.textView6);
        t8 = (TextView) fview2.findViewById(R.id.textView8);
        t10 = (TextView) fview2.findViewById(R.id.textView10);
        t12 = (TextView) fview2.findViewById(R.id.textView12);
        return fview2;
    }

    public void getInfo(int spa, int piz, int mem, int to, int index, String time){

        t2.setText("Table " + (index+1));
        t4.setText(time);
        t6.setText(spa + "개");
        t8.setText(piz + "개");
        if (mem == 1) t10.setText("일반 멤버쉽");
        else if (mem == 2) t10.setText("VIP 멤버쉽");
        else t10.setText("멤버쉽 없음");
        t12.setText(to + " 원");
    }

    public void Init(){
        int table_number = ((MainActivity) getActivity()).index + 1;
        t2.setText("Table " + table_number);
        t4.setText("입력 없음");
        t6.setText("입력 없음");
        t8.setText("입력 없음");
        t10.setText("입력 없음");
        t12.setText("입력 없음");
    }
}
