package com.example.sudokusolve;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;

public class ManualEntry extends AppCompatActivity{
    protected class Cell {
        int value;
        Button btn;

        public Cell(int initValue, Context THIS) {
            value=initValue;
            btn = new Button(THIS);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    value++;
                    if (value>9) {
                        value=0;
                        btn.setText(null);
                    } else {
                        btn.setText(String.valueOf(value));
                    }
                }
            });
        }
    }

    public static Cell[][] table;
    String input;
    TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        input = "2 9 ? 7 4 3 8 6 1 "+
//                "4 ? 1 8 6 5 9 ? 7 "+
//                "8 7 6 1 9 2 5 4 3 "+
//                "3 8 7 4 5 9 2 1 6 "+
//                "6 1 2 3 ? 7 4 ? 5 "+
//                "? 4 9 2 ? 6 7 3 8 "+
//                "? ? 3 5 2 4 1 8 9 "+
//                "9 2 8 6 7 1 ? 5 4 "+
//                "1 5 4 9 3 ? 6 7 2 ";
//
//        String[] split = input.split(" ");
        table = new Cell[9][9];
        tl = new TableLayout(this);
        for (int i=0;i<9;i++) {
            TableRow tr = new TableRow(this);
            for (int j=0;j<9;j++) {
//                String s = split[i*9+j];
//                Character c = s.charAt(0);
                table[i][j]=new Cell(0, this);
                tr.addView(table[i][j].btn);
            }
            tl.addView(tr);
        }
        tl.setShrinkAllColumns(true);
        tl.setStretchAllColumns(true);
        Button saveBtn = new Button(this);
        saveBtn.setText("Save");
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePuzzle(v);
            }
        });
        tl.addView(saveBtn);
        setContentView(tl);
    }

    public void savePuzzle(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
