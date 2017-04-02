package com.android.masiro.proj052;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by haeyoung on 2017-04-01.
 */

public class Fragment1 extends Fragment {

    String[] LIST_MENU = {"Table 1 (empty)", "Table 2 (empty)", "Table 3 (empty)", "Table 4 (empty)"};
    ListView listview;
    ArrayAdapter Adapter;
    static int select = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fview1 = inflater.inflate(R.layout.fragment1, null);

        Adapter = new ArrayAdapter(getActivity(), R.layout.support_simple_spinner_dropdown_item, LIST_MENU);
        listview = (ListView) fview1.findViewById(R.id.listview1);
        listview.setAdapter(Adapter);

        return fview1;
    }

    public int getItemPosition(String str) {

        if (str == "Table 1") return 1;
        else if (str == "Table 2") return 2;
        else if (str == "Table 3") return 3;
        else if (str == "Table 4") return 4;
        else if (str == "Table 1 (empty)") return 10;
        else if (str == "Table 2 (empty)") return 20;
        else if (str == "Table 3 (empty)") return 30;
        else if (str == "Table 4 (empty)") return 40;
        else return 0;
    }

    public void OnList(int pos) {
        if (pos == 10 || pos == 1) select = 1;
        else if (pos == 20 || pos == 2) select = 2;
        else if (pos == 30 || pos == 3) select = 3;
        else if (pos == 40 || pos == 4) select = 4;
        else select = 0;

    }

    public void ChangeState(int index, int init) {
        if(init ==0) {
            if (index == 0) LIST_MENU[index] = "Table 1";
            else if (index == 1) LIST_MENU[index] = "Table 2";
            else if (index == 2) LIST_MENU[index] = "Table 3";
            else if (index == 3) LIST_MENU[index] = "Table 4";
        }
        else{
            if (index == 0) LIST_MENU[index] = "Table 1 (empty)";
            else if (index == 1) LIST_MENU[index] = "Table 2 (empty)";
            else if (index == 2) LIST_MENU[index] = "Table 3 (empty)";
            else if (index == 3) LIST_MENU[index] = "Table 4 (empty)";
        }
        Adapter.notifyDataSetChanged();
    }

    public void OnItem() {
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String table_name = (String) parent.getItemAtPosition(position);
                int pos = getItemPosition(table_name);
                OnList(pos);
                ((MainActivity) getActivity()).index = select - 1;
                if (((MainActivity) getActivity()).D[((MainActivity) getActivity()).index].set == 0)
                    ((MainActivity) getActivity()).set = 0;
                else ((MainActivity) getActivity()).set = 1;

                ((MainActivity) getActivity()).InitText();

            }
        });

    }

}
