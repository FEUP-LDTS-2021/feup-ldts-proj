package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.Element;
import feup.ldts.proj.model.game.elements.Player;

public class PlayerViewer implements ElementViewer<Player> {
    @Override
    public String getColor(Player player) {
        float hpPercentage = (float) player.getHP() / player.getMaxHP();

        if (hpPercentage <= 0.20) return Game.Colors.get("DyingGreen");
        if (hpPercentage <= 0.40) return Game.Colors.get("WoundedGreen");
        if (hpPercentage <= 0.60) return Game.Colors.get("DarkGreen");
        if (hpPercentage <= 0.80) return Game.Colors.get("Green");
        return Game.Colors.get("HealthyGreen");
    }

    @Override
    public char getChar(Player player) {
        if (player.getFacingDirection() == Element.Direction.UP) return '\u00D5';
        else if (player.getFacingDirection() == Element.Direction.DOWN) return '\u00D4';
        else if (player.getFacingDirection() == Element.Direction.LEFT) return '\u00D3';
        else return '\u00D2';
    }

    @Override
    public void draw(Player player, GUI gui) {
        gui.drawCharacter(player.getPosition().getX(), player.getPosition().getY(), getChar(player), getColor(player));
    }
}