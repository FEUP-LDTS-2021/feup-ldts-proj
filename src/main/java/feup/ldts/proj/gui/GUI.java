package feup.ldts.proj.gui;

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
    void drawText(Position position, String text, String color);

    void clear();
    void refresh() throws IOException;
    void close() throws IOException;
}