package net.undervolt;

import net.undervolt.camera.Camera;
import net.undervolt.renderer.Game;
import net.undervolt.util.Display;
import org.lwjgl.glfw.GLFWFramebufferSizeCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.opengl.GL;

import java.awt.*;
import java.util.logging.Logger;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Main {

    private int width = 1280;
    private int height = 720;
    private boolean fullscreen = false;
    private Logger log = Logger.getLogger("UnderVolt");

    public void runGame(){

        if(!glfwInit()){
            log.severe("Failed to start GLFW");
            glfwTerminate();
        }

        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 2);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 0);

        long window = glfwCreateWindow(width, height, "RPGGame", NULL, NULL);

        if(window == NULL){
            log.severe("Failed to create window");
            glfwDestroyWindow(window);
            return;
        }

        glfwMakeContextCurrent(window);

        Display.getInstance().initDisplay(this.width, this.height, window);
        Game g = Game.getInstance();
        Camera cam = Camera.getInstance();

        glfwSwapInterval(0);

        glfwSetFramebufferSizeCallback(window, new GLFWFramebufferSizeCallback() {
            @Override
            public void invoke(long window, int width, int height) {

                Display.getInstance().resizeDisplay(width, height);

                Main.this.width = width;
                Main.this.height = height;
            }
        });

        glfwSetKeyCallback(window, new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {

            }
        });

        GL.createCapabilities();

        cam.initCamera();
        g.init();

        float previous = (float) glfwGetTime();

        while(!glfwWindowShouldClose(window)){

            float now = (float) glfwGetTime();
            float delta = (now - previous) * 1000;
            previous = now;

            cam.updateCamera(delta, window);

            Color bG = new Color(100, 149, 255);

            glClearColor(bG.getRed() / 255.0f, bG.getGreen() / 255.0f, bG.getBlue() / 255.0f, bG.getAlpha() / 255.0f);
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            glEnable(GL_TEXTURE_2D);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            g.draw();

            glDisable(GL_TEXTURE_2D);
            glEnable(GL_BLEND);

            glfwSwapBuffers(window);
            glfwPollEvents();
        }

        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);
        glfwTerminate();
    }


    public static void main(String[] args) {
        new Main().runGame();
    }

}
