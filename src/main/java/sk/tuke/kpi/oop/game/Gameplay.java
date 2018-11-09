package sk.tuke.kpi.oop.game;

import org.jetbrains.annotations.NotNull;
import sk.tuke.kpi.gamelib.Scene;
import sk.tuke.kpi.gamelib.actions.ActionSequence;
import sk.tuke.kpi.gamelib.actions.Invoke;
import sk.tuke.kpi.gamelib.actions.Wait;
import sk.tuke.kpi.gamelib.actions.When;
import sk.tuke.kpi.gamelib.framework.Scenario;
import sk.tuke.kpi.gamelib.map.MapMarker;
import sk.tuke.kpi.oop.game.tools.Hammer;

import java.util.Map;

public class Gameplay extends Scenario {
    @Override
    public void setupPlay(@NotNull Scene scene) {
        Map<String, MapMarker> markers = scene.getMap().getMarkers();

        Reactor reactor = new Reactor();
        MapMarker reactorArea = markers.get("reactor-area-1");
        scene.addActor(reactor, 64, 64);
        reactor.turnOn();

        Cooler cooler = new Cooler(reactor);
        MapMarker coolerArea = markers.get("cooler-area-1");
        scene.addActor(cooler, coolerArea.getPosX(), coolerArea.getPosY());

        new ActionSequence<>(
            new Wait(10),
            new Invoke(cooler::turnOn)
        ).scheduleOn(cooler);

        Hammer hammer = new Hammer();
        scene.addActor(hammer, 64,64);

       /*
       new When<>(
            (action) -> return reactor.getTemperature() == 3000,
        )
        --je tam chladič, takže to nemá efekt
        */

        new Invoke(() -> { reactor.repairWith(hammer); }).scheduleOn(reactor);
    }

}
// ok
