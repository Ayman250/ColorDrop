package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import gameworld.GameRenderer;
import gameworld.GameWorld;
import helper.InputHandler;


public class GameScreen implements Screen{
    private static final float MIN_FRAME_LENGTH = 1f/120f;
    private float timeSinceLastRender;

    private GameWorld world;
    private GameRenderer renderer;
    private InputHandler inputHandler
;
    private int gameWidth, screenWidth;
    private int gameHeight, screenHeight;
    private float runTime;

    public GameScreen(){
        screenWidth = Gdx.graphics.getWidth();
        screenHeight = Gdx.graphics.getHeight();

        gameWidth = 1080;
        gameHeight = 1920;

        world = new GameWorld(gameWidth, gameHeight);
        renderer = new GameRenderer(world);
        Gdx.input.setInputProcessor(new InputHandler(world, (float)gameWidth/screenWidth, (float)gameHeight/screenHeight));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        /*Only render if it's been 1/60 of a second since last render.
        Cap FPS at 60
        */
        timeSinceLastRender += delta;
//        if (timeSinceLastRender >= MIN_FRAME_LENGTH) {
            world.update(delta);
            runTime+= delta;
            renderer.render(delta, runTime);
            timeSinceLastRender = 0f;
//        }

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
