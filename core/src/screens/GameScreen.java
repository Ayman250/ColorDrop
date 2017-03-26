package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import gameworld.GameRenderer;
import gameworld.GameWorld;


public class GameScreen implements Screen{
    private GameWorld world;
    private GameRenderer renderer;

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


    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        world.update(delta);

        runTime+= delta;
        renderer.render(delta, runTime);
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
