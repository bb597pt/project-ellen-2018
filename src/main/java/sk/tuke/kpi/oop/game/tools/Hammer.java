package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Hammer extends BreakableTool  {
    private int remainingUses;

    public Hammer(){
        super(1);
        setAnimation(new Animation("spirites/hammer.png",16,16, 0.1f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public int getRemainingUses() {
        return remainingUses ;
    }

    public void use() {
        if (remainingUses==0) {
            //Scene  scene = getScene() ;
            //scene.removeActor(this) ;
            this.getScene().removeActor(this);
        }
    }

}
