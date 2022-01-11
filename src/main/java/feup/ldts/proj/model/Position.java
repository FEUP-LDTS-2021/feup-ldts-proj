package feup.ldts.proj.model;

import java.util.Random;

public class Position {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}

    public int getY() {return y;}

    public Position getLeft() {return new Position(x - 1, y);}

    public Position getRight() {return new Position(x + 1, y);}

    public Position getUp() {return new Position(x, y - 1);}

    public Position getDown() {return new Position(x, y+1);}

    public Position getRandomPosition() {
        switch (new Random().nextInt(4)) {
            case 0: return new Position(x - 1, y);
            case 1: return new Position(x + 1, y);
            case 2: return new Position(x, y - 1);
            case 3: return new Position(x, y + 1);
        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public String toString() {
        return "X: " + x + " Y: " + y;
    }
}
