package net.undervolt.engine;

public enum Material {
    NULL("null", new ResLocation("textures/blocks/null.png"), -1),
    GRASS("grass", new ResLocation("textures/blocks/grass.png"), 0),
    STONE("stone", new ResLocation("textures/blocks/stone.png"), 1),
    DUMMY("dummy", new ResLocation("textures/blocks/dummy.png"), 2),
    DIRT("dirt", new ResLocation("textures/blocks/dirt.png"), 3),
    OAK("oak", new ResLocation("textures/blocks/oak.png"), 4);

    private String displayName;
    private int materialId;
    private Texture tex;

    Material(String displayName, ResLocation loc, int materialId) {
        this.displayName = displayName;
        this.materialId = materialId;
        this.tex = new Texture(loc);
    }

    public static Material getMaterial(String value){
        for (Material m : Material.values()) {
            if(m.getDisplayName().equalsIgnoreCase(value)) return m;
        }
        return Material.NULL;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getMaterialId() {
        return materialId;
    }

    public Texture getTex() {
        return tex;
    }
}
