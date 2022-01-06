package feup.ldts.proj.model;


public class Weapon {
    private int damage, range, capacity;

    public Weapon(int damage, int range, int capacity) {
        this.damage = damage;
        this.range = range;
        this.capacity = capacity;
    }

    //getters

    public int getDamage() {
        return damage;
    }

    public int getRange() {
        return range;
    }

    public int getCapacity() { return capacity; }

    //setters

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void setCapacity(int capacity) { this.capacity = capacity; }
}
