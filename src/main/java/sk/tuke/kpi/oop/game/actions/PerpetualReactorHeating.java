package sk.tuke.kpi.oop.game.actions;

import sk.tuke.kpi.gamelib.framework.actions.AbstractAction;
import sk.tuke.kpi.oop.game.Reactor;

public class PerpetualReactorHeating extends AbstractAction <Reactor> {

    private int temperatureIncrement;

    public PerpetualReactorHeating(int temperatureIncrement){
        this.temperatureIncrement= temperatureIncrement;
    }

    @Override
    public void execute(float deltaTime) {
        Reactor reactor = (Reactor) getActor();
        reactor.increaseTemperature(temperatureIncrement);
    }
}
