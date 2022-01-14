package feup.ldts.proj.controller.elements.observers;

import feup.ldts.proj.model.elements.monsters.Monster;

public interface MonsterObserver {
    void hpChanged(Monster monster);
    void positionChanged(Monster monster);
}
