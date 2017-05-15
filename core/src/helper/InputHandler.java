package helper;

import com.badlogic.gdx.InputProcessor;

import gameobjects.Box;
import gameworld.GameWorld;

public class InputHandler implements InputProcessor{
    private Box pressedBox;
    private GameWorld world;
    private AssetLoader assetLoader;
    private float scaleX, scaleY;

    public InputHandler(GameWorld world, float scaleFactorX, float scaleFactorY){
        this.pressedBox = null;
        this.world = world;
        this.scaleX = scaleFactorX;
        this.scaleY = scaleFactorY;
        assetLoader.load();
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
        switch (world.getCurrentState()) {
            case READY: world.newGame();
            case RUNNING:
                for (Box box : world.getBoxes()) {
                if (box.isTouchUp(screenX * scaleX, screenY * scaleY)) {
                    assetLoader.clickBox.play();
                    if (pressedBox == null) {
                        pressedBox = box;
                        pressedBox.setY(pressedBox.getY() - 20);
                    } else {
                        world.swapBoxes(pressedBox, box);
                        pressedBox.setY(pressedBox.getY() + 20);
                        pressedBox = null;
                    }
                }
            }
            break;
            case GAMEOVER:
                world.newGame();
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
