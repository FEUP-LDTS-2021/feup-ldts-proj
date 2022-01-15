package feup.ldts.proj.controller.game.elements.observers;

import feup.ldts.proj.model.game.elements.monsters.Monster;

public interface MonsterObserver {
    void hpChanged(Monster monster);
    void positionChanged(Monster monster);
}
