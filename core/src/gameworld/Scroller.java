package gameworld;


import java.util.Collection;
import java.util.Random;

import gameobjects.Drop;
import gameworld.GameWorld;

public class Scroller {
    private float scroll_speed;
    private GameWorld world;
    private float speedMult;

    public Scroller(GameWorld world){
        this.world = world;
        scroll_speed=250;
        speedMult = 1;
    }

    public void update(float delta){
        updateDrops(delta);
    }

    //Scroll Drops & Shit
    public void updateDrops(float delta){
        for(Drop drop : world.getDrops()){
            drop.setY(drop.getY()+delta*scroll_speed);
        }
        if(world.getDrops().get(1).getY() >= world.getBoxes().get(1).getY()){
            if(correctBoxes()) {
                world.addPoints(1);
            }
            resetDrops();
        }
    }

    private void resetDrops(){
        for(Drop drop : world.getDrops()){
            world.resetDropColors();
            drop.resetY();
        }
        //After every point scored increase dropspeed
        scroll_speed*=speedMult;
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
