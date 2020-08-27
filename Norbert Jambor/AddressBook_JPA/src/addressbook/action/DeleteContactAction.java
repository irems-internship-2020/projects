package addressbook.action;

import java.util.Iterator;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import addressbook.jpa.JpaOperations;
import addressbook.model.Contact;
import addressbook.view.ContactsView;

public class DeleteContactAction implements IViewActionDelegate {
		
	//private DataBaseOperations dataBase = new DataBaseOperations();
	private JpaOperations jpaOperations = new JpaOperations();

	@Override
	public void run(IAction action) {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ContactsView view = (ContactsView) activePage.findView(ContactsView.ID);
		ISelection selection = view.getSite().getSelectionProvider().getSelection();
		
		if (selection != null && selection instanceof IStructuredSelection) {
			//List<Contact> persons = ModelProviderEnum.INSTANCE.getContacts();
			IStructuredSelection sel = (IStructuredSelection) selection;

			for (@SuppressWarnings("unchecked")
			Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
				Contact contact = iterator.next();
				jpaOperations.deleteJpa(contact);
				jpaOperations.closeTransaction();
				try {
					//ResultSet result = statement.executeQuery(dataBase.loadDB());
					//dataBase.deleteDB(contact);
					view.viewer.setInput(jpaOperations.loadJpa());
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
