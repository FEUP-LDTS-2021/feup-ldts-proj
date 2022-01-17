package feup.ldts.proj.controller.game.elements.strategies;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.game.elements.bullets.MonsterBullet;
import feup.ldts.proj.model.game.elements.monsters.Boss;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.room.Room;

import java.util.Random;

public class BossAttackStrategy implements AttackStrategy {

    @Override
    public void attack(Monster monster, Player player, Room room) {
        if (new Random().nextInt(6) == 1)
        bossAttack(room.getBoss(), room);
    }

    public void bossAttack(Boss boss, Room room) {
        for (int i = -2; i < 3; i++)
            for (int j = -2; j < 3; j++) {
                int dmg = boss.getDamage();
                int range = (i == 0 || j == 0) ? 8 : 1;
                Position bulletPosition = new Position(boss.getPosition().getX() + j, boss.getPosition().getY() + i);
                Element.Direction bulletDirection = getBossBulletDirection(i, j);
                room.addMonsterBullet(new MonsterBullet(bulletPosition, range, dmg, bulletDirection));
            }
    }

    private Element.Direction getBossBulletDirection(int i, int j) {
        if (Math.abs(j) == 2)
            return j < 0 ? Element.Direction.LEFT : Element.Direction.RIGHT;
        if (i < 0)
            return Element.Direction.UP;
        else if (i > 0)
            return Element.Direction.DOWN;
        else {
            if (j < 0)
                return Element.Direction.LEFT;
            else if (j > 0)
                return Element.Direction.RIGHT;
            else
                return Element.Direction.DOWN; // in the boss' exact position
        }

    }
}
