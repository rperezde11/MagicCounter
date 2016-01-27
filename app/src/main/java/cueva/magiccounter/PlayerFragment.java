package cueva.magiccounter;

import android.annotation.TargetApi;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerFragment extends Fragment implements MenuEvents.OnMenuOptionSelected{

    /* Attributes */
    private float scr_width;
    private int id;
    private boolean isChooserVisible;

    private Map<ImageButton,PlayerColor> dict;

    private Info info;
    private View view;

    /* Layout Components */
    private ImageButton btn_more;
    private TextView txt_points;
    private LinearLayout layout;
    private  LinearLayout layout_chooser;
    private List<ImageView> imageViews;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.player_fragment, container, false);
        id = getArguments().getInt("player_id");

        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                                                LinearLayout.LayoutParams.MATCH_PARENT);

        if (getArguments().getBoolean("up")) {
            layoutParams.addRule(RelativeLayout.ABOVE, R.id.layout_menu);
        }else {
            layoutParams.addRule(RelativeLayout.BELOW, R.id.layout_menu);
        }
        layoutParams.setMargins(0,2,0,0);

        view.setLayoutParams(layoutParams);

        view.setRotation(getArguments().getInt("degrees"));

        initGameLogic();
        initComponents();

        return view;
    }


    // Add layout components here...
    private void initComponents()
    {
        // Define components
        //imageViews.add((ImageView) view.findViewById(R.id.img));
        btn_more = (ImageButton) view.findViewById(R.id.btn_more);
        txt_points = (TextView) view.findViewById(R.id.txt_score_plyr);
        layout = (LinearLayout) view.findViewById(R.id.layout_imgs_container);
        layout_chooser = (LinearLayout) view.findViewById(R.id.layout_chooser);

        dict.put((ImageButton)view.findViewById(R.id.btn_white), new PlayerColor("white",false));
        dict.put((ImageButton)view.findViewById(R.id.btn_green), new PlayerColor("green",false));
        dict.put((ImageButton)view.findViewById(R.id.btn_red), new PlayerColor("red",false));
        dict.put((ImageButton)view.findViewById(R.id.btn_blue), new PlayerColor("blue",false));
        dict.put((ImageButton) view.findViewById(R.id.btn_black), new PlayerColor("black", false));

        // Define listeners
        initListeners();

        txt_points.setText((CharSequence) Integer.toString(info.getPlayer(id).getPoints()));

    }

    // Initialize attributes here...
    private void initGameLogic()
    {
        scr_width  = Utilities.getScreenWidth(this);
        dict = new HashMap<ImageButton, PlayerColor>();

        imageViews = new ArrayList<ImageView>();

        info = Info.get();

        isChooserVisible = false;
    }

    public void addImage(int id_image)
    {
        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(id_image);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.5f));

        imageViews.add(imageView);

        ( (LinearLayout)view.findViewById(R.id.layout_imgs_container) ).addView(imageView);

    }

    public void removeAllImageViews()
    {
        ( (LinearLayout)view.findViewById(R.id.layout_imgs_container) ).removeAllViews();
    }

    public void refreshImages()
    {
        removeAllImageViews();

        for(int _id : info.getPlayer(id).idImgs())
        {
            addImage(_id);
        }

    }

    private void initListeners()
    {
        for(ImageButton imgBtn : dict.keySet())
        {
            imgBtn.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View v)
                {
                    ImageButton btn = (ImageButton) v;
                    PlayerColor color = dict.get(btn);

                    boolean tick = color.isTicked() ^ true;

                    color.setTicked(tick);

                    Image img = info.getImage(color.getColor(), Const.THEME_NAMES[0]);

                    if (tick)
                    {
                        info.getPlayer(id).addImage(img);
                        btn.setImageResource(R.drawable.ico_tick);
                    }
                    else
                    {
                        boolean result =  info.getPlayer(id).rmvImage(img);

                        btn.setImageResource(0);
                    }


                    refreshImages();
                }
            });
        }

        btn_more.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (isChooserVisible ^= true){
                    layout_chooser.setVisibility(View.VISIBLE);
                }
                else {
                    layout_chooser.setVisibility(View.GONE);
                }


            }
        });

        layout.setOnTouchListener(new TouchListener());
    }

    @Override
    public void refresh()
    {
        int points = info.getPlayer(id).setDefaultPoints();

        txt_points.setText(Integer.toString(points));
    }

    @Override
    public void diceTriggered() {

    }


    /*************************************************************************/

    // Inner classes

    public class TouchListener implements View.OnTouchListener {

        private float s_x;
        private boolean hasMoved;

        public TouchListener()
        {
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
                int punctuation = info.getPlayer(id).getPoints();

                if(!isChooserVisible)
                {
                    if (!hasMoved)
                    {
                        if (event.getX() > ( scr_width/2)) { punctuation = info.getPlayer(id).add(); }
                        else { punctuation = info.getPlayer(id).rem(); }
                    }
                    else
                    {
                        float diff = event.getX() - s_x;

                        // If significant right-swype happens
                        if ( diff > 0 && Math.abs(diff) > scr_width * Const.SWYPE_ERROR_TOLERANCE )
                        {
                            punctuation = info.getPlayer(id).add();
                        }
                        // If significant left-swype happens
                        if ( diff < 0 && Math.abs(diff) > scr_width * Const.SWYPE_ERROR_TOLERANCE )
                        {
                            punctuation = info.getPlayer(id).rem();
                        }
                    }
                }
                else
                {
                    isChooserVisible = false;
                    layout_chooser.setVisibility(View.GONE);
                }

                txt_points.setText(Integer.toString(punctuation));

                if(punctuation < 0) {
                    txt_points.setTextColor(Color.RED);
                }
                else {
                    txt_points.setTextColor(Color.WHITE);
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