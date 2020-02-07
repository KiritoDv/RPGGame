package net.undervolt.renderer;

public class Game {

    private static Game instance = new Game();
    private MapRenderer mapRenderer = MapRenderer.getInstance();

    public void init(){
        this.mapRenderer.initMap();
    }

    public void update(){

    }

    public void draw() {
        this.mapRenderer.drawMap();
    }

    public static Game getInstance() {
        return instance;
    }
}
