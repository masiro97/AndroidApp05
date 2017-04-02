package com.android.masiro.proj052;

import android.content.DialogInterface;
import android.support.annotation.RequiresPermission;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

class Dataset {
    int spa = 0;
    int piz = 0;
    int mem = 0;
    int to = 0;
    int set = 0;
    int IsFirstInput = 0;

    void getData(int spaghetti, int pizza, int member, int total) {
        set = 0;
        spa = spaghetti;
        piz = pizza;
        mem = member;
        to = total;
    }
}

public class MainActivity extends AppCompatActivity {
    int init = 0;
    EditText e1, e2;
    TextView t, t14, t18;
    RadioButton r1, r2;
    Button b4;
    int index = -1;
    int member = 0;
    int set = -1;
    Dataset D[] = new Dataset[4];
    View dlgView;
    Fragment1 frag1;
    Fragment2 frag2;
    String CurrentTime;

    public void OnButton(final View v) {
        AlertDialog.Builder dlg = new AlertDialog.Builder(this);
        dlgView = getLayoutInflater().inflate(R.layout.order_dialog, null);
        t18 = (TextView) dlgView.findViewById(R.id.textView18);
        t = (TextView) dlgView.findViewById(R.id.textView);
        t14 = (TextView) dlgView.findViewById(R.id.textView14);
        r1 = (RadioButton) dlgView.findViewById(R.id.radioButton1);
        r2 = (RadioButton) dlgView.findViewById(R.id.radioButton2);
        e1 = (EditText) dlgView.findViewById(R.id.editText1);
        e2 = (EditText) dlgView.findViewById(R.id.editText2);
        b4 = (Button) dlgView.findViewById(R.id.button4);

        if (v.getId() == R.id.button1) {
            if (index == -1) {
                Toast.makeText(getApplicationContext(), "Plaase Select Table", Toast.LENGTH_SHORT).show();
            } else {
                CurrentTime = DateFormat.getDateTimeInstance().format(new Date());
                dlg.setView(dlgView);
                dlg.setTitle("New Order");
                dlg.setPositiveButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Snackbar.make(v, "새 주문이 취소되었습니다", Snackbar.LENGTH_SHORT)
                                .setAction("OK", null).show();
                    }
                });
                dlg.setNegativeButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        frag1.ChangeState(index, 0);
                        frag2.getInfo(D[index].spa, D[index].piz, D[index].mem, D[index].to, index, CurrentTime);
                        D[index].set = 1;
                        D[index].IsFirstInput = 1;
                        Snackbar.make(v, "정보가 입력되었습니다", Snackbar.LENGTH_SHORT)
                                .setAction("OK", null).show();
                    }
                });
                dlg.show();
                WriteInfo();
            }
        } else if (v.getId() == R.id.button2) {
            if (index == -1) {
                Toast.makeText(getApplicationContext(), "Plaase Select Table", Toast.LENGTH_SHORT).show();
            } else {
                if (D[index].IsFirstInput == 0) {
                    Toast.makeText(getApplicationContext(), "Please New Order", Toast.LENGTH_SHORT).show();
                } else {
                    dlg.setTitle("Rewrite Information")
                            .setView(dlgView)
                            .setPositiveButton("Close", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Snackbar.make(v, "정보수정을 취소합니다", Snackbar.LENGTH_SHORT)
                                            .show();
                                }
                            });
                    dlg.setNegativeButton("Enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            frag1.ChangeState(index, 0);
                            frag2.getInfo(D[index].spa, D[index].piz, D[index].mem, D[index].to, index, CurrentTime);
                            D[index].set = 1;
                            D[index].IsFirstInput = 1;
                            Snackbar.make(v, "정보가 입력되었습니다", Snackbar.LENGTH_SHORT)
                                    .setAction("OK", null).show();
                        }
                    });
                    dlg.show();
                    t.setText("Table " + (index + 1));
                    t14.setText(CurrentTime);
                    frag1.ChangeState(index, init);
                    WriteInfo();
                }
            }


        } else if (v.getId() == R.id.button3) {
            if (index == -1) {
                Toast.makeText(getApplicationContext(), "Plaase Select Table", Toast.LENGTH_SHORT).show();
            } else {
                if (D[index].IsFirstInput == 0) {
                    Toast.makeText(getApplicationContext(), "Please New Order", Toast.LENGTH_SHORT).show();
                } else {

                    D[index].spa = 0;
                    D[index].piz = 0;
                    D[index].mem = 0;
                    D[index].set = 0;
                    D[index].to = 0;
                    frag1.ChangeState(index, 1);
                    frag2.Init();

                    Snackbar.make(v, "주문을 초기화합니다", Snackbar.LENGTH_SHORT)
                            .setAction("OK", null).show();
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dlgView = getLayoutInflater().inflate(R.layout.order_dialog, null);
        t18 = (TextView) dlgView.findViewById(R.id.textView18);
        r1 = (RadioButton) dlgView.findViewById(R.id.radioButton1);
        r2 = (RadioButton) dlgView.findViewById(R.id.radioButton2);
        e1 = (EditText) dlgView.findViewById(R.id.editText1);
        e2 = (EditText) dlgView.findViewById(R.id.editText2);
        b4 = (Button) dlgView.findViewById(R.id.button4);
        for (int i = 0; i < 4; i++) {
            D[i] = new Dataset();
            D[i].getData(0, 0, 0, 0);
        }

        frag1 = (Fragment1) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        frag2 = (Fragment2) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        frag1.OnItem();

        replaceFragment1();
        replaceFragment2();
    }

    public void WriteInfo() {

        t.setText("Table " + (index + 1));

        t14.setText(CurrentTime);
        r1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                member = 1;
            }
        });

        r2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                member = 2;
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                D[index].spa = Integer.parseInt(e1.getText().toString());
                D[index].piz = Integer.parseInt(e2.getText().toString());
                D[index].to = D[index].spa * 10000 + D[index].piz * 12000;
                if (member == 1) {
                    D[index].to *= 0.9;
                    D[index].mem = 1;
                }
                if (member == 2) {
                    D[index].to *= 0.7;
                    D[index].mem = 2;
                }
                t18.setText(D[index].to + " 원");
            }
        });

    }

    public void replaceFragment1() {

        Fragment1 fragment1 = new Fragment1();
        FragmentManager fm1 = getSupportFragmentManager();
        FragmentTransaction ft1 = fm1.beginTransaction();
        ft1.replace(R.id.fragment1, fragment1);
        ft1.commit();
    }

    public void replaceFragment2() {

        Fragment2 fragment2 = new Fragment2();
        FragmentManager fm2 = getSupportFragmentManager();
        FragmentTransaction ft2 = fm2.beginTransaction();
        ft2.replace(R.id.fragment2, fragment2);
        ft2.commit();
    }

    public void InitText() {

        if (set == 0) {
            frag2.Init();
        } else {
            String currentTime = DateFormat.getDateTimeInstance().format(new Date());
            frag2.getInfo(D[index].spa, D[index].piz, D[index].mem, D[index].to, index, currentTime);

        }
    }
}
