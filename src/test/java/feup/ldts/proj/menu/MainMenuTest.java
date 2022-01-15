package feup.ldts.proj.menu;

import feup.ldts.proj.model.menu.MainMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainMenuTest {
    private MainMenu menu;

    @BeforeEach
    public void setUp() {
        this.menu = new MainMenu();
    }

    @Test
    public void previousOptionTest() {
        Assertions.assertEquals(0, menu.getCurrentOption());
        menu.previousOption();
        Assertions.assertEquals(0, menu.getCurrentOption());
    }

    @Test
    public void nextOptionTest() {
        Assertions.assertEquals(0, menu.getCurrentOption());
        menu.nextOption();
        Assertions.assertEquals(1, menu.getCurrentOption());
        menu.nextOption();
        Assertions.assertEquals(2, menu.getCurrentOption());

        menu.nextOption();
        Assertions.assertEquals(2, menu.getCurrentOption());
    }
}
