package handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.widgets.Shell;

public class MigratedE4Handler {

  // Injected fields will only be once injected (maybe to early?), so inject windows or selections
  // in the class call
	
  @Execute
  public Object execute(@Named(IServiceConstants.ACTIVE_SHELL) Shell s, @Optional MWindow window) {
    InputDialog input =
        new InputDialog(
            s, "Select input", "Enter a text to be displayed as window title", "", null);

    if (input.open() == Dialog.OK) {
    	if (window != null) {
    		window.setLabel(input.getValue());
    	}
    }
    return null;
  }
}
