package gameworld;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import gameobjects.Box;
import gameobjects.Drop;
import helper.AssetLoader;


public class GameWorld {
    private AssetLoader assetLoader;
    private Scroller scroller;

    private int gameWidth, gameHeight;
    private int boxLength, dropRadius, boxX, dropX, boxY, dropY, boxGap, score, level;

    private List<Box> boxes;
    private List<Drop> drops;

    private GameState currentState;
    private Random r;

    private List<Color> colors;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE, PAUSED, RESUMING;
    }

    public GameWorld(int gameWidth, int gameHeight) {
        scroller = new Scroller(this    );
        currentState = GameState.READY;
        assetLoader.load();
        r = new Random();
        this.score  = 0;
        this.level = 1;

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        this.boxLength = 150;
        this.dropRadius = 40;
        //X location for the first box
        //X location for the first drop (midpoint of first box)
        this.boxX = 125;
        this.dropX = boxX + boxLength/2;

        this.boxY = 1550;
        this.dropY = 150;

        this.boxGap = 20;

        colors = new ArrayList<Color>();

        colors.add(Color.TEAL);
        colors.add(Color.CORAL);
        colors.add(Color.OLIVE);
        colors.add(Color.PINK);
        colors.add(Color.SCARLET);

        //Generate Boxes
        boxes = new ArrayList<Box>();
        boxes.add(new Box(boxX, boxY, boxLength, colors.get(0)));
        boxes.add(new Box(boxes.get(0).getX()+boxLength + boxGap, boxY, boxLength, colors.get(1)));
        boxes.add(new Box(boxes.get(1).getX()+boxLength + boxGap, boxY, boxLength, colors.get(2)));
        boxes.add(new Box(boxes.get(2).getX()+boxLength + boxGap, boxY, boxLength, colors.get(3)));
        boxes.add(new Box(boxes.get(3).getX()+boxLength + boxGap, boxY, boxLength, colors.get(4)));

        //Generate Drops
        drops = new ArrayList<Drop>();
        drops.add(new Drop(dropX, dropY, dropRadius, colors.get(0)));
        drops.add(new Drop(drops.get(0).getX()+boxLength + boxGap, dropY, dropRadius, colors.get(1)));
        drops.add(new Drop(drops.get(1).getX()+boxLength + boxGap, dropY, dropRadius, colors.get(2)));
        drops.add(new Drop(drops.get(2).getX()+boxLength + boxGap, dropY, dropRadius, colors.get(3)));
        drops.add(new Drop(drops.get(3).getX()+boxLength + boxGap, dropY, dropRadius, colors.get(4)));

    }

    public void update(float delta){
        switch (currentState){
            case READY:
                break;
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
        if(score % 1 /*10*/ == 0){
            nextLevel();
        }
        assetLoader.scorePoint.play();
    }

    public void resetDropColors(){
        Collections.shuffle(colors);
        for(int i = 0; i< colors.size(); i ++){
            drops.get(i).setDropColor(colors.get(i));
        }
    }

    public void swapBoxes(Box box1, Box box2){
        Color temp = box1.getBoxColor();
        box1.setBoxColor(box2.getBoxColor());
        box2.setBoxColor(temp);
    }

    public void nextLevel(){
        level++;
        switch(level){
            case 2:
                scroller.resetScrollSpeed();
                setNewColors(0f, 0f, 1f);
                break;
            case 3:
                setNewColors(0f, 0f, .75f);
                //Do level 3 stuff
                break;
            case 4:
                setNewColors(0f, 0f, .50f);
                //Do level 4 stuff
                break;
            case 5:
                setNewColors(0f, 0f, .25f);
                //Do level 5 stuff
            case 6:
                setNewColors(0f, .0f, .325f);
                //Do level 6 stuff
                break;
            default:
                setNewColors(0f, .0f, .325f);
                break;

        }
    }

    public void setNewColors(float base, float min, float max){
        for(int i = 0; i<colors.size(); i++){
            colors.set(i, new Color(base + r.nextFloat()*(max - min) + min, base+r.nextFloat()*(max - min) + min, base+r.nextFloat()*(max - min) + min, 1));
            boxes.get(i).setBoxColor(colors.get(i));
            drops.get(i).setDropColor(colors.get(i));
            i++;
        }
    }

    public void gameOver(){
        currentState = GameState.GAMEOVER;
    }

    public void newGame(){
        this.score  = 0;
        this.level = 1;

        colors.set(0, Color.TEAL);
        colors.set(1, Color.CORAL);
        colors.set(2, Color.OLIVE);
        colors.set(3, Color.PINK);
        colors.set(4, Color.SCARLET);

        for(int i = 0; i<boxes.size(); i++){
            boxes.get(i).setBoxColor(colors.get(i));
            drops.get(i).setDropColor(colors.get(i));
        }

        currentState = GameState.RUNNING;
    }

    public int getScore() {
        return score;
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

    public List<Color> getColors(){
        return colors;
    }

    public GameState getCurrentState(){
        return currentState;
    }
}
