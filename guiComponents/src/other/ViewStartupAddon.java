package other;

import java.util.List;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;

public class ViewStartupAddon {

  private final String PART_ID = "guicomponents.partdescriptor.part";

  @PostConstruct
  private void applicationStarted(IEventBroker eb, EPartService partService, EModelService service) {

    eb.subscribe(
        UIEvents.UILifeCycle.APP_STARTUP_COMPLETE,
        new EventHandler() {

          @Override
          public void handleEvent(Event event) {
            Object data = event.getProperty("org.eclipse.e4.data");

            // Get application object
            if (data instanceof MApplication) {
              List<MWindow> childs = ((MApplication) data).getChildren();

              if (childs.size() >= 1) {
                // Create view
                MPart myView = partService.createPart(PART_ID);
                myView.setLabel("My View");
                myView.setCloseable(true);

                // Get main window and find its part stack children
                MWindow mainWindow = childs.get(0);
                List<MPartStack> stacks =
                    service.findElements(mainWindow, null, MPartStack.class, null);

                // Add view to the last part stack
                int last = stacks.size() - 2;
                MPartStack mPartStack = stacks.get(last);
                mPartStack.getChildren().add(myView);
                mPartStack.setSelectedElement(myView);
              }
            }
            eb.unsubscribe(this);
          }
        });
  }
}
