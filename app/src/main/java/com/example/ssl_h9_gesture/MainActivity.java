package com.example.ssl_h9_gesture;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

//Koita muutta LinearLayoutiksi jos tulee ongelmia
public class MainActivity extends AppCompatActivity {
    static int vari =1;
    public  class Kuuntelija extends GestureDetector.SimpleOnGestureListener { //minun pit laittaa tuo Gesture tuohon eteen
        ConstraintLayout leiaut = findViewById(R.id.leiautti);

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            //Toast.makeText(MainActivity.this, "FLINGGI", Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, e1.toString(), Toast.LENGTH_SHORT).show();
            float x = e1.getX();
            float y = e1.getY() ;
            float x2 = e2.getX();
            float y2 = e2.getY() ;
            String tooooast = Float.toString(x)+ " " +Float.toString(y);
            tooooast += " " + Float.toString(x2)+ " " +Float.toString(y2);
            Toast.makeText(MainActivity.this,tooooast, Toast.LENGTH_SHORT).show();

            //maxarvo 255
            int redValue = (int) velocityX;
            int bluValue = (int) velocityY;
            //leiaut.setBackgroundColor(Color.rgb(113,123,123));

            leiaut.setBackgroundColor(Color.rgb(redValue,123,bluValue));



            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Toast.makeText(MainActivity.this, "tupla", Toast.LENGTH_SHORT).show();
            if (vari != 0){
                leiaut.setBackgroundColor(Color.BLACK);
                vari = 0;
            } else {
                leiaut.setBackgroundColor(Color.WHITE);
                vari = 1;
            }
            return super.onDoubleTap(e);
        }
    }

    GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //TextView textView = findViewById(R.id.teksti);
        ConstraintLayout leiaut = findViewById(R.id.leiautti);
        gestureDetector =new GestureDetector(this, new Kuuntelija());
        leiaut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                return true;//tässä kun oli false, niin ei toiminut!!!!
            }
        });
/*
        textView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gestureDetector.onTouchEvent(event);
                Toast.makeText(MainActivity.this, "TESTIII", Toast.LENGTH_SHORT).show();
                return false; //pitääkö tämä laittaa trueksi
            }
        });
*/

    }
}
