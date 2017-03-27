package gameobjects;

import com.badlogic.gdx.graphics.Color;

public class Drop {
    private float x, y;
    private int radius;
    private Color dropColor;

    public Drop(float x, float y, int radius, Color dropColor){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.dropColor = dropColor;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getRadius(){
        return radius;
    }

    public Color getDropColor() {
        return dropColor;
    }

}
