package cueva.magiccounter;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int id;
    private int points;
    private List<Integer> listImgs;

    private CustomEvents.PointEvents listener;

    public Player(int id)
    {
        this.id = id;
        points = Const.DEF_NUM_POINTS;
        listImgs = new ArrayList<>();
    }


    public void add()
    {
        this.points++;

        firePoints();
    }

    public void rem()
    {
        this.points--;

        firePoints();
    }


    /**
     * Adds the image on drawable.
     * @param image is an int representing the id of a drawable image.
     */
    public boolean addImage(int image)
    {
        if(!listImgs.contains(image) && !(listImgs.size() >= Const.MAX_COLORS))
            return listImgs.add(image);

        return false;
    }

    /**
     * Removes the image based on drawable id.
     * @param image is an int representing the id of a drawable image.
     */
    public boolean rmvImage(int image)
    {
        if(listImgs.contains(image))
            return listImgs.remove((Object) image);

        return false;
    }


    private void firePoints() throws NullPointerException
    {
        if (!listener.equals(null))
            listener.OnChangedPoints(this.id);
        else
            throw new NullPointerException();
    }

    /*********************
     * GETTERS and SETTERS
     *********************/

    public int getPoints()
    {
        return this.points;
    }

    public int getId()
    {
        return this.id;
    }

    public void setOnPointsChangedListener(CustomEvents.PointEvents listener)
    {
        this.listener = listener;
    }
}
