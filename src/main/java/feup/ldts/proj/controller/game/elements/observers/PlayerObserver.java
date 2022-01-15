package feup.ldts.proj.controller.game.elements.observers;

import feup.ldts.proj.model.game.elements.Player;

public interface PlayerObserver {
    void positionChanged(Player player);
}
