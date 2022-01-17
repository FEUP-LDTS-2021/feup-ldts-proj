package feup.ldts.proj.model.menu;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.Game;

import java.util.Arrays;

public class ControlsMenu extends Menu {

    public ControlsMenu() {
        this.options = Arrays.asList("GO BACK");
        this.assets = Arrays.asList("CONTROLS", "ARROW KEYS to move", "X to shoot", "Q to go back", "CTRL+D to quit");
        this.currentOption = 0;
    }

    @Override
    public Position getOptionPosition(int option) {
        switch (option) {
            case 0: return new Position(1, 16);
            default: return null;
        }
    }

    @Override
    public Position getAssetPosition(int asset) {
        switch (asset) {
            case 0: return new Position(6, 2);
            case 1: return new Position(1, 6);
            case 2: return new Position(1, 8);
            case 3: return new Position(1, 10);
            case 4: return new Position(1, 12);
            default: return null;
        }
    }

    @Override
    public void nextOption() {
        currentOption++;
        if (currentOption > 0) currentOption = 0;
    }

    @Override
    public void previousOption() {
        currentOption--;
        if (currentOption < 0) currentOption = 0;
    }

    @Override
    public String getAssetColor(int asset) {
        if (asset == 0) return Game.Colors.get("Orange");
        return Game.Colors.get("White");
    }
}
