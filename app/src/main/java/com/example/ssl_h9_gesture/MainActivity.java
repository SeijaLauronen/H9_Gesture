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

            tooooast = Float.toString(velocityX)+ " Y:" +Float.toString(velocityY);

            //Toast.makeText(MainActivity.this,tooooast, Toast.LENGTH_SHORT).show();

            //värin maxarvo 255

            //Nopeuksien maxarvot? 16000 jossain kai, mutta omassa jotain muuta
            Integer maxVX =24000;//25000;
            Integer maxVY =24000;//25000;
            Integer dividerX =maxVX/255;
            Integer dividerY =maxVY/255;


            int redValue = (int) velocityX/dividerX; //140;
            int bluValue = (int) velocityY/dividerY; //240;
            //En nyt jaksa etisä mikä se oli se itseisarvo...
            //miksi ylipäätään velocitystä tulee negatiivia arvoja?

            if (redValue<0){
                redValue = redValue *-1;
            }
            if (bluValue<0){
                bluValue = bluValue *-1;
            }


        //Tässä valittaa, jos antaa negatiivisia arvoja, mutta toimii kuitenkin niilläkin
            //leiaut.setBackgroundColor(Color.rgb(255,0,255));

            leiaut.setBackgroundColor(Color.rgb(redValue,0,bluValue));
            tooooast="";
            tooooast+= "   RED " + Integer.toString(redValue);
            if (redValue>255 || redValue < 0) {
                tooooast+= " RED meni yli ";// + Integer.toString(redValue);
                //Toast.makeText(MainActivity.this, tooooast, Toast.LENGTH_SHORT).show();
            }

            tooooast+= "   BLUE : " + Integer.toString(bluValue);
            if (bluValue>255 || bluValue < 0) {
                tooooast+= " BLUE meni yli ";//  + Integer.toString(bluValue);
                //Toast.makeText(MainActivity.this, tooooast, Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(MainActivity.this, tooooast, Toast.LENGTH_SHORT).show();


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
