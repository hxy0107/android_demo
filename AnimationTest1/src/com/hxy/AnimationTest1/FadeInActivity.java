package com.hxy.AnimationTest1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by xianyu.hxy on 2015/8/10.
 */
public class FadeInActivity extends Activity implements Animation.AnimationListener {

    TextView txtMessage;
    Button btnStart;
    Animation animFadein;

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if(animation==animFadein){
            Toast.makeText(getApplicationContext(),"Animation Stopped",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fadein);


        txtMessage = (TextView) findViewById(R.id.txtMessage);
        btnStart = (Button) findViewById(R.id.btnStart);
        animFadein= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        //animFadein.setAnimationListener(this);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMessage.setVisibility(View.INVISIBLE);
                txtMessage.startAnimation(animFadein);
            }
        });
    }
}
