package feup.ldts.proj.gui;

import com.googlecode.lanterna.screen.Screen;
import feup.ldts.proj.model.game.Position;

import java.io.IOException;

public interface GUI {
    ACTION getAction() throws IOException;
    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, EXIT, SHOOT, SELECT, RETURN}

    void drawText(Position position, String text, String color);
    void drawCharacter(int x, int y, char c, String color);

    void clear();
    void refresh() throws IOException;
    void close() throws IOException;

    Screen getScreen();
}
