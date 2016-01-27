package cueva.magiccounter;

import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ImageView;

public class Utilities  {

    public static <T extends Fragment> int getScreenWidth(T t)
    {
        DisplayMetrics metrics;

        t.getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics = new DisplayMetrics());

        return metrics.widthPixels;
    }

    public static ImageView getImage(Context context, int id)
    {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(id);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        /****
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                0.5f));
         ****/

        return imageView;
    }
}
