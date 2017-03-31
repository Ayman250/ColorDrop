package gameworld;


import gameobjects.Drop;
import gameworld.GameWorld;

public class Scroller {
    private static int SCROLL_SPEED = 1000;
    private GameWorld world;

    public Scroller(GameWorld world){
        this.world = world;
    }

    public void update(float delta){
        updateDrops(delta);
    }

    //Scroll Drops & Shit
    public void updateDrops(float delta){
        for(Drop drop : world.getDrops()){
            drop.setY(drop.getY()+delta*SCROLL_SPEED);
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
            drop.resetY();
        }
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
}
