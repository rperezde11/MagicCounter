package cueva.magiccounter;


public abstract class SingletonInfo {

    private static Info instance = null;

    public SingletonInfo(){}

    public static Info get()
    {
        if(instance == null)
        {
            instance = new Info();
        }

        return instance;
    }

    public static void clear()
    {
        instance = null;
    }
}
