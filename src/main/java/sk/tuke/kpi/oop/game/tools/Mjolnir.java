package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.Hammer;

public class Mjolnir extends Hammer {

    private int remainingUses;

    public Mjolnir(){
        remainingUses = 4;
        setAnimation(new Animation("sprites/hammer.png",16,16));
    }

    public void use(){
       if (remainingUses==0){
           getScene().removeActor(this);
       }
        else if (remainingUses>1) remainingUses--;
    }
}
