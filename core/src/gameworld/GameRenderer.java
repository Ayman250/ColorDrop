package gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;


import gameobjects.Box;
import gameobjects.Drop;

public class GameRenderer {
    private int gameWidth, gameHeight;

    private GameWorld world;
    private OrthographicCamera cam;
    private SpriteBatch batch;
    private ShapeRenderer shapeRenderer;
    private Rectangle background;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont font12;

    public GameRenderer(GameWorld world){
        this.world = world;
        this.gameWidth = world.getGameWidth();
        this.gameHeight = world.getGameHeight();

        cam = new OrthographicCamera();
        cam.setToOrtho(true, this.gameWidth, this.gameHeight);

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setProjectionMatrix(cam.combined);

        //Font
        generator = new FreeTypeFontGenerator(Gdx.files.internal("Roboto-Bold.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //Flip
        parameter.flip = true;
        parameter.size = 50;
        font12 = generator.generateFont(parameter); // font size 12 pixels
        font12.setColor(Color.WHITE);

    }

    public void render(float delta, float runTime){
        //Clears buffer for fresh render
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        drawBackground();
        drawScore();
        drawDrops();
        drawBoxes();
    }

    private void drawBackground(){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(0, 0, gameWidth, gameHeight);
        shapeRenderer.end();
    }

    private void drawScore(){
        batch.begin();
        font12.draw(batch, String.valueOf(world.getScore()), 500,100);
        batch.end();
//        generator.dispose(); // don't forget to dispose to avoid memory leaks!
    }

    private void drawBoxes(){
        for(Box box : world.getBoxes()){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.rect(box.getX(), box.getY(), box.getLength(), box.getLength());
            shapeRenderer.setColor(box.getBoxColor());
            shapeRenderer.rect(box.getX()+5, box.getY()+5, box.getLength()-10, box.getLength()-10);
            shapeRenderer.end();
        }
    }

    public void drawDrops(){
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);


        for(Drop drop: world.getDrops()){
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);

            //Draw outside circles to make it smoother
            float a = 1;
            float alphaMultiplier = 0.5f; //you may play with different coefficients
            float radiusStep = drop.getRadius()/200;

            for(int i=0; i<3; i++) {
                a *= alphaMultiplier;
                shapeRenderer.setColor(255, 255, 255, a);
                shapeRenderer.circle(drop.getX(), drop.getY(), drop.getRadius() + 8 + i*radiusStep);

            }

            shapeRenderer.setColor(Color.WHITE);
            shapeRenderer.circle(drop.getX(), drop.getY(), drop.getRadius()+8);
            shapeRenderer.end();
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(drop.getDropColor());
            shapeRenderer.circle(drop.getX(), drop.getY(), drop.getRadius());
            shapeRenderer.end();
        }

    }

    public void dispose(){
        generator.dispose();
        font12.dispose();
    }

}
