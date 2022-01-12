package feup.ldts.proj.viewer.menu;


import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.Position;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }
    @Override
    protected void drawElements(GUI gui) {
        for (int i = 0; i < getModel().getNumberEntries(); i++)
            gui.drawText(
                    new Position(8, 9 + i),
                    getModel().getOption(i),
                    getModel().isSelected(i) ? "#FFD700" : "#FFFFFF");
    }
}
