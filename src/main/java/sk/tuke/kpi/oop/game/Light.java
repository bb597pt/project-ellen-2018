package sk.tuke.kpi.oop.game;

import sk.tuke.kpi.gamelib.framework.AbstractActor;
import sk.tuke.kpi.gamelib.graphics.Animation;

public class Light extends AbstractActor implements Switchable{
    private Animation onAnimation;
    private Animation offAnimation;
    private boolean isOn;
    private boolean isPowered;
    private Light light;


    public Light(){
        offAnimation = new Animation("sprites/light_off.png", 16,16);
        onAnimation = new Animation("spiries/light_on.png", 16,16);
        isOn = false;
        isPowered = false;

    }

    @Override
    public void turnOn(){
        isOn = true;
        if(light!=null){
            light.setElectricityFlow(true);
        }
        setAnimation(onAnimation);
    }
    @Override
    public void turnOff(){
        isOn = false;
        if(light!=null) {
            light.setElectricityFlow(false);
        }
        setAnimation(offAnimation);
    }
    @Override
    public boolean isOn(){
        if(isOn) return true;
        else return false;
    }
    public void toggle(){

       if (isOn) { isOn = false;
        }
        else isOn = true;
        if (isOn && isPowered){
            setAnimation(onAnimation);
        }
        else setAnimation(offAnimation);
    }

    public void setElectricityFlow(boolean isElectricity){

        isPowered = isElectricity;
        if (isPowered && isOn) {
            setAnimation(onAnimation);
        }
        else {
            setAnimation(offAnimation);
        }
    }
}
