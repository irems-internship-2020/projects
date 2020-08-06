package org.o7planning.tutorial.rcp.cmd;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.handlers.HandlerUtil;
import org.o7planning.tutorial.rcp.dialog.*;

public class AboutCommand extends AbstractHandler {

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException {
      Shell shell = HandlerUtil.getActiveWorkbenchWindow(event).getShell();
      AboutDialog dialog = new AboutDialog(shell, SWT.DIALOG_TRIM
              | SWT.APPLICATION_MODAL);
      dialog.open();
      return null;
  }

}