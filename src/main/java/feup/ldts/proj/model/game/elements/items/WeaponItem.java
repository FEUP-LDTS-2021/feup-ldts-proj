package feup.ldts.proj.model.game.elements.items;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.menu.WeaponMenu;
import feup.ldts.proj.states.menu.WeaponMenuState;

import java.util.Random;

public class WeaponItem extends Item {
    Weapon weapon;

    public WeaponItem(Position position, int depth) {
        super(position, depth);
        this.weapon = createRandomWeapon(depth);
    }

    @Override
    public void onPickup(Player player) {
        Game.stateStack.push(new WeaponMenuState(new WeaponMenu(player, weapon), player, weapon));
    }

    private Weapon createRandomWeapon(int depth) {
        int DMG = (new Random().nextInt(2 + depth) + depth) * (1+depth);
        int RANGE = new Random().nextInt(1 + depth) + 2 * depth;
        int CAPACITY = new Random().nextInt(2 + depth) + 2 * depth;

        return new Weapon(DMG, RANGE, CAPACITY);
    }
}
