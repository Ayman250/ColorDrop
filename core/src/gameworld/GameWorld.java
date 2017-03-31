package gameworld;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Box;
import gameobjects.Drop;


public class GameWorld {
    private Scroller scroller;

    private int gameWidth, gameHeight;
    private int boxLength, dropRadius, boxX, dropX, boxY, dropY, boxGap, score;

    private List<Box> boxes;
    private List<Drop> drops;

    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE, PAUSED, RESUMING;
    }

    public GameWorld(int gameWidth, int gameHeight) {
        scroller = new Scroller(this    );
        currentState = GameState.READY;
        this.score  = 0;

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        this.boxLength = 150;
        this.dropRadius = 40;
        //X location for the first box
        //X location for the first drop (midpoint of first box)
        this.boxX = 125;
        this.dropX = boxX + boxLength/2;

        this.boxY = 1350;
        this.dropY = 200;

        this.boxGap = 20;

        //Generate Boxes
        boxes = new ArrayList<Box>();
        boxes.add(new Box(boxX, boxY, boxLength, Color.TEAL));
        boxes.add(new Box(boxes.get(0).getX()+boxLength + boxGap, boxY, boxLength, Color.CORAL));
        boxes.add(new Box(boxes.get(1).getX()+boxLength + boxGap, boxY, boxLength, Color.OLIVE));
        boxes.add(new Box(boxes.get(2).getX()+boxLength + boxGap, boxY, boxLength, Color.PINK));
        boxes.add(new Box(boxes.get(3).getX()+boxLength + boxGap, boxY, boxLength, Color.SCARLET));

        //Generate Drops
        drops = new ArrayList<Drop>();
        drops.add(new Drop(dropX, dropY, dropRadius, Color.TEAL));
        drops.add(new Drop(drops.get(0).getX()+boxLength + boxGap, dropY, dropRadius, Color.CORAL));
        drops.add(new Drop(drops.get(1).getX()+boxLength + boxGap, dropY, dropRadius, Color.PINK));
        drops.add(new Drop(drops.get(2).getX()+boxLength + boxGap, dropY, dropRadius, Color.OLIVE));
        drops.add(new Drop(drops.get(3).getX()+boxLength + boxGap, dropY, dropRadius, Color.SCARLET));

    }

    public void update(float delta){
        switch (currentState){
            case READY:
            case MENU:
            case RUNNING:
                scroller.updateDrops(delta);
            case GAMEOVER:
            case HIGHSCORE:
            case PAUSED:
            case RESUMING:
        }
    }

    public void addPoints(int points){
        score+=points;
    }

    public int getScore() {
        return score;
    }

    public void swapBoxes(Box box1, Box box2){
        Color temp = box1.getBoxColor();
        box1.setBoxColor(box2.getBoxColor());
        box2.setBoxColor(temp);
    }

    public int getGameWidth() {
        return gameWidth;
    }

    public int getGameHeight() {
        return gameHeight;
    }

    public List<Box> getBoxes(){
        return boxes;
    }

    public List<Drop> getDrops() { return drops; }

}
