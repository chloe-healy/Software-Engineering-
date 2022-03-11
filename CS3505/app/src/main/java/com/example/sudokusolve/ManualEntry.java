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

        // Cell stores a button and a text value for the button
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
    TableLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        table = new Cell[9][9];
        tl = new TableLayout(this);

        // create a 9x9 grid of Cells
        for (int i=0;i<9;i++) {
            TableRow tr = new TableRow(this);
            for (int j=0;j<9;j++) {
                table[i][j]=new Cell(0, this);
                tr.addView(table[i][j].btn);
            }
            tl.addView(tr);
        }

        // fit grid to screen
        tl.setShrinkAllColumns(true);
        tl.setStretchAllColumns(true);

        // button to save puzzle and return to original screen
        Button saveBtn = new Button(this);
        saveBtn.setText("Save");
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePuzzle(v);
            }
        });
        tl.addView(saveBtn);

        // print the grid of Cells to the screen
        setContentView(tl);
    }

    public void savePuzzle(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
