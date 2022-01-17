package feup.ldts.proj.model.menu;

import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.Game;

import java.util.Arrays;

public class ControlsMenu extends Menu {

    public ControlsMenu() {
        this.options = Arrays.asList("-GO BACK");
        this.assets = Arrays.asList("CONTROLS", "ARROW KEYS TO MOVE", "X TO SHOOT", "Q TO RETURN TO THE", "MAIN MENU", "CTRL+D TO QUIT");
        this.currentOption = 0;
    }

    @Override
    public Position getOptionPosition(int option) {
        switch (option) {
            case 0: return new Position(7, 16);
            default: return null;
        }
    }

    @Override
    public Position getAssetPosition(int asset) {
        switch (asset) {
            case 0: return new Position(7, 2);
            case 1: return new Position(1, 5);
            case 2: return new Position(1, 7);
            case 3: return new Position(1, 9);
            case 4: return new Position(1, 11);
            case 5: return new Position(1, 13);
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
        return Game.Colors.get("HealthyGreen");
    }
}
