package cueva.magiccounter;

import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

public class Utilities  {

    public static int getScreenWidth(MainGame mainGame)
    {
        DisplayMetrics metrics;

        mainGame.getWindowManager().getDefaultDisplay().getMetrics(metrics = new DisplayMetrics());

        return metrics.widthPixels;
    }

}
