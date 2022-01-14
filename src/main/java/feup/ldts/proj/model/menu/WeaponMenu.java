package feup.ldts.proj.model.menu;

import feup.ldts.proj.model.Position;
import feup.ldts.proj.states.GameState;

import java.util.Arrays;

public class WeaponMenu extends Menu {

    public WeaponMenu() {
        this.options = Arrays.asList("YES", "NO");
        this.assets = Arrays.asList("WEAPON MENU", "SWAP WEAPON?");
        this.currentOption = 0;
    }

    @Override
    public Position getOptionPosition(int option) {
        switch (option) {
            case 0: return new Position(6, 15); //temporary Positions to see if it looks good
            case 1: return new Position(14, 15);
        }
        return null;
    }

    @Override
    public Position getAssetPosition(int asset) {
        switch (asset) {
            case 0: return new Position(3, 3);
            case 1: return new Position(4, 7);
        }
        return null;
    }

    @Override
    public void nextOption() {
        if (currentOption < this.options.size())
            currentOption++;
    }

    @Override
    public void previousOption() {
        if (currentOption > 0)
            currentOption--;
    }
}
