package handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class ShowViewInNewPartStack {
	
	private final String PART_ID = "guicomponents.partdescriptor.part";

	  @Execute
	  public void execute(@Optional EPartService service, @Optional MWindow window) {
	    if (service != null) {
	    	
	      // Create part
	      MPart myView = service.createPart(PART_ID);
	      myView.setLabel("My View");
	      myView.setCloseable(true);
	      
	      MPartStack partStack = MBasicFactory.INSTANCE.createPartStack();
	      System.out.println( partStack.getElementId() );
	      partStack.getChildren().add(myView);
	      
	      window.getChildren().add(partStack);
	      
//	      MTrimBar trimBar = MBasicFactory.INSTANCE.createTrimBar();
//	      trimBar.getChildren().add(e)
//	      myView.getTrimBars().add(trimBar);
	      
//	      MToolBar toolBar = myView.getToolbar();
//	      toolBar.getChildren().add();
	    }
	  }
}
