package feup.ldts.proj.model.game;

public class Weapon {
    private int damage, range, capacity;

    public Weapon(int damage, int range, int capacity) {
        this.damage = damage;
        this.range = range;
        this.capacity = capacity;
    }

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getCapacity() { return capacity; }
}
