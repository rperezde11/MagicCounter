package cueva.magiccounter;

import android.content.Context;
import android.content.res.Resources;

public class Image {

    private String type;
    private String theme;
    private int id;


    public Image(String type, String theme, int id)
    {
        this.type = type;
        this.theme = theme;
        this.id = id;
    }

    public Image(Context context, String type, String theme)
    {
        this.type = type;
        this.theme = theme;
        this.id = context.getResources().getIdentifier(theme+"_"+type,"drawable",context.getPackageName());
    }


    /*********************************
     *       GETTERS & SETTERS
     *********************************/

    public String getType(){ return this.type; }

    public String getTheme() { return this.theme; }

    public int getId(){ return id; }

}
