package feup.ldts.proj.controller.game.elements.observers;

import feup.ldts.proj.model.game.elements.bullets.Bullet;

public interface BulletObserver {
    void decayed(Bullet bullet);
}
