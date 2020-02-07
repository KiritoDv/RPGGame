package net.undervolt.map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hackoeur.jglm.Mat;
import com.hackoeur.jglm.Vec3;
import net.undervolt.camera.Camera;
import net.undervolt.engine.Block;
import net.undervolt.engine.Material;
import net.undervolt.util.Display;

import java.util.HashMap;
import java.util.List;

public class Map {

    private static Map instance = new Map();

    private List<Block> blocks = Lists.newArrayList();

    public void setBlock(Vec3 loc, Material m){
        Block b = new Block(loc, m);
        if(!this.blocks.contains(b)) this.blocks.add(b);
    }

    public void setBlock(Block b){
        if(!this.blocks.contains(b)) this.blocks.add(b);
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public static Map getInstance() {
        return instance;
    }

}
