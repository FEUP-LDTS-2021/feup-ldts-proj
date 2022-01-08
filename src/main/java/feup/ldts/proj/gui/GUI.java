package feup.ldts.proj.gui;

import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.model.Position;

import java.io.IOException;
import java.util.HashMap;

public interface GUI {
    ACTION getAction() throws IOException;
    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, EXIT, SHOOT}

    void drawPlayer(Position position, String color);
    void drawWall(Position position);
    void drawMonster(Position position, String color);
    void drawBullet(Position position, String color);
    void drawPassage(Position position, String color);
    void drawText(Position position, String text, String color);
    void drawCharacter(int x, int y, char c, String color);

    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    Screen getScreen();
}
