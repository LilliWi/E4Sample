package handler;

import java.util.List;

import org.eclipse.e4.ui.di.AboutToShow;
import org.eclipse.e4.ui.model.application.ui.menu.MDirectMenuItem;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuElement;
import org.eclipse.e4.ui.model.application.ui.menu.MMenuFactory;

public class TestDynamicClass {
	
  @AboutToShow
  public void aboutToShow(List<MMenuElement> items) {
    MDirectMenuItem dynamicItem = MMenuFactory.INSTANCE.createDirectMenuItem();
    dynamicItem.setLabel("Test Label-");
    dynamicItem.setContributorURI("platform:/plugin/guiComponents");
    dynamicItem.setContributionURI("bundleclass://handler/CreateMenuEntry");
    items.add(dynamicItem);
  }
}
