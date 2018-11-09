package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Computer extends AbstractActor {
    private Animation normalAnimation;

    public Computer (){
       setAnimation( normalAnimation = new Animation("spirites/computer.png",80,48, 0.2f, Animation.PlayMode.LOOP_PINGPONG));
    }

    public float Add(float a, float b) {
        return a + b;
    }

    public int Add(int a, int b) {
        return a + b;
    }

    public float Sub(float a, float b) {
        return a - b;
    }

    public int Sub(int a, int b) {
        return a - b;
    }

}
