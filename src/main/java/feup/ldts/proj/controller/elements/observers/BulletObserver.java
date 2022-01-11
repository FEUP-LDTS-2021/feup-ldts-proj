package feup.ldts.proj.controller.elements.observers;

import feup.ldts.proj.model.elements.Bullet;

public interface BulletObserver {
    void decayed(Bullet bullet);
}
