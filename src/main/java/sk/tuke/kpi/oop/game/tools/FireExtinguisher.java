package sk.tuke.kpi.oop.game.tools;

import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.tools.BreakableTool;

public class FireExtinguisher extends BreakableTool {
    private int to_use;
    private Animation  extinguisher;

    public FireExtinguisher(){
        super(1);
        extinguisher = new Animation("sprites/extinguisher.png",16,16);
        setAnimation(extinguisher);
    }

    public int getTo_use() {
        return to_use;
    }

    public void use(){
        if (to_use == 1) {
            to_use = 0;
            getScene().removeActor(this);
        }
        else if (to_use>1) to_use--;
    }
}
