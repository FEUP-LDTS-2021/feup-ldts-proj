import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.elements.Monster;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MonsterTest {
    private Position position;
    private int depth;
    Monster monster;

    @BeforeEach
    public void setUpMonster() {
        position = new Position(5, 5);
        depth = 1;
        monster = new Monster(position.getX(), position.getY(), depth);
    }

    @Test
    public void moveMonsterTest() {
        monster.setPosition(monster.moveMonster());
        Assertions.assertNotEquals(new Position(5, 5), monster.getPosition());
    }
}
