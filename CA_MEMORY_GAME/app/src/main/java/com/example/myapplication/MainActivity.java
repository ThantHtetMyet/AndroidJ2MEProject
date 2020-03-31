package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyService.Callback
{

    ImageView image_view_ele;
    private int[] NO_SELECT_IMAGE = new int[0];
    private int IMAGE_COUNTER;
    final int GAME_CODE=100;
    private MyService svc;
    protected String input_url =null;
    private int count;
    final String [] url=new String[]{"/1.jpg","/2.jpg","/3.jpg","/4.jpg","/5.jpg",
            "/6.jpg","/7.jpg","/8.jpg","/9.jpg","/10.jpg","/11.jpg","/12.jpg",
            "/13.jpg","/14.jpg","/15.jpg","/16.jpg","/17.jpg","/18.jpg","/19.jpg","/20.jpg"};

    final int[] drawable=new int[]{ R.id.imageView1,R.id.imageView2,R.id.imageView3,R.id.imageView4,R.id.imageView5
            ,R.id.imageView6,R.id.imageView7,R.id.imageView8,R.id.imageView9,R.id.imageView10
            ,R.id.imageView11,R.id.imageView12,R.id.imageView13
            ,R.id.imageView14,R.id.imageView15,R.id.imageView16
            ,R.id.imageView17,R.id.imageView18,R.id.imageView19,R.id.imageView20};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, MyService.class);
        bindService(intent, svcConn, BIND_AUTO_CREATE);


        image_view_ele = (ImageView) findViewById(R.id.imageView1);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView2);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView3);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView4);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView5);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView6);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView7);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView8);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView9);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView10);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView11);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView12);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView13);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView14);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView15);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView16);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView17);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView18);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView19);
        image_view_ele.setOnClickListener(this);
        image_view_ele = (ImageView) findViewById(R.id.imageView20);
        image_view_ele.setOnClickListener(this);


        Button load_button = findViewById(R.id.load_button);

        EditText search=findViewById(R.id.search_input_box);

        search.setOnClickListener(this);

        load_button.setOnClickListener(this);

        Log.i("SEARCH LINK:",search.getText().toString());

        input_url = search.getText().toString();

    }

    ServiceConnection svcConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyService.LocalBinder binder = (MyService.LocalBinder) iBinder;
            if(binder != null){
                svc = binder.getService();
                svc.setCallback(MainActivity.this);
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            svc = null;
        }
    };


    @Override
    public void onClick(View v)
    {
        switch(v.getId()){
            case R.id.load_button:
                Log.i("I AM SEARCHING","Searching...!");
                Log.i("TARGET SITE : ",input_url);
                svc.Specific_Search(input_url);
                break;

            case R.id.imageView1:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW ONE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView2:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW TWO",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView3:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW THREE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView4:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW FOUR",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView5:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW FIVE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView6:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW SIX",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView7:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW SEVEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView8:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW EIGHT",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView9:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW NINE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView10:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW TEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView11:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW ELEVEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView12:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW TWELVE",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView13:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW THIRTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView14:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW FOURTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView15:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW FIFTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView16:
               // Toast.makeText(MainActivity.this,"IMAGE VIEW SIXTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView17:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW SEVENTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView18:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW EIGHTEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView19:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW NINETEEN",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
            case R.id.imageView20:
                //Toast.makeText(MainActivity.this,"IMAGE VIEW TWENTY",Toast.LENGTH_SHORT).show();
                SELECT_IMAGES(v);
                break;
        }
    }

    protected void onActivity(int requestCode, int resultCode,Intent data){//data is passed from elsewhere
        switch(requestCode){
            case GAME_CODE:
                int[] memoryImages = data.getIntArrayExtra("images");
                Intent done = new Intent(this,GameActivity.class);//intent shows where i want tog o
                done.putExtra("images",memoryImages);
                startActivity(done);
                break;
        }
    }

    @Override
    public void callback(String... images) {
        Log.i("CALL BACK","Call back!");

        String[] imgs = new String[20];
        int c = 0;
        String[] content = images[0].split("<");
        for(int i = 0; i < content.length; i++){
            if((content[i].contains("img src=")) && (content[i].contains("cdn.stocksnap.io"))){
                String img_link = content[i].split("\"")[1];
                if(c < 20){
                    imgs[c] = img_link;
                    c++;
                }
                else {
                    break;
                }
            }
        }
        downloadImages(imgs);
    }

    @Override
    public void publishProgress() {
        Log.i("PUBLISH PROGRESS","Publish Progress!");

        count += 1;
        final int i = count;
        for(int z=0;z<drawable.length;z++)
        {
            ImageView img = findViewById(drawable[z]);
            img.setVisibility(View.GONE);
        }
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final ProgressBar bar = findViewById(R.id.progressBar);
                final TextView progresstext = findViewById(R.id.progressText);
                int increment = 5;
                int current = bar.getProgress();
                int total = increment + current;
                System.out.println("Total: " + total);
                bar.setProgress(total);
                progresstext.setText("Downloading " + i + " of 20 images...");
                if(i == 20){
                    bar.setVisibility(View.GONE);
                    progresstext.setVisibility(View.GONE);

                }
                for(int a=0;a<i;a++)
                {
                    ImageView img = findViewById(drawable[a]);
                    img.setVisibility(View.VISIBLE);
                }
            }
        });
    }


    // SELECTING IMAGES
    public void SELECT_IMAGES(View imageview)
    {
        Toast.makeText(MainActivity.this,"You select IMAGE VIEW ID: "+ imageview.getId(),Toast.LENGTH_SHORT).show();

        imageview.setBackgroundColor(R.drawable.selector);

        int newSize = NO_SELECT_IMAGE.length+1;
        int[] temp = new int[newSize];
        for(int i = 0;i<NO_SELECT_IMAGE.length;i++)
        {
            temp[i]=NO_SELECT_IMAGE[i];
        }
        NO_SELECT_IMAGE = temp;
        NO_SELECT_IMAGE[IMAGE_COUNTER] = 3;
        IMAGE_COUNTER++;

        Log.i("IMAGE NUMBER:",":" + IMAGE_COUNTER);

        if(IMAGE_COUNTER==6){
            Log.i("IMAGE LIMITATION","IMAGE_COUNTER is 6");
            Toast.makeText(MainActivity.this,"IMAGE LIMITATION "+ imageview.getId(),Toast.LENGTH_SHORT).show();
//
               Intent playGame = new Intent(this, GameActivity.class);
                playGame.putExtra("images",NO_SELECT_IMAGE);
                startActivityForResult(playGame, GAME_CODE);
        }
    }

    private void downloadImages(String[] images){
        Log.i("DOWNLOAD)IMAGES","Download Images!");

        for(int i = 0; i < images.length; i++){
            String fn = getFilesDir() + "/" + (i + 1) + ".jpg";
            svc.DownloadImage(i, images[i], fn, (i + 1) + "");
            displayImage(drawable[i],url[i]);
            try {
                Thread.sleep(800);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void displayImage(int drawable,String url)
    {
        Log.i("DISPLAY_IMAGE","Display image!");

        try {
            File file = new File(getFilesDir() + url);
            Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));

            ImageView img = findViewById(drawable);
            img.setImageBitmap(bitmap);
            img.setVisibility(View.VISIBLE);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayImages(int[] drawable,String[] url)
    {
        Log.i("DISPLAY IMAGES","Display Images!");

        try {
            for (int j = 0, i = 0; j < url.length; j++) {
                File file = new File(getFilesDir() + url[j]);
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(file));

                ImageView img = findViewById(drawable[i]);
                img.setImageBitmap(bitmap);
                img.setVisibility(View.VISIBLE);
                i++;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
