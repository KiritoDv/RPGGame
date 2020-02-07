package net.undervolt.engine;

import java.awt.*;
import java.awt.color.ColorSpace;

import static org.lwjgl.opengl.GL11.glColor4f;

public class IColor extends Color {
    public IColor(int r, int g, int b) {
        super(r, g, b);
    }

    public IColor(int r, int g, int b, int a) {
        super(r, g, b, a);
    }

    public IColor(int rgb) {
        super(rgb);
    }

    public IColor(int rgba, boolean hasalpha) {
        super(rgba, hasalpha);
    }

    public IColor(float r, float g, float b) {
        super(r, g, b);
    }

    public IColor(float r, float g, float b, float a) {
        super(r, g, b, a);
    }

    public IColor(ColorSpace cspace, float[] components, float alpha) {
        super(cspace, components, alpha);
    }

    public void bindColor(){
        glColor4f(this.getRed() / 255.0f, this.getGreen() / 255.0f, this.getBlue() / 255.0f, this.getAlpha() / 255.0f);
    }
}
