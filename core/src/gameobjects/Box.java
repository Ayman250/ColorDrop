package gameobjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;

public class Box {
    private int boxLength;
    private Rectangle hitBox;
    private float x, y;
    private Color boxColor;
    public Box(float x, float y, int boxLength, Color boxColor){
        this.boxLength = boxLength;
        hitBox = new Rectangle(x,y, boxLength, boxLength);
        this.x = x;
        this.y = y;
        this.boxColor = boxColor;

    }

    public boolean isTouchUp(float x, float y){
        return hitBox.contains(x, y);
    }

    public boolean isTouchDown(float x, float y){
        return hitBox.contains(x, y);
    }

    public float getLength() {
        return boxLength;
    }

    public Rectangle getHitBox() {
        return hitBox;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public Color getBoxColor(){
        return  boxColor;
    }

    public int getBoxLength(){
        return boxLength;
    }

    public void setBoxColor(Color newColor){
        this.boxColor = newColor;
    }

    public void setX(float newX){
        this.x = newX;
    }

    public void setY(float newY){
        this.y = newY;
    }

}
