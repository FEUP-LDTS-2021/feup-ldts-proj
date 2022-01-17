package feup.ldts.proj.model.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;

import java.util.List;
import java.util.Arrays;

public abstract class Menu {
    protected List<String> options;
    protected List<String> assets;
    protected int currentOption = 0;

    public Menu() { this.options = Arrays.asList(); this.assets = Arrays.asList(); }

    public String getOption(int i) { return options.get(i); }

    public String getAsset(int i) { return assets.get(i); }

    public abstract String getAssetColor(int asset);

    public abstract Position getOptionPosition(int option);

    public abstract Position getAssetPosition(int asset);

    public int getNumberOptions() { return this.options.size(); }

    public int getNumberAssets() { return this.assets.size(); }

    public int getCurrentOption() { return currentOption; }

    public String getOptionColor(int option) {
        if (isSelected(option)) return Game.Colors.get("Golden");
        return Game.Colors.get("White");
    }

    public abstract void nextOption();

    public abstract void previousOption();

    public boolean isSelected(int i) { return currentOption == i; }
}
