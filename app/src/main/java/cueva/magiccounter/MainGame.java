package cueva.magiccounter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class MainGame extends AppCompatActivity implements CustomEvents.PointEvents {

    private List<TextView> txt_points = new ArrayList<>(Const.DEF_NUM_PLAYERS); // TODO: TO CHANGE IF WE FINALLY ADD MORE PLAYERS...;
    private List<ImageView> imgs_plyrs = new ArrayList<>(Const.DEF_NUM_PLAYERS);
    private List<Player> players = new ArrayList<>(Const.DEF_NUM_PLAYERS);

    private int scr_width;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

        initGameLogic();
        initComponents();
    }

    // Add layout components here...
    private void initComponents()
    {
        txt_points.add((TextView) findViewById(R.id.score_plyr_1));
        txt_points.add((TextView) findViewById(R.id.score_plyr_2));

        imgs_plyrs.add((ImageView) findViewById(R.id.img_1));
        imgs_plyrs.add((ImageView) findViewById(R.id.img_2));


        for (int id = 0; id < Const.DEF_NUM_PLAYERS; id++)
        {
            //imgs_plyrs.get(id).setOnClickListener(new ClkListener(id));
            imgs_plyrs.get(id).setOnTouchListener(new TouchListener(id));
            txt_points.get(id).setText((CharSequence) Integer.toString(players.get(id).getPoints()));
        }

    }

    // Initialize attributes here...
    private void initGameLogic()
    {
        scr_width  = Utilities.getScreenWidth(this);

        for(int id = 0; id < Const.DEF_NUM_PLAYERS; id++)
        {
            Player player = new Player(id);
            player.setOnPointsChangedListener(this);

            players.add(player);

        }
    }


    @Override
    public void OnChangedPoints(int id)
    {
        txt_points.get(id).setText((CharSequence) Integer.toString(players.get(id).getPoints()));
    }


    // Inner classes

    public class ClkListener implements View.OnClickListener
    {
        private int _id;

        public ClkListener(int id) { _id = id; }

        @Override
        public void onClick(View v)
        {
            players.get(_id).add();
        }

    }

    public class TouchListener implements View.OnTouchListener {

        private int _id;
        private float s_x;
        private boolean hasMoved;

        public TouchListener(int id)
        {
            _id = id;
            s_x = Float.MIN_VALUE;
            hasMoved = false;
        }

        @Override
        @TargetApi(12) // event.getAxisValue not available on SDK version 11. (>= 12)
        public boolean onTouch(View v, MotionEvent event)
        {
            // UP
            if(event.getAction() == MotionEvent.ACTION_UP)
            {
                if (!hasMoved)
                {
                    if (event.getX() > ((float) scr_width) / 2) players.get(_id).add();
                    else players.get(_id).rem();
                }
                else
                {
                    float diff = event.getX() - s_x;

                    // If significant right-swype happens
                    if ( diff > 0 && Math.abs(diff) > scr_width * Const.SWYPE_ERROR_TOLERANCE )
                    {
                        players.get(_id).add();
                    }
                    // If significant left-swype happens
                    if ( diff < 0 && Math.abs(diff) > scr_width * Const.SWYPE_ERROR_TOLERANCE )
                    {
                        players.get(_id).rem();
                    }
                }

                hasMoved = false;
            }

            // DOWN
            if(event.getAction() == MotionEvent.ACTION_DOWN)
            {
                s_x = event.getX();
            }

            // MOVE
            if(event.getAction() == MotionEvent.ACTION_MOVE)
            {
                hasMoved = true;
            }

            return true;
        }

    }

}
