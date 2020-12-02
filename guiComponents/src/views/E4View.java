package views;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

public class E4View {
	
  private TableViewer viewer;

  public static final String ID = "e3.views.E4View";

  @PostConstruct
  public void createPartControl(Composite parent, @Optional EPartService service, MWindow window) {
    System.out.println("Enter in SampleE4View postConstruct");

    viewer = new TableViewer(parent, SWT.BORDER);
    viewer.setContentProvider(ArrayContentProvider.getInstance());
    
    Table table = viewer.getTable();
    table.setHeaderVisible(true);
    table.setLinesVisible(true);
    
    String[] inputList = {"1", "2", "3", "4"};
    viewer.setInput(inputList);
  }
  
}
