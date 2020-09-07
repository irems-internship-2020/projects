package addressbook.action;

import java.util.Iterator;

import javax.naming.NamingException;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import addressbook.database.DatabaseOperations;
import addressbook.model.Contact;
import addressbook.view.ContactsView;

public class DeleteContactAction implements IViewActionDelegate {
		
	private DatabaseOperations databaseOperations = new DatabaseOperations();

	@Override
	public void run(IAction action) {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ContactsView view = (ContactsView) activePage.findView(ContactsView.ID);
		ISelection selection = view.getSite().getSelectionProvider().getSelection();
		
		if (selection != null && selection instanceof IStructuredSelection) {
			IStructuredSelection sel = (IStructuredSelection) selection;

			for (@SuppressWarnings("unchecked")
			Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
				Contact contact = iterator.next();
				try {
					databaseOperations.getDatabase().deleteJpa(contact);
					databaseOperations.getDatabase().closeTransaction();
					databaseOperations.getDatabase().closeTransaction();
				} catch (NamingException e1) {
					e1.printStackTrace();
				}
				try {
					view.viewer.setInput(databaseOperations.getDatabase().loadJpa());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			view.getViewer().refresh();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void init(IViewPart view) {
	}
}
