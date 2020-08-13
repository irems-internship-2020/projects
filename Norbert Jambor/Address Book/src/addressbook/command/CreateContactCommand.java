package addressbook.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import addressbook.editor.CreateContactInput;
import addressbook.editor.CreateContact;

public class CreateContactCommand extends AbstractHandler {

	public static final String ID = "addressbook.command.create";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();

		CreateContactInput input = new CreateContactInput();
		try {
			page.openEditor(input, CreateContact.ID);
		} catch (PartInitException e) {
			System.out.println("Error:" + this.getClass().getName() + ":" + e);
			e.printStackTrace();
			throw new ExecutionException("Error open UserEditor");
		}
		return null;
	}
}