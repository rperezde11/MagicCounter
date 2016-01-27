package cueva.magiccounter;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {

    private int id;
    private int points;
    private List<Image> listImgs;

    private CustomEvents.PointEvents listener;

    public Player(int id)
    {
        this.id = id;
        this.points = Const.DEF_NUM_POINTS;

        listImgs = new ArrayList<>();
    }


    public int add()
    {
        return ++(this.points);
    }

    public int rem()
    {
        return --(this.points);
    }

    public int setDefaultPoints()
    {
        return (this.points = Const.DEF_NUM_POINTS);
    }


    /**
     * Adds the image on drawable.
     * @param image is an int representing the id of a drawable image.
     */
    public boolean addImage(Image image)
    {
        if(!listImgs.contains(image) && !(listImgs.size() >= Const.MAX_COLORS) && !hasType(image))
            return listImgs.add(image);

        return false;
    }


    public boolean addImage(Info info, String type, String theme)
    {
        return addImage(info.getImage(type,theme));
    }

    /**
     * Removes the image based on drawable id.
     * @param image is an int representing the id of a drawable image.
     */
    public boolean rmvImage(Image image)
    {
        if(listImgs.contains(image) && listImgs.size() > 0)
            return listImgs.remove(image);

        return false;
    }

    public List<Integer> idImgs()
    {
        List<Integer> ids = new ArrayList<>();

        for (Image image: listImgs) {
            ids.add(image.getId());
        }

        return ids;
    }


    /*********************
     *      PRIVATE
     *********************/

    private boolean hasImage(Image img)
    {
        for(Image image : listImgs)
        {
            if(img == image) { return true; }
        }

        return false;
    }

    private boolean hasType(String type)
    {
        for(Image image : listImgs)
        {
            if(image.getType().equals(type)) { return true; }
        }

        return false;
    }

    private boolean hasType(Image image)
    {
        return hasType(image.getType());
    }

    /*********************
     * GETTERS and SETTERS
     *********************/

    public int getPoints()
    {
        return this.points;
    }

    public int getId() { return this.id; }
}
