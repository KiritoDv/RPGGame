package net.undervolt.util;

import net.undervolt.engine.ResLocation;
import org.lwjgl.glfw.GLFWImage;

import static org.lwjgl.glfw.GLFW.glfwSetWindowIcon;

public class Display {

    private static Display instance = new Display();
    private Display(){}

    private int windowWidth;
    private int windowHeight;

    public void initDisplay(int width, int height, long window){
        this.windowWidth = width;
        this.windowHeight = height;

        Image logo = new Image(new ResLocation("icon512.png"));

        GLFWImage.Buffer gb = GLFWImage.create(1);
        GLFWImage iconGI = GLFWImage.create().set(logo.getWidth(), logo.getHeight(), logo.getData());
        gb.put(0, iconGI);

        glfwSetWindowIcon(window, gb);
    }

    public void resizeDisplay(int nWidth, int nHeight){
        this.windowWidth = nWidth;
        this.windowHeight = nHeight;
    }

    public static Display getInstance() {
        return instance;
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}
