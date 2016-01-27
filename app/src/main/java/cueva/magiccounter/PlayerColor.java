package cueva.magiccounter;


public class PlayerColor {

    private String color;
    private boolean ticked;

    public PlayerColor(String color, boolean ticked)
    {
        this.color = color;
        this.ticked = ticked;
    }


    public String getColor()
    {
        return this.color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public boolean isTicked()
    {
        return this.ticked;
    }

    public void setTicked(boolean ticked)
    {
        this.ticked = ticked;
    }

}
