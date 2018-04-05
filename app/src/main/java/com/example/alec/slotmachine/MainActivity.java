package com.example.alec.slotmachine;

import android.content.Intent;
import android.media.Image;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int pointCount;
    private boolean stopped;
    private int counter;
    private int counter2;
    private int counter3;
    private long slot_one_speed;
    private long slot_two_speed;
    private long slot_three_speed;
    private ImageView slot_one;
    private ImageView slot_two;
    private ImageView slot_three;
    private TextView points;
    private SeekBar bar;
    private Button start;
    private Button rules;
    private Handler handler;
    private Update update;
    private Update2 update2;
    private Update3 update3;
    private int[] images = {R.drawable.asmongold, R.drawable.tyler, R.drawable.greek, R.drawable.boogie};
    Random rand = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;
        counter2 = 1;
        counter3 = 2;
        stopped = false;
        update = new Update();
        update2 = new Update2();
        update3 = new Update3();
        points = findViewById(R.id.point_amount);
        bar = findViewById(R.id.bar);
        start = findViewById(R.id.start_button);
        rules = findViewById(R.id.rules_button);
        slot_one_speed=bar.getProgress()+1*rand.nextInt(3)+1;
        slot_two_speed=bar.getProgress()+1*rand.nextInt(3)+1;
        slot_three_speed=bar.getProgress()+1*rand.nextInt(3)+1;
        slot_one = findViewById(R.id.slot_one);
        slot_two = findViewById(R.id.slot_two);
        slot_three = findViewById(R.id.slot_three);
        handler = new Handler();
        handler.postDelayed(update, 1000);
        handler.postDelayed(update2, 1000);
        handler.postDelayed(update3, 1000);


        bar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        slot_one_speed=progress+1*rand.nextInt(3)+1;
                        slot_two_speed=progress+1*rand.nextInt(3)+1;
                        slot_three_speed=progress+1*rand.nextInt(3)+1;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        if (savedInstanceState !=null) {
            points.setText(savedInstanceState.getString("POINTS"));
            pointCount = Integer.parseInt(points.getText().toString());
        }


        }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("POINTS", points.getText().toString());
    }





    public void buttonPressed(View v){
        if(stopped == false) {
            handler.removeCallbacks(update);
            handler.removeCallbacks(update2);
            handler.removeCallbacks(update3);
            start.setText("Start");
            stopped = true;
            if((counter == counter2) && (counter2 == counter3)){
                pointCount+=40;
                Toast.makeText(this, "+40 points!", Toast.LENGTH_SHORT).show();

            }else
            if((counter == 2) || (counter2 == 2) || (counter3 == 2)){
                pointCount+=10;
                Toast.makeText(this, "+10 points!", Toast.LENGTH_SHORT).show();
            }
            points.setText(pointCount+"");
        } else {
            slot_one_speed=bar.getProgress()+1*rand.nextInt(3)+1;
            slot_two_speed=bar.getProgress()+1*rand.nextInt(3)+1;
            slot_three_speed=bar.getProgress()+1*rand.nextInt(3)+1;
            handler.postDelayed(update, 1000);
            handler.postDelayed(update2, 1000);
            handler.postDelayed(update3, 1000);
            start.setText("Stop");
            stopped = false;
        }
    }

    public class Update implements Runnable {
        @Override
        public void run(){
            if(counter < 4) {
                slot_one.setImageDrawable(getResources().getDrawable(images[counter]));
                counter++;
            } else {
                counter = 0;
                slot_one.setImageDrawable(getResources().getDrawable(images[counter]));
                counter++;
            }



            handler.postDelayed(update, slot_one_speed*100);


        }

    }

    public class Update2 implements Runnable {
        @Override
        public void run() {
            if (counter2 < 4) {
                slot_two.setImageDrawable(getResources().getDrawable(images[counter2]));
                counter2++;
            } else {
                counter2 = 0;
                slot_two.setImageDrawable(getResources().getDrawable(images[counter2]));
                counter2++;
            }


            handler.postDelayed(update2, slot_two_speed*100);

        }
    }

    public class Update3 implements Runnable {
        @Override
        public void run() {
            if (counter3 < 4) {
                slot_three.setImageDrawable(getResources().getDrawable(images[counter3]));
                counter3++;
            } else {
                counter3 = 0;
                slot_three.setImageDrawable(getResources().getDrawable(images[counter3]));
                counter3++;
            }


            handler.postDelayed(update3, slot_three_speed*100);

        }
    }

    public void gotoRules(View v) {
        Intent i = new Intent(this, Rules.class);
        i.putExtra("POINTS", pointCount);
        startActivity(i);
    }

}
