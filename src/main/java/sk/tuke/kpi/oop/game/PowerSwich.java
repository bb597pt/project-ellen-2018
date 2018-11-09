package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class PowerSwich extends AbstractActor  {

    private Animation switchAnimation;
    private Switchable device;

    public PowerSwich(Switchable device){
        switchAnimation = new Animation("sprites/switch.png", 1,16);
        setAnimation(switchAnimation);
        this.device = device;
    }
    public void toggle(){
        if (device.isOn()) {
            device.turnOff();
        }
        else device.turnOn();
    }
}
//ok
