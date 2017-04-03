package helper;

import com.badlogic.gdx.InputProcessor;

import gameobjects.Box;
import gameworld.GameWorld;

public class InputHandler implements InputProcessor{
    private Box pressedBox;
    private GameWorld world;
    private float scaleX, scaleY;

    public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY){
        this.pressedBox = null;
        this.world = world;
        this.scaleX = scaleFactorX;
        this.scaleY = scaleFactorY;
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println(screenX * scaleX + " " + screenY * scaleY);
        for(Box box:world.getBoxes()){
            if(box.isTouchUp(screenX * scaleX, screenY * scaleY)) {
                System.out.println("Box Pressed");
                if (pressedBox == box) break;
                if (pressedBox == null){
                    pressedBox = box;
                    pressedBox.setY(pressedBox.getY() - 20);
                }
                else{
                    world.swapBoxes(pressedBox, box);
                    pressedBox.setY(pressedBox.getY() + 20);
                    pressedBox = null;
                }
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
