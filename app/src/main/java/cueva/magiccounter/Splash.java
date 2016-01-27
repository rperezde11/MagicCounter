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


    protected static ImageView img_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setupFullscreen();

        super.onCreate(savedInstanceState);

        getSupportActionBar().hide(); // This to hide title bar...

        setContentView(R.layout.activity_splash);

        init();

        timer.scheduleAtFixedRate(new ModifyLogo(), 0, Const.SPLASH_LOGO_N_FRAMES);
        new Handler().postDelayed(new Task() , Const.SPLASH_INTRO_DELAY);
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
            Intent intent = new Intent(Splash.this, MainActivity.class);
            startActivity(intent);
            Splash.timer.cancel();
            finish();
        }
    }

    class ModifyLogo extends TimerTask
    {
        private float scale;
        private boolean up = true;

        public ModifyLogo()
        {
            scale = Const.SPLASH_MIN_SCALE;;
        }

        @Override
        public void run()
        {
            setScales(scale+Const.STEP);

            if( (scale+Const.STEP) < Const.SPLASH_MIN_SCALE ||
                    (scale+Const.STEP) > Const.SPLASH_MAX_SCALE )
            { up ^= true; }

            scale += up ? +Const.STEP : -Const.STEP;

        }

        public void setScales(float scale)
        {
            Splash.img_logo.setScaleX(scale);
            Splash.img_logo.setScaleY(scale);
        }
    }

}
