package cueva.magiccounter;

public class MenuEvents {

    // public static enum Events  { REFRESH , DICE , OTHERS }

    public interface OnMenuOptionSelected {

        void refresh();
        void diceTriggered();

    }

}
