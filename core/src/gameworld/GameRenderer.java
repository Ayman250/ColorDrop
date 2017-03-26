package gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


import gameobjects.Box;

public class GameRenderer {
    private int gameWidth, gameHeight;

    private GameWorld world;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Rectangle background;

    public GameRenderer(GameWorld world){
        this.world = world;
        this.gameWidth = world.getGameWidth();
        this.gameHeight = world.getGameHeight();

        background = new Rectangle(0, 0, gameWidth, gameHeight);

        cam = new OrthographicCamera();
        cam.setToOrtho(true, this.gameWidth, this.gameHeight);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
    }

    public void render(float delta, float runTime){
        //Clears buffer for fresh render
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawBackground();
        drawBoxes();
        drawDrops();
    }

    private void drawBackground(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(0, 0, gameHeight, gameWidth);
        shapeRenderer.end();
    }

    private void drawBoxes(){
        for(Box box : world.getBoxes()){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(box.getBoxColor());
            shapeRenderer.rect(box.getX(), box.getY(), box.getLength(), box.getLength());
            shapeRenderer.end();
        }
    }

    public void drawDrops(){

    }



}
