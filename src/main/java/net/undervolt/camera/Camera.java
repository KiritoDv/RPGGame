package net.undervolt.camera;

import com.hackoeur.jglm.Vec3;
import net.undervolt.util.Display;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.opengl.GL11.*;

public class Camera {

    private static Camera instance = new Camera();

    private Display display = Display.getInstance();
    private Vec3 position = new Vec3(0, 0, 0);

    public void initCamera(){
        float cW = position.getX() + display.getWindowWidth();
        float cH = position.getY() + display.getWindowHeight();

        glMatrixMode(GL_PROJECTION);
        glViewport(0, 0, display.getWindowWidth(), display.getWindowHeight());
        glLoadIdentity();
        glOrtho(position.getX(), cW, cH, position.getY(), 0.0, 1.0);
    }

    public void updateCamera(float delta, long window){
        float speed = delta;

        if (glfwGetKey(window, GLFW_KEY_W) == GLFW_PRESS) {
            this.position = this.position.add(new Vec3(0, -speed, 0));
        }
        if (glfwGetKey(window, GLFW_KEY_S) == GLFW_PRESS) {
            this.position = this.position.add(new Vec3(0, speed, 0));
        }
        if (glfwGetKey(window, GLFW_KEY_A) == GLFW_PRESS) {
            this.position = this.position.add(new Vec3(-speed, 0, 0));
        }
        if (glfwGetKey(window, GLFW_KEY_D) == GLFW_PRESS) {
            this.position = this.position.add(new Vec3(speed, 0, 0));
        }

        this.initCamera();
    }

    public Display getDisplay() { return Display.getInstance(); }

    public Vec3 getPosition() {
        return position;
    }

    public static Camera getInstance() {
        return instance;
    }
}
