package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Chronometer;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.media.MediaPlayer;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import static android.widget.AdapterView.*;

public class GameActivity extends AppCompatActivity
{

    Intent playGame = getIntent();
    private MediaPlayer mp;
    TextView t=null;
    ImageView currentView=null;
    private int paircount=0;
    //images are stored in drawables and named as sample_0,sample_1 etc

    //index array
    int[] pos={6,3,2,1,4,5,1,6,2,4,3,5};

    //Reshuffling the position array
    int[] position=RandomizeArray(pos);
    int currentPosition=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent data = getIntent();
        final int[] drawable = data.getIntArrayExtra("images");
        setContentView(R.layout.activity_game);

        final Chronometer timer = findViewById(R.id.timer);
        timer.start();

        t = findViewById(R.id.match);
        t.setText("STARTED");

        GridView gridview = findViewById(R.id.gridview);
        MyAdapter imageAdapter = new MyAdapter(this);
        gridview.setAdapter(imageAdapter);
        gridview.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                // Add sound
                mp = MediaPlayer.create(GameActivity.this, R.raw.flip);

                if(currentPosition<0)
                {
                    mp.seekTo(0);
                    mp.start();

                    currentPosition=i;
                    currentView=(ImageView)view;
                    File file = new File(getFilesDir() + "/"+position[i]+".jpg");
                    Bitmap bitmap = null;
                    try {
                        bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    ((ImageView)view).setImageBitmap(bitmap);
/*                    ImageView img = findViewById(R.id.imageView);
                    img.setImageBitmap(bitmap);



                    File file = new File(getFilesDir() + url);
                    Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                    ((ImageView)view).setImageBitmap(bitmap);*/
/*                    ImageView img = findViewById(drawable);
                    img.setImageBitmap(bitmap);
                    img.setVisibility(View.VISIBLE);*/


                    /*((ImageView)view).setImageResource(drawable[position[i]]);*/
                }
                else
                {
                    mp.seekTo(0);
                    mp.start();

                    if(currentPosition==i) {
                        ((ImageView) view).setImageResource(R.drawable.question_mark);
                    }
                    else if(position[currentPosition]!=position[i])
                    {
                        /*final ImageView nextView=(ImageView)view;*/
                        final ImageView nextView=(ImageView)view;
                        File file = new File(getFilesDir() + "/"+position[i]+".jpg");
                        Bitmap bitmap = null;
                        try {
                            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                            ((ImageView)view).setImageBitmap(bitmap);
                            final Handler handler=new Handler();
                            handler.postDelayed(new Runnable(){
                                @Override
                                public void run(){
                                    nextView.setImageResource(R.drawable.question_mark);
                                    currentView.setImageResource(R.drawable.question_mark);
                                }
                            },1000);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        /*((ImageView)view).setImageResource(drawable[position[i]]);*/

                        Toast.makeText(getApplicationContext(),"Not Matching", Toast.LENGTH_SHORT).show();

                    }

                    else
                    {
                        /*final ImageView nextView=(ImageView)view;*/
                        File file = new File(getFilesDir() + "/"+position[i]+".jpg");
                        Bitmap bitmap = null;
                        try {
                            bitmap = BitmapFactory.decodeStream(new FileInputStream(file));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        ((ImageView)view).setImageBitmap(bitmap);
                        /*((ImageView)view).setImageResource(drawable[position[i]]);*/
                        paircount++;
                        t.setText(paircount+"/6 Matches");

                        if(paircount==6)
                        {
                            Toast.makeText(getApplicationContext(),"Yow win",Toast.LENGTH_SHORT).show();
                            ((Chronometer) findViewById(R.id.timer)).stop();
                            AlertDialog.Builder alert=new AlertDialog.Builder(GameActivity.this);
                            alert.setTitle("Do you want to go back to main menu");
                            alert.setCancelable(true);
                            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                                    startActivity(intent);
                                }
                            });
                            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Toast.makeText(getApplicationContext(),"You already won!!",Toast.LENGTH_LONG);
                                }
                            });
                            AlertDialog al=alert.create();
                            al.show();

                        }
                    }

                    currentPosition=-1;

                }
            }
        });

    }


    //Reshuffling the array function
    int[] RandomizeArray(int[] array)
    {
        Random gen = new Random();  // Random number generator

        for (int i=0; i<array.length; i++) {
            int randomPosition = gen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }
    }
