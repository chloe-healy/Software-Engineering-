package com.example.sudokusolve;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    public static final int CAMERA_ACTION_CODE=1;
    ImageView gridView;
    TextView textBox;
    FloatingActionButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        imageView = findViewById(R.id.gridView);
        button = findViewById(R.id.button);

        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{
                    Manifest.permission.CAMERA
            }, CAMERA_ACTION_CODE);
        }

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_ACTION_CODE);
        });

        if (ManualEntry.table!=null) {
            textBox = findViewById(R.id.text);
            String grid="-------------------------------------------------------------\n";
            for (int i=0; i<9; i++) {
                grid += "  |  ";
                for (int j=0; j<9; j++) {
                    grid += ManualEntry.table[i][j].value;
                    grid += "  |  ";
                }
                grid+="\n-------------------------------------------------------------\n";
            }
            textBox.setText(grid);
        } else {
            textBox = findViewById(R.id.text);
            String grid="-------------------------------------------------------------\n";
            for (int i=0; i<9; i++) {
                grid += "  |  ";
                for (int j=0; j<9; j++) {
//                    grid += ManualEntry.table[i][j].value;
                    grid += "   |   ";
                }
                grid+="\n-------------------------------------------------------------\n";
            }
            textBox.setText(grid);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        // process picture here
        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode==CAMERA_ACTION_CODE && resultCode==RESULT_OK && data!=null) {
//            Bundle bundle = data.getExtras();
//            Bitmap finalPhoto = (Bitmap) bundle.get("data");
//            imageView.setImageBitmap(finalPhoto);
//        }
    }

    public void enterPuzzle(View view) {
        Intent intent = new Intent(this, ManualEntry.class);
        startActivity(intent);
    }
}
