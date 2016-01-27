package cueva.magiccounter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


import java.util.HashMap;
import java.util.Map;

public class ColorChoiceFragment  extends Fragment {


    private Map<ImageButton,PlayerColor> dict;

    private View view;
    private int id;
    private Info info;

    private ImageButton btn_white;
    private ImageButton btn_green;
    private ImageButton btn_red;
    private ImageButton btn_blue;
    private ImageButton btn_black;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.color_choice, container, false);

        RelativeLayout.LayoutParams layoutParams =
                new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 120);

        layoutParams.setMargins(35,0,35,0);
        layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL, RelativeLayout.TRUE);

        view.setLayoutParams(layoutParams);

        initAttributes();
        initListeners();

        Log.d("t", "fuckyou");

        return view;
    }


    /*****************************************************
     * INITIALIZATION
     *****************************************************/

    private void initAttributes()
    {
        dict = new HashMap<ImageButton, PlayerColor>();

        dict.put((ImageButton)view.findViewById(R.id.btn_white), new PlayerColor("white",false));
        dict.put((ImageButton)view.findViewById(R.id.btn_green), new PlayerColor("green",false));
        dict.put((ImageButton)view.findViewById(R.id.btn_red), new PlayerColor("red",false));
        dict.put((ImageButton)view.findViewById(R.id.btn_blue), new PlayerColor("blue",false));
        dict.put((ImageButton)view.findViewById(R.id.btn_black), new PlayerColor("black",false));
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
                    ImageButton btn = (ImageButton)v;
                    PlayerColor color = dict.get(btn);

                    info.getPlayer(id).addImage(info, color.getColor(), Const.THEME_NAMES[0]);

                    color.setTicked(color.isTicked() ^ true);

                    if(color.isTicked()) btn.setImageResource(R.drawable.ico_tick);
                    else btn.setImageResource(0);
                }

            });
        }
    }
}
