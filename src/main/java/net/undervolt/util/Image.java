package net.undervolt.util;

import net.undervolt.engine.ResLocation;
import org.lwjgl.BufferUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.ByteBuffer;

public class Image {
    private int width;
    private int height;
    private ByteBuffer data;

    public Image(ResLocation path){

        try{
            BufferedImage image = ImageIO.read(path.getAsStream());
            this.width = image.getWidth();
            this.height = image.getHeight();

            int[] pixels = new int[width * height];
            image.getRGB(0, 0, width, height, pixels, 0, width);

            ByteBuffer buffer = BufferUtils.createByteBuffer(width * height * 4);

            for(int y = 0; y < height; ++y) {
                for(int x = 0; x < width; ++x) {
                    int pixel = pixels[x + y * width];
                    buffer.put((byte) ((pixel >> 16) & 0xFF));
                    buffer.put((byte) ((pixel >> 8) & 0xFF));
                    buffer.put((byte) (pixel & 0xFF));
                    buffer.put((byte) ((pixel >> 24) & 0xFF));
                }
            }

            buffer.flip();

            this.data = buffer;

            System.out.println("Loading Texture: " + path.getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ByteBuffer getData() {
        return data;
    }
}
