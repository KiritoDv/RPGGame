package net.undervolt.loaders;

import com.hackoeur.jglm.Vec3;
import net.undervolt.engine.Block;
import net.undervolt.engine.Material;
import net.undervolt.engine.ResLocation;
import net.undervolt.util.Image;
import org.apache.commons.io.FilenameUtils;
import org.mapeditor.core.Map;
import org.mapeditor.core.MapLayer;
import org.mapeditor.core.Tile;
import org.mapeditor.core.TileLayer;
import org.mapeditor.io.TMXMapReader;

public class MapLoader {

    private final static MapLoader instance = new MapLoader();
    private TMXMapReader tmr = new TMXMapReader();
    private net.undervolt.map.Map map = net.undervolt.map.Map.getInstance();

    public void loadMap(ResLocation path){

        try {
            Map m = tmr.readMap(path.getAsStream());
            for (int i = 0; i < m.getLayers().size(); i++) {
                MapLayer l = m.getLayer(i);
                if(l instanceof TileLayer){
                    TileLayer tl = (TileLayer)l;

                    for(int x = 0; x < l.getWidth(); x++){
                        for(int y = 0; y < l.getHeight(); y++){
                            Tile tile = tl.getTileAt(x, y);
                            if(tile == null) continue;

                            int finalI = i;
                            tl.getProperties().keySet().forEach(p -> {
                                System.out.println(finalI +": "+p+": "+tl.getProperties().getProperty(p));
                            });

                            Block b = new Block(new Vec3(x, y, Integer.parseInt(tl.getProperties().getProperty("layer-z", "0"))), Material.getMaterial(FilenameUtils.getBaseName(tile.getImageData().getSource())));

                            tile.getProperties().keySet().forEach(k -> {
                                switch (k.toLowerCase()){
                                    case "color": {

                                    }
                                }
                            });

                            map.setBlock(b);
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MapLoader getInstance() {
        return instance;
    }
}
