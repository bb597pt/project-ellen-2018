package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.framework.actions.Loop;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Cooler extends AbstractActor implements Switchable {

    private Reactor reactor;
    private boolean isOn;
    private Light light;


    public Cooler(Reactor reactor) {
        this.reactor = reactor;
        setAnimation(new Animation("spirites/fan.png",32,32, 0.2f));
    }

    @Override
    public void turnOn(){
        isOn = true;
        if(light!=null){
            light.setElectricityFlow(true);
        }
        setAnimation(new Animation("spirites/fan.png",32,32,0.2f));
    }

    @Override
    public void turnOff() {
        isOn = false;
        if (light != null) {
            light.setElectricityFlow(false);
        }
        setAnimation(new Animation("spirites/fan.png",32,32,0.2f));
    }

    @Override
    public boolean isOn() {
        if (isOn) return true;
        else return false;
    }

    private void coolReactor() {
        reactor.decreaseTemperature(1);
    }

    @Override
    public void addedToScene(@NotNull Scene scene) {
        super.addedToScene(scene);
        new Loop<>(new Invoke(this::coolReactor)).scheduleOn(this);
    }
}
//+ 3 z reactora
