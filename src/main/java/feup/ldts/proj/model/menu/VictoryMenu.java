package feup.ldts.proj.model.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;

import java.util.Arrays;

public class VictoryMenu extends Menu {

    public VictoryMenu() {
        this.options = Arrays.asList("MAIN MENU", "EXIT"); //side-by-side
        this.assets = Arrays.asList("CONGRATULATIONS!", "YOU WON!");
        this.currentOption = 0;
    }

    @Override
    public Position getOptionPosition(int option) {
        switch (option) {
            case 0: return new Position(4, 10);
            case 1: return new Position(14, 10);
            default: return null;
        }
    }

    @Override
    public Position getAssetPosition(int asset) {
        switch (asset) {
            case 0: return new Position(2, 3);
            case 1: return new Position(6, 5);
            default: return null;
        }
    }

    @Override
    public void nextOption() {
        currentOption++;
        if (currentOption > options.size() - 1) currentOption = 0;
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
