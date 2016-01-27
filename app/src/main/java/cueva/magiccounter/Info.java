package cueva.magiccounter;

import java.util.ArrayList;
import java.util.List;

public class Info extends SingletonInfo{

    private List<Image> images;
    private List<Player> players;

    protected Info()
    {
        images = new ArrayList<>();
        players = new ArrayList<>(Const.DEF_NUM_PLAYERS);

        fillImageList();
        fillPlayersList();
    }

    private void fillImageList()
    {
        for (String theme : Const.THEME_NAMES)
        {
            for (String type : Const.TYPE_NAMES)
            {
                images.add(new Image(MainActivity.context, type , theme));
            }
        }
    }

    public List<Image> getImgList (String type)
    {
        List<Image> aux_images = new ArrayList<>();

        for (Image image : images) {
            if(image.getType().equals(type)) aux_images.add(image);
        }

        return aux_images;
    }

    public List<Image> getTypeImageList (List<Image> _images, String type)
    {
        List<Image> aux_images = new ArrayList<>();

        for (Image image : _images) {
            if(image.getType().equals(type)) aux_images.add(image);
        }

        return aux_images;
    }

    public List<Image> getThemeImageList (List<Image> _images, String theme)
    {
        List<Image> aux_images = new ArrayList<>();

        for (Image image : _images) {
            if(image.getTheme().equals(theme)) aux_images.add(image);
        }

        return aux_images;
    }

    public Image getImage (String type, String theme)
    {
        return getThemeImageList(getTypeImageList(images,type),theme).get(0);
    }

    public List<Integer> idList (String type)
    {
        List<Integer> aux_images = new ArrayList<>();

        for (Image image : images) {
            if(image.getType().equals(type)) aux_images.add(image.getId());
        }

        return aux_images;
    }

    public int getId (String type, String theme)
    {
        int res = Integer.MIN_VALUE;
        int counter = 0;
        boolean found = false;

        while (!found && counter < images.size())
        {
            Image image = images.get(counter);
            if(image.getTheme() == theme && image.getType() == type)
            {
                res = image.getId();
                found = true;
            }

            counter++;
        }

        return res;
    }



    private void fillPlayersList()
    {
        for(int id = 0; id < Const.DEF_NUM_PLAYERS; id++)
        {
            Player player = new Player(id);
            players.add(player);
        }
    }

    public Player getPlayer(int id)
    {
        for(Player player : players)
        {
            if(player.getId() == id)
                return player;
        }

        return null;
    }

    public List<Player> getPlayers()
    {
        return players;
    }
}
