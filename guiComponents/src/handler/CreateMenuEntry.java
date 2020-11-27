package handler;

import java.util.Date;
import java.util.List;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenu;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;
import org.eclipse.e4.ui.workbench.modeling.EModelService;

public class CreateMenuEntry {

  private final String MENU_NAME = "guicomponents.menu.dynamicmenu";
  private int number = 0;

  @Execute
  public void execute(@Optional EModelService service, @Optional MWindow window) {
    if (service != null && window != null) {
      MUIElement menu = service.find(MENU_NAME, window.getMainMenu());

      if (menu instanceof MMenu) {
        MDirectMenuItem dynamicItem = MMenuFactory.INSTANCE.createDirectMenuItem();
        dynamicItem.setLabel("Dynamic Menu Item: " + number);
        dynamicItem.setContributorURI("platform:/plugin/guiComponents");
        
        // Sets the handler class
        // dynamicItem.setContributionURI("bundleclass://guiComponents/handler.CreateMenuEntry");

        ((MMenu) menu).getChildren().add(dynamicItem);
        number++;
      }
    }
  }

  @AboutToShow
  public void aboutToShow(List<MMenuElement> items) {
    MDirectMenuItem dynamicItem = MMenuFactory.INSTANCE.createDirectMenuItem();
    dynamicItem.setLabel("Dynamic Menu Item (" + new Date() + ")");
    dynamicItem.setContributorURI("platform:/plugin/guiComponents");
    dynamicItem.setContributionURI("bundleclass://guiComponents/handler.CreateMenuEntry");
    items.add(dynamicItem);
  }
}
