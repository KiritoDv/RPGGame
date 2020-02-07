package net.undervolt.renderer;

import com.hackoeur.jglm.Vec3;
import net.undervolt.camera.Camera;
import net.undervolt.engine.Material;
import net.undervolt.engine.ResLocation;
import net.undervolt.loaders.MapLoader;
import net.undervolt.map.Map;
import net.undervolt.util.Display;
import net.undervolt.util.GFXUtil;

import java.util.Comparator;

public class MapRenderer {

    private static MapRenderer instance = new MapRenderer();
    private MapLoader mapLoader = MapLoader.getInstance();
    private Map map = Map.getInstance();

    public void initMap(){
        mapLoader.loadMap(new ResLocation("maps/Test_1.tmx"));
    }

    public void drawMap(){
        Display dManager = Display.getInstance();

        map.getBlocks().stream().filter((b)->{
            Vec3 pos = Camera.getInstance().getPosition();
            int tW = 32;
            int tH = 32;
            int x = Math.round(b.getLocation().getX() - b.getLocation().getY()) * (tW / 2);
            int y = Math.round((((b.getLocation().getX() + b.getLocation().getY()) * (tH / 4))) - ((b.getLocation().getZ() * (tH / 2))));

            return (x + tW > pos.getX() && y + tH > pos.getY() && x <= (pos.getX() + dManager.getWindowWidth()) && y <= (pos.getY() + dManager.getWindowHeight()));
        }).sorted((a, b) -> Float.compare(a.getLocation().getX() + a.getLocation().getY() + (a.getLocation().getZ() * 2), (b.getLocation().getX() + b.getLocation().getY() + (b.getLocation().getZ() * 2)))).forEach(b -> {
            int tW = 32;
            int tH = 32;

            int x = Math.round(b.getLocation().getX() - b.getLocation().getY()) * (tW / 2);
            int y = Math.round((((b.getLocation().getX() + b.getLocation().getY()) * (tH / 4))) - ((b.getLocation().getZ() * (tH / 2))));

            b.getMaterial().getTex().bindTexture();
            b.getColor().bindColor();

            GFXUtil.drawTexture(x, y, 0, 0, tW, tH, tW, tH);
        });
    }

    public static MapRenderer getInstance() {
        return instance;
    }

}
