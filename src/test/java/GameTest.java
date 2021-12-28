import feup.ldts.proj.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class GameTest {
    Game game = new Game();
    int roomNum, depth, numRows, numCols;

   @BeforeEach
    public void setUpFilename() {
        roomNum = 1;
        depth = 1;
        numRows = 20;
        numCols = 20;
    }

    @Test
    public void loadRoomTest() {
        game.loadRoom(roomNum, depth);
        Assertions.assertEquals(numRows, game.getRoomLayout().size()); //the roomLayout has numRows rows
        for (int i = 0; i < 20; i++)
            Assertions.assertEquals(numCols, game.getRoomLayout().get(i).size()); //the room has numCols cols at every row;
    }

}
