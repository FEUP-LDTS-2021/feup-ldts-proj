package feup.ldts.proj.viewer.menu;

import feup.ldts.proj.gui.GUI;
import feup.ldts.proj.model.menu.Menu;
import feup.ldts.proj.viewer.Viewer;

public class MenuViewer extends Viewer<Menu> {
    public MenuViewer(Menu menu) {
        super(menu);
    }
    @Override
    protected void drawElements(GUI gui) {
        for (int asset = 0; asset < getModel().getNumberAssets(); asset++)
            gui.drawText(
                    getModel().getAssetPosition(asset),
                    getModel().getAsset(asset),
                    getModel().getAssetColor()
            );

        for (int option = 0; option < getModel().getNumberOptions(); option++)
            gui.drawText(
                    getModel().getOptionPosition(option),
                    getModel().getOption(option),
                    getModel().getOptionColor(option)
            );
    }
}
