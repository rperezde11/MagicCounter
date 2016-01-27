package cueva.magiccounter;


public class DiceData {

    private int number;
    private float x;
    private float y;

    public DiceData(float x, float y, int number)
    {
        this.x = x;
        this.y = y;
        this.number = number;
    }



    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

}
