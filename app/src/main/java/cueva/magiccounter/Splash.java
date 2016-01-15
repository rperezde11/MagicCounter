package cueva.magiccounter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Splash extends AppCompatActivity
{

    public static Timer timer;
    private final int INTRO_DELAY = 2000; // Time the intro lasts in milliseconds.
    private final int LOGO_N_FRAMES = 20; // Time the intro lasts in milliseconds.


    protected static ImageView img_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setupFullscreen();

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide(); // This to hide title bar...

        setContentView(R.layout.activity_splash);

        init();

        timer.scheduleAtFixedRate(new ModifyLogo(), 0, LOGO_N_FRAMES);
        new Handler().postDelayed(new Task() , INTRO_DELAY);
    }


    /**
     * Apparently this has to be used before adding content to the layout.
     */
    private void setupFullscreen()
    {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void init()
    {
        timer = new Timer();

        img_logo = (ImageView) findViewById(R.id.logo);
    }



    class Task implements Runnable
    {
        @Override
        public void run()
        {
            Intent intent = new Intent(Splash.this, MainGame.class);
            startActivity(intent);
            Splash.timer.cancel();
            finish();
        }
    }

    class ModifyLogo extends TimerTask
    {
        private int counter = 0;
        private float initial_sc = 0.05f;
        private final float DIFF = 0.01f;

        @Override
        public void run()
        {
            float actual_scale_X = counter==0 ?  initial_sc : Splash.img_logo.getScaleX();
            float actual_scale_Y = counter==0 ?  initial_sc : Splash.img_logo.getScaleY();

            Splash.img_logo.setScaleX(actual_scale_X + DIFF);
            Splash.img_logo.setScaleY(actual_scale_Y + DIFF);

            initial_sc += DIFF;
            counter++;
        }
    }

}
