package cueva.magiccounter;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    /* Java Attributes */

    public static Context context;

    private int scr_width;

    private Info info;

    private List<PlayerFragment> fragments;


    /* Layout Components */
    private ImageButton button_menu;
    private LinearLayout layout_menu;

    private ImageButton button_exit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initGameLogic();
        generateMainLayout();
        initComponents();

        Info info = Info.get();

    }



    private void generateMainLayout()
    {
        displayFragment(0, "fragment0", 180, true);
        displayFragment(1, "fragment1", 0, false);
    }


    private void displayFragment(int id, String tag, int degrees, boolean up)
    {
        PlayerFragment fragment = new PlayerFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("player_id", id);
        bundle.putInt("degrees", degrees);
        bundle.putBoolean("up", up);
        fragment.setArguments(bundle);

        android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction = transaction.add(R.id.layout_container, fragment, tag);
        transaction.commit();

        fragments.add(fragment);
    }


    // Add layout components here...
    private void initComponents()
    {
        // Define components
        button_menu = (ImageButton) findViewById(R.id.button_menu);
        layout_menu = (LinearLayout) findViewById(R.id.layout_menu_inside);

        button_exit = (ImageButton) findViewById(R.id.quit);
        //button_dice = (ImageButton) findViewById(R.id.dice);
        //button_conf = (ImageButton) findViewById(R.id.configuration);


        // Define listeners
        button_menu.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                layout_menu.setVisibility(View.VISIBLE);
                button_menu.setVisibility(View.GONE);
            }
        });

        button_menu.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                for (Player player : info.getPlayers()) {
                    player.setDefaultPoints();
                }

                return true;
            }
        });

        button_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout_menu.setVisibility(View.GONE);
                button_menu.setVisibility(View.VISIBLE);
            }
        });

        button_menu.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v)
            {
                for (PlayerFragment fragment : fragments) {
                    fragment.refresh();
                }

                return true;
            }
        });

    }

    // Initialize attributes here...
    private void initGameLogic()
    {
        context = getApplicationContext();

        fragments = new ArrayList<>();

        info = Info.get();
    }
}
