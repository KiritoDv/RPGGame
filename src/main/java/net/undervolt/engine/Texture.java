package net.undervolt.engine;

import com.google.common.collect.Maps;
import net.undervolt.util.Image;

import java.util.Map;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;

public class Texture {

    private static Map<ResLocation, Texture> texCache = Maps.newHashMap();

    private int id;
    private int width;
    private int height;

    public Texture(ResLocation path){
        if(!texCache.containsKey(path)){
            this.id = glGenTextures();

            Image i = new Image(path);

            this.width = i.getWidth();
            this.height = i.getHeight();

            glBindTexture(GL_TEXTURE_2D, this.id);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

            glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, i.getWidth(), i.getHeight(), 0, GL_RGBA, GL_UNSIGNED_BYTE, i.getData());

            glBindTexture(GL_TEXTURE_2D, 0);
            texCache.put(path, this);
        }else{
            Texture tmp = texCache.get(path);
            this.width = tmp.getWidth();
            this.height = tmp.getHeight();
            this.id = tmp.getId();
        }
    }

    public void bindTexture(){
        glActiveTexture(GL_TEXTURE0);
        glBindTexture(GL_TEXTURE_2D, this.id);
    }

    public int getId() {
        return id;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
