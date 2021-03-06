package adressBook.cmd;


import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import addressBook.editor.UserCreate;
import addressBook.editor.Editor;
public class UserCommandCreate extends AbstractHandler {

	   public static final String ID = "RCPAddressBookApp.cmd.create";

	   @Override
	   public Object execute(ExecutionEvent event) throws ExecutionException {
	       IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
	       IWorkbenchPage page = window.getActivePage();

	       UserCreate input = new UserCreate();
	       try {
	           page.openEditor(input, Editor.ID);
	       } catch (PartInitException e) {
	           System.out.println("Error:" + this.getClass().getName() + ":" + e);
	           e.printStackTrace();
	           throw new ExecutionException("Error open UserEditor");
	       }
	       return null;
	   }

	}
