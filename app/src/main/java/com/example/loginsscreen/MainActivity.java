package com.example.loginsscreen;
import android.os.Build;
import android.util.Pair;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Paint;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private  static int SPLASH_SCREEN=4000;

    Animation top_anim,bottom_anim;

    ImageView image;
    TextView text,slogan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //hooks
        image=findViewById(R.id.imageView);
        text=findViewById(R.id.textView);
        slogan=findViewById(R.id.textView2);

        top_anim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_anim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        image.setAnimation(top_anim);
        text.setAnimation(bottom_anim);
        slogan.setAnimation(bottom_anim);

        //if we want to set counter on xml file then..
        //app:counterEnabled="true"
        //app:counterMaxLength="15"


        //delayed method
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,Login.class);
                Pair[] pairs=new Pair[2];
                pairs[0]=new Pair<View,String>(image,"logo_image");
                pairs[1]=new Pair<View,String>(text,"logo_text");
                ActivityOptions options=ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                startActivity(intent,options.toBundle());

            }
        },SPLASH_SCREEN);

    }
}