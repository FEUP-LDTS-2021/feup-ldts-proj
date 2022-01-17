package feup.ldts.proj.menu;

import feup.ldts.proj.Game;
import feup.ldts.proj.model.game.Position;
import feup.ldts.proj.model.game.Weapon;
import feup.ldts.proj.model.game.elements.Player;
import feup.ldts.proj.model.menu.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MenuTest {
    private Player player;
    private Weapon newWeapon;
    private Menu menu;

    @BeforeEach
    public void setUp() {
        this.player = new Player(new Position(1, 1));
        this.newWeapon = new Weapon(4, 3, 5);
    }

    @Test
    public void controlsMenuTest() {
        menu = new ControlsMenu();

        Assertions.assertEquals(0, menu.getCurrentOption());

        for (int tries = 1; tries <= 100; tries++) menu.nextOption();
        Assertions.assertEquals(0, menu.getCurrentOption());

        for (int tries = 1; tries <= 100; tries++) menu.previousOption();
        Assertions.assertEquals(0, menu.getCurrentOption());

        Assertions.assertEquals(menu.getOptionColor(menu.getCurrentOption()), Game.Colors.get("Golden"));
    }

    @Test
    public void mainMenuTest() {
        menu = new MainMenu();

        Assertions.assertEquals(0, menu.getCurrentOption());

        menu.nextOption();
        Assertions.assertEquals(1, menu.getCurrentOption());

        menu.nextOption();
        Assertions.assertEquals(2, menu.getCurrentOption());

        for (int tries = 1; tries <= 100; tries++) menu.nextOption();
        Assertions.assertEquals(2, menu.getCurrentOption());

        menu.previousOption();
        Assertions.assertEquals(1, menu.getCurrentOption());

        menu.previousOption();
        Assertions.assertEquals(0, menu.getCurrentOption());

        for (int tries = 1; tries <= 100; tries++) menu.previousOption();
        Assertions.assertEquals(0, menu.getCurrentOption());

        Assertions.assertEquals(menu.getOptionColor(menu.getCurrentOption()), Game.Colors.get("Golden"));
        Assertions.assertEquals(menu.getOptionColor(1), Game.Colors.get("White"));
        Assertions.assertEquals(menu.getOptionColor(2), Game.Colors.get("White"));
    }

    @Test
    public void victoryMenuTest() {
        menu = new VictoryMenu();

        Assertions.assertEquals(0, menu.getCurrentOption());

        menu.nextOption();
        Assertions.assertEquals(1, menu.getCurrentOption());

        for (int tries = 1; tries <= 100; tries++) menu.nextOption();
        Assertions.assertEquals(1, menu.getCurrentOption());

        menu.previousOption();
        Assertions.assertEquals(0, menu.getCurrentOption());


        for (int tries = 1; tries <= 100; tries++) menu.previousOption();
        Assertions.assertEquals(0, menu.getCurrentOption());

        Assertions.assertEquals(menu.getOptionColor(menu.getCurrentOption()), Game.Colors.get("Golden"));
        Assertions.assertEquals(menu.getOptionColor(1), Game.Colors.get("White"));
    }

    @Test
    public void weaponMenuTest() {
        menu = new WeaponMenu(player, newWeapon);

        Assertions.assertEquals(0, menu.getCurrentOption());
        Assertions.assertEquals(5, menu.getNumberAssets());

        menu.nextOption();
        Assertions.assertEquals(1, menu.getCurrentOption());

        for (int tries = 1; tries <= 100; tries++) menu.nextOption();
        Assertions.assertEquals(1, menu.getCurrentOption());

        menu.previousOption();
        Assertions.assertEquals(0, menu.getCurrentOption());


        for (int tries = 1; tries <= 100; tries++) menu.previousOption();
        Assertions.assertEquals(0, menu.getCurrentOption());

        Assertions.assertEquals(menu.getOptionColor(menu.getCurrentOption()), Game.Colors.get("Golden"));
        Assertions.assertEquals(menu.getOptionColor(1), Game.Colors.get("White"));
    }
}