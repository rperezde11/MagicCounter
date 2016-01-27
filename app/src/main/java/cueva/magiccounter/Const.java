package cueva.magiccounter;

public final class Const
{

    public Const ()
    {
        // Empty constructor
    }

    public static final int SPLASH_INTRO_DELAY = 5000; // Time the intro lasts in milliseconds.
    public static final int SPLASH_LOGO_N_FRAMES = 20; // Time the intro lasts in milliseconds.
    public static final float STEP = 0.001f;
    public static final float SPLASH_MIN_SCALE = 0.4f;
    public static final float SPLASH_MAX_SCALE = 0.45f;

    public static final int MAX_COLORS = 6;
    public static final int MEAN_DICE_ROLLS = 6;
    public static final int DEF_NUM_PLAYERS = 2;
    public static final int DEF_NUM_POINTS = 20;
    public static final int DEF_THEME = 0;          // INDEX TO THEME NAMES

    public static final float SWYPE_ERROR_TOLERANCE = 0.2f;


    public static final String[] TYPE_NAMES = { "white" , "green" , "red" , "blue" , "black" , "grey" };
    public static final String[] THEME_NAMES =  { "art1" , "art2" };

}
