package gameobjects;

import com.badlogic.gdx.graphics.Color;

public class Drop {
    private float x, y, initY;
    private int radius;
    private Color dropColor;

    public Drop(float x, float y, int radius, Color dropColor) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.dropColor = dropColor;
        this.initY = y;
    }

    public void resetY(){
        this.y = initY;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public Color getDropColor() {
        return dropColor;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setDropColor(Color newColor){
        this.dropColor = newColor;
    }

}
