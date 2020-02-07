package net.undervolt.engine;

import com.hackoeur.jglm.Vec3;

import java.security.SecureRandom;
import java.util.Random;

public class Block {

    private Vec3 location;
    private IColor color;
    private Material material;

    public Block(Vec3 location, Material material){
        this.location = location;
        this.material = material;
        this.color = this.genColor();
    }

    public Vec3 getLocation() {
        return location;
    }

    public IColor getColor() {
        return color;
    }

    public Material getMaterial() {
        return material;
    }

    private IColor genColor(){

        SecureRandom sr = new SecureRandom();
        Random r = new Random();
        sr.setSeed(r.nextInt());
        int c = Math.round(sr.nextFloat() * 13);

        return new IColor(255 - c, 255 - c, 255 - c);
    }
}
