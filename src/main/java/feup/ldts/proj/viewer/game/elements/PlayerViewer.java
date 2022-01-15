package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.Player;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public String getColor(Player player) {
        int HP = player.getHP();
        int maxHP = player.getMaxHP();
        if ((float) HP/maxHP <= 0.20) Game.Colors.get("DyingGreen");
        if ((float) HP/maxHP <= 0.40) Game.Colors.get("WoundedGreen");
        if ((float) HP/maxHP <= 0.60) Game.Colors.get("DarkGreen");
        if ((float) HP/maxHP <= 0.80) Game.Colors.get("Green");
        return Game.Colors.get("HealthyGreen");
    }

    @Override
    public char getChar(Player player) {
        return 'X';
    }

    @Override
    public void draw(Player player, GUI gui) {
        gui.drawCharacter(player.getPosition().getX(), player.getPosition().getY(), getChar(player), getColor(player));
    }
}