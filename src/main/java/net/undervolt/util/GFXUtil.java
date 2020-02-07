package net.undervolt.util;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class GFXUtil {

    public void drawRect(float x, float y, float w, float h, Color c) {
        glBegin(GL_QUADS);
            glColor4d(c.getRed() / 255.0f, c.getGreen() / 255.0f, c.getBlue() / 255.0f, c.getAlpha() / 255.0f);
            glVertex2f(x, y);
            glVertex2f(x + w, y);
            glVertex2f(x + w, y + h);
            glVertex2f(x, y + h);
        glEnd();
        glFlush();
    }

    public static void drawTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        float f = 1.0F / textureWidth;
        float f1 = 1.0F / textureHeight;
        glBegin(GL_QUADS);
        glTexCoord2f(0, 1);
        glVertex2f(x, y + height);
        glTexCoord2f(1, 1);
        glVertex2f(x + width, y + height);
        glTexCoord2f(1, 0);
        glVertex2f(x + width, y);
        glTexCoord2f(0, 0);
        glVertex2f(x, y);
        glEnd();
    }

}
