package sk.tuke.kpi.oop.game;


import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;
import sk.tuke.kpi.oop.game.actions.PerpetualReactorHeating;
import sk.tuke.kpi.oop.game.tools.FireExtinguisher;
import sk.tuke.kpi.oop.game.tools.Hammer;

public class Reactor extends AbstractActor implements Switchable {

    private int temperature;
    private int damage;
    private Animation offAnimation;
    private Animation normalAnimation;
    private Animation brokenAnimation;
    private Animation hotAnimation;
    private Animation extinguished;
    private float pulsing;
    private boolean isOn;
    private Light light;

    public Reactor() {
        temperature = 0;
        damage = 0;
        normalAnimation = new Animation("spirites/reactor_on.png",80,80, 0.2f, Animation.PlayMode.LOOP_PINGPONG);
        setAnimation(normalAnimation);
        pulsing = 0.1f;
        isOn = false;

    // create animation object
    offAnimation = new Animation("sprites/reactor.png",80,80);
    normalAnimation = new Animation("sprites/reactor_on.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    hotAnimation = new Animation("sprites/reactor_hot.png", 80, 80, 0.05f, Animation.PlayMode.LOOP_PINGPONG);
    brokenAnimation = new Animation("sprites/reactor_broken.png", 80, 80, 0.1f, Animation.PlayMode.LOOP_PINGPONG);
    extinguished = new Animation("sprites/reactor_extinguished.png",80,80);
    // set actor's animation to just created Animation object
    setAnimation(offAnimation);
}
@Override
    public void turnOn(){
        isOn = true;
        if(light!=null){
        light.setElectricityFlow(true);
        }
        updateAnimation();
    }
@Override
    public void turnOff(){
        isOn = false;
        if(light!=null) {
         light.setElectricityFlow(false);
        }
        updateAnimation();
    }
@Override
    public boolean isOn(){
        if(isOn) return true;
        else return false;
    }

    /////pridat tieto 3 hore aj do cooler a light

    public int getTemperature() {
        return temperature;
    }

    public int getDamage() {
        return damage;
    }

    private void updateAnimation() {

        if (!isOn && damage!=100) setAnimation(offAnimation);

        else if (this.temperature<4000) {
            this.pulsing = (float) (0.1 - (this.damage*0.001));
            normalAnimation.setFrameDuration(pulsing);
            setAnimation(normalAnimation);
        }
        else if (this.temperature>6000){
            setAnimation(brokenAnimation);
        }
        else {
            this.pulsing = (float) (0.5 - (this.damage*0.001));
            normalAnimation.setFrameDuration(pulsing);
            setAnimation(hotAnimation);
        }

    }

    public void increaseTemperature(int increment) {
        if (increment > 0 && isOn) {
            if (damage<33) {
                temperature = (int) Math.ceil(temperature + increment);
            }
            else if (damage <= 66){
                temperature = (int) Math.ceil(temperature + 1.5*increment);
            }
            else {
                temperature = (int) Math.ceil(temperature + 2*increment);
            }
            if ((temperature > 2000) && (temperature<6000)) {
                damage = (int) Math.floor((temperature-2000)/40);
                light.setElectricityFlow(false);
            }
            else if ((temperature>=6000) && (damage!=100)) {
                damage = 100;
                turnOff();
            }
            updateAnimation();
        }
    }

    public void decreaseTemperature(int decrement){
        if (decrement>0 && isOn){
            if (damage<100){
                if (damage>50){
                    temperature = temperature - Math.round (decrement/2);
                }
                else temperature = temperature - decrement;
            }
            if (temperature < 4000){
                updateAnimation();
            }
        }
    }

    public void repairWith( Hammer hammer){

        if (hammer == null || damage == 0 || hammer.getRemainingUses() == 0 || !isOn) return;
        hammer.use();
        temperature = ((damage-50)*40)+2000;
        damage = (damage-50) > 0 ? damage-50 : 0;
        updateAnimation();
        if (damage == 0) light.setElectricityFlow(true);
    }

    public void addLight (Light light){

        this.light = new Light();
        if (isOn && (damage==0)){
            light.setElectricityFlow(true);
        }
    }

    public void removeLight (Light light){
        getScene().removeActor(light);
    }

    public void extinguishWith(FireExtinguisher extinguisher){

        if (extinguisher == null || damage == 0 || extinguisher.getTo_use() == 0 || !isOn) return;
        extinguisher.use();
        temperature =4000;
        setAnimation(extinguished);
    }

    @Override
    public void addedToScene(@NotNull Scene scene){
    super.addedToScene(scene);
        new PerpetualReactorHeating(1).scheduleOn(this);
        //PerpetualReactorHeating reactorHeating = new PerpetualReactorHeating(1)
        //scene.scheduleAction(new PerpetualReactorHeating(1), this);
    }

}

