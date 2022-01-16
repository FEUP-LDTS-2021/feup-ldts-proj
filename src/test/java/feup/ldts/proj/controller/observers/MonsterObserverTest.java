package feup.ldts.proj.controller.observers;

import feup.ldts.proj.controller.game.elements.observers.MonsterObserver;
import feup.ldts.proj.controller.game.elements.strategies.BiteStrategy;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.monsters.Zombie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MonsterObserverTest {
    Zombie zombie;

    @BeforeEach
    public void setUp() {
        this.zombie = new Zombie(new Position(1 , 1),1, new BiteStrategy());
    }

    /*@Test
    public void alertPositionChanged() {
        MonsterObserver observer = Mockito.mock(MonsterObserver.class);
        zombie.addMonsterObserver(observer);

        zombie.setPosition(new Position(2, 2));
        Mockito.verify(observer, Mockito.times(1)).positionChanged(zombie);

        zombie.setPosition(new Position(3, 3));
        zombie.setPosition(new Position(2, 4));
        Mockito.verify(observer, Mockito.times(3)).positionChanged(zombie);
    }

    @Test
    public void alertHPChanged() {
        MonsterObserver observer = Mockito.mock(MonsterObserver.class);
        zombie.addMonsterObserver(observer);

        zombie.decreaseHP(1);
        Mockito.verify(observer, Mockito.times(1)).hpChanged(zombie);

        zombie.decreaseHP(1);
        zombie.decreaseHP(1);
        Mockito.verify(observer, Mockito.times(3)).hpChanged(zombie);
    }*/
}
