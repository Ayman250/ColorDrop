package gameworld;


import java.util.Collection;
import java.util.Random;

import gameobjects.Drop;
import gameworld.GameWorld;

public class Scroller {
    private static float INIT_SPEED = 250;
    private float scroll_speed;
    private GameWorld world;
    private float acceleration;

    public Scroller(GameWorld world){
        this.world = world;
        scroll_speed= INIT_SPEED;
        acceleration = 0f;
    }

    public void update(float delta){
        updateDrops(delta);
    }

    /*
    Scroll drops and shit
    */
    public void updateDrops(float delta){
        scroll_speed *= (1 + delta * acceleration);
        for(Drop drop : world.getDrops()){
            drop.setY(drop.getY()+delta*scroll_speed) ;
        }
        if(world.getDrops().get(1).getY() >= world.getBoxes().get(1).getY()){
            if(correctBoxes()) {
                world.addPoints(1);
                resetDrops();
            }
            else {
                resetDrops();
                world.gameOver();
            }
        }
    }

    private void resetDrops(){
        for(Drop drop : world.getDrops()){
            world.resetDropColors();
            drop.resetY();
        }
        /*
        Reset drops to initial speed.
        */
        scroll_speed = INIT_SPEED;
    }

    private boolean correctBoxes(){
        for(int i = 0; i<world.getBoxes().size(); i++){
            if(!world.getBoxes().get(i).getBoxColor().equals(world.getDrops().get(i).getDropColor())){
                System.out.println(world.getBoxes().get(i).getBoxColor().toString() + " " + world.getDrops().get(i).getDropColor().toString());
                return false;
            }
        }
        return true;
    }

    public void resetScrollSpeed(){
        scroll_speed = 250;
    }
}
