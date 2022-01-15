package feup.ldts.proj.model.menu;

import feup.ldts.proj.model.game.Position;

import java.util.Arrays;

public class MainMenu extends Menu {

    public MainMenu() {
        this.options = Arrays.asList("PLAY", "CONTROLS", "EXIT");
        this.assets = Arrays.asList("GAME NAME HERE");
        this.currentOption = 0;
    }

    @Override
    public Position getOptionPosition(int option) {
        switch (option) {
            case 0: return new Position(8, 7);
            case 1: return new Position(6, 9);
            case 2: return new Position(8, 11);
        }
        return null;
    }

    @Override
    public Position getAssetPosition(int asset) {
        return new Position(3, 3);
    }

    @Override
    public void nextOption() {
        currentOption++;
        if (currentOption > this.options.size() - 1) currentOption = 2;
    }

    @Override
    public void previousOption() {
        currentOption--;
        if (currentOption < 0) currentOption = 0;
    }
}
