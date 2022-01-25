package feup.ldts.proj.viewer.game.elements;

import feup.ldts.proj.Game;
import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.game.elements.monsters.Monster;
import feup.ldts.proj.model.game.elements.monsters.Skeleton;
import feup.ldts.proj.model.game.elements.monsters.Zombie;

public class MonsterViewer implements ElementViewer<Monster> {
    @Override
    public String getColor(Monster monster) {
        float hpPercentage = (float) monster.getHP() / monster.getMaxHP();
        if (monster instanceof Zombie) {
            if (hpPercentage <= 0.33) return Game.Colors.get("ZombieLightGreen");
            if (hpPercentage <= 0.66) return Game.Colors.get("ZombieGreen");
            return Game.Colors.get("ZombieDarkGreen");
        }
        else if (monster instanceof Skeleton) {
            if (hpPercentage <= 0.33) return Game.Colors.get("LightGrey"); //light grey
            if (hpPercentage <= 0.66) return Game.Colors.get("Grey"); //grey
            return Game.Colors.get("DarkGrey"); //dark grey
        }
        else {
            if (hpPercentage <= 0.33) return Game.Colors.get("Purple");
            if (hpPercentage <= 0.66) return Game.Colors.get("Pink");
            return Game.Colors.get("Red");
        }
    }

    @Override
    public char getChar(Monster monster) {
        if (monster instanceof Zombie) return '"';
        if (monster instanceof Skeleton)  return '\u00C3';
        return '~';
    }

    @Override
    public void draw(Monster monster, GUI gui) {
        gui.drawCharacter(monster.getPosition().getX(), monster.getPosition().getY(), getChar(monster), getColor(monster));
    }
}
