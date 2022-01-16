package feup.ldts.proj.model.game.elements.items;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.menu.WeaponMenu;
import feup.ldts.proj.states.WeaponMenuState;

import java.util.Random;

public class WeaponItem extends Item {

    Weapon weapon;

    public WeaponItem(Position position, int depth) {
        super(position, depth);
        this.weapon = createRandomWeapon(depth);
    }

    @Override
    public void onPickup(Player player) {
        System.out.println("Got into the onPickup of weaponItem. the player here has " + player.getMaxHP() + " maxHP.");

        Game.stateStack.push(new WeaponMenuState(new WeaponMenu(player, weapon), player, weapon));
    }

    private Weapon createRandomWeapon(int depth) {
        int dmg = (new Random().nextInt(2 + depth) + 1) * depth;
        int range = (new Random().nextInt(1 + depth) + 2) + depth;
        int capacity = (new Random().nextInt(2 + depth) + 2) * depth;

        return new Weapon(dmg, range, capacity);
    }
}
