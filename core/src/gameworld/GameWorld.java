package gameworld;

import com.badlogic.gdx.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import gameobjects.Box;
import gameobjects.Drop;


public class GameWorld {

    private int gameWidth, gameHeight;
    private int boxLength;

    private List<Box> boxes;
    private List<Drop> drops;

    private GameState currentState;

    public enum GameState {
        MENU, READY, RUNNING, GAMEOVER, HIGHSCORE, PAUSED, RESUMING;
    }

    public GameWorld(int gameWidth, int gameHeight) {
        currentState = GameState.READY;

        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;

        this.boxLength = 150;

        //Generate Boxes
        boxes = new ArrayList<Box>();
        boxes.add(new Box(165, 400, boxLength, Color.FOREST));
        boxes.add(new Box(boxes.get(0).getX()+boxLength, 400, boxLength, Color.BLUE));
        boxes.add(new Box(boxes.get(1).getX()+boxLength, 400, boxLength, Color.YELLOW));
        boxes.add(new Box(boxes.get(2).getX()+boxLength, 400, boxLength, Color.PINK));
        boxes.add(new Box(boxes.get(3).getX()+boxLength, 400, boxLength, Color.SCARLET));

    }

    public void update(float delta){
        switch (currentState){
            case READY:
            case MENU:
            case RUNNING:
            case GAMEOVER:
            case HIGHSCORE:
            case PAUSED:
            case RESUMING:
        }
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

}
