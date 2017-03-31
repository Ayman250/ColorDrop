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

}
