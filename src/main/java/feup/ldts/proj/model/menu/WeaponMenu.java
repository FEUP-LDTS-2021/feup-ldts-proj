package feup.ldts.proj.model.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.Player;

import java.util.Arrays;

public class WeaponMenu extends Menu {

    private Weapon newWeapon;
    private Player player;

    public WeaponMenu() {
        this.options = Arrays.asList("YES", "NO");
        this.assets = Arrays.asList("WEAPONS MENU", "SWAP WEAPON?");
        this.currentOption = 0;
    }

    public WeaponMenu(Player player, Weapon newWeapon) {
        this.player = player;
        this.newWeapon = newWeapon;

        this.options = Arrays.asList("YES", "NO");
        setUpAssets(player.getWeapon(), newWeapon);
        this.currentOption = 0;
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
        int dmgDiff = newWeapon.getDamage() - currWeapon.getDamage();
        int rangeDiff = newWeapon.getRange() - currWeapon.getRange();
        int capacityDiff = newWeapon.getCapacity() - currWeapon.getCapacity();

        String dmgString = "D:" + newWeapon.getDamage() + " (" + (dmgDiff >= 0 ? "+" + dmgDiff : dmgDiff) + ")";
        String rangeString = "R:" + newWeapon.getRange() + " (" + ( rangeDiff >= 0 ? "+" + rangeDiff  : rangeDiff ) + ")";
        String capacityString = "C:"+ newWeapon.getCapacity() + " (" + ( capacityDiff >= 0 ? "+" + capacityDiff  : capacityDiff ) + ")";

        this.assets = Arrays.asList("WEAPONS MENU", dmgString, rangeString, capacityString , "SWAP WEAPON?");
    }

    @Override
    public String getAssetColor(int asset) {
        if (asset == 0 || asset == 4) return Game.Colors.get("HealthyGreen");
        return Game.Colors.get("Blurple");
    }
}
