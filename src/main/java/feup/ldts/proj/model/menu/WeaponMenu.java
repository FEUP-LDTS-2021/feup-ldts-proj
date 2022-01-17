package feup.ldts.proj.model.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.Player;

import java.util.Arrays;

public class WeaponMenu extends Menu {
    private Weapon newWeapon;
    private Player player;

    public WeaponMenu(Player player, Weapon newWeapon) {
        this.player = player;
        this.newWeapon = newWeapon;
        this.options = Arrays.asList("YES", "NO");
        this.currentOption = 0;
        setUpAssets(player.getWeapon(), newWeapon);
    }

    public Player getPlayer() {
        return player;
    }

    public Weapon getNewWeapon() {
        return newWeapon;
    }

    @Override
    public Position getOptionPosition(int option) {
        switch (option) {
            case 0: return new Position(5, 15);
            case 1: return new Position(13, 15);
        }
        return null;
    }

    @Override
    public Position getAssetPosition(int asset) {
        switch (asset) {
            case 0: return new Position(4, 3);
            case 1: return new Position(6, 6);
            case 2: return new Position(6, 8);
            case 3: return new Position(6, 10);
            case 4: return new Position(4, 13);
        }
        return null;
    }

    @Override
    public void nextOption() {
        if (currentOption < this.options.size() - 1)
            currentOption++;
    }

    @Override
    public void previousOption() {
        if (currentOption > 0)
            currentOption--;
    }

    private void setUpAssets(Weapon currWeapon, Weapon newWeapon) {
        int DMG_DIFF = newWeapon.getDamage() - currWeapon.getDamage();
        int RANGE_DIFF = newWeapon.getRange() - currWeapon.getRange();
        int CAPACITY_DIFF = newWeapon.getCapacity() - currWeapon.getCapacity();

        String DMG_STR = "D:" + newWeapon.getDamage() + " (" + (DMG_DIFF >= 0 ? "+" + DMG_DIFF : DMG_DIFF) + ")";
        String RANGE_STR = "R:" + newWeapon.getRange() + " (" + (RANGE_DIFF >= 0 ? "+" + RANGE_DIFF : RANGE_DIFF) + ")";
        String CAPACITY_STR = "C:"+ newWeapon.getCapacity() + " (" + (CAPACITY_DIFF >= 0 ? "+" + CAPACITY_DIFF  : CAPACITY_DIFF) + ")";

        this.assets = Arrays.asList("WEAPONS MENU", DMG_STR, RANGE_STR, CAPACITY_STR , "SWAP WEAPON?");
    }

    @Override
    public String getAssetColor(int asset) {
        if (asset == 0 || asset == 4) return Game.Colors.get("HealthyGreen");
        return Game.Colors.get("OceanBlue");
    }
}
