package feup.ldts.proj.controller.elements.observers;

import feup.ldts.proj.model.elements.Monster;

public interface MonsterObserver {
    void hpChanged(Monster monster);
    void positionChanged(Monster monster);
}
