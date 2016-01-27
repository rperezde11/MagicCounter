package cueva.magiccounter;

import android.content.Context;
import android.widget.ImageView;

public class Dice{

    private ImageView imgDice;
    private int number;

    public Dice (ImageView imgDice, int number)
    {
        this.imgDice = imgDice;
        this.number = number;
    }

    public Dice (Context context, int id, int number)
    {
        this.imgDice = Utilities.getImage(context,id);
        this.number = number;
    }

    public ImageView getImgDice() {
        return imgDice;
    }

    public int getNumber() {
        return number;
    }
}
