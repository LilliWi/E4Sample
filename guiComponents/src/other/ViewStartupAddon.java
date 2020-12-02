package other;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.descriptor.basic.MPartDescriptor;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.UIEvents;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.osgi.service.event.Event;

public class ViewStartupAddon {

  private final String PART_ID = "guicomponents.partdescriptor.part";

  @PostConstruct
  void applicationStarted(
      @Optional @UIEventTopic(UIEvents.UILifeCycle.APP_STARTUP_COMPLETE) Event event,
      EModelService service,
      MApplication application) {

	  MWindow mainWindow = application.getChildren().get(0);
	  
    MPartDescriptor viewDescriptor = service.getPartDescriptor(PART_ID);
    MPart myView = service.createPart(viewDescriptor);
    myView.setCloseable(true);

    MPartStack partStack = MBasicFactory.INSTANCE.createPartStack();
    partStack.getChildren().add(myView);
    
    mainWindow.getChildren().add(partStack);
  }
  
//  @PostConstruct
//  void applicationStarted2(
//		  @Optional @UIEventTopic(UIEvents.EventTags.ELEMENT) Event event,
//		  EModelService service,
//		  MApplication application) {
//	  
//	  MWindow mainWindow = application.getChildren().get(0);
//	  
//	  MPartDescriptor viewDescriptor = service.getPartDescriptor(PART_ID);
//	  MPart myView = service.createPart(viewDescriptor);
//	  myView.setCloseable(true);
//	  
//	  MPartStack partStack = MBasicFactory.INSTANCE.createPartStack();
//	  partStack.getChildren().add(myView);
//	  
//	  mainWindow.getChildren().add(partStack);
//  }
}
