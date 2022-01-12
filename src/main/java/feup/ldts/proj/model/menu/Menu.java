package feup.ldts.proj.model.menu;

import java.util.List;
import java.util.Arrays;

public class Menu {
    private final List<String> options;
    private int currentEntry = 0;

    public Menu() {
        this.options = Arrays.asList("Play", "Exit");
    }

    public void nextOption() {
        currentEntry++;
        if (currentEntry > this.options.size() - 1) currentEntry = 1;
    }

    public void previousOption() {
        currentEntry--;
        if (currentEntry <= 0) currentEntry = 0;
    }

    public String getOption(int i) {
        return options.get(i);
    }

    public boolean isSelected(int i) {
        return currentEntry == i;
    }

    public boolean isSelectedExit() {
        return isSelected(1);
    }

    public boolean isSelectedStart() {
        return isSelected(0);
    }

    public int getNumberEntries() {
        return this.options.size();
    }
}
