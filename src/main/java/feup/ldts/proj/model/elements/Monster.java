package feup.ldts.proj.model.elements;

public abstract class Monster extends Element {
    final int baseHP = 5;
    int HP;

    public Monster(int x, int y, int depth) {
        super(x, y);
        HP = baseHP * depth;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
