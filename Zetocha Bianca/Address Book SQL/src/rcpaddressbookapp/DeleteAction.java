package rcpaddressbookapp;

import java.util.Iterator;

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import Address.Book.Views.ContactView;
import addressBook.database.DataBaseCRUD;
import addressBook.editor.Editor;
import addressBook.tableviewer.model.Contact;
import addressBook.tableviewer.model.ModelProvider;


public class DeleteAction implements IViewActionDelegate {
	private DataBaseCRUD dataBase = new DataBaseCRUD(); 
	@Override
	public void run(IAction action) {
		IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		ContactView view = (ContactView) activePage.findView(ContactView.ID);
		ISelection selection = view.getSite().getSelectionProvider().getSelection();
		
		if (selection != null && selection instanceof IStructuredSelection) {
			//List<Contact> persons = ModelProvider.INSTANCE.getContacts();
			IStructuredSelection sel = (IStructuredSelection) selection;

			for (@SuppressWarnings("unchecked")
			Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
				Contact person = iterator.next();
				//persons.remove(person);
				try {
					//ResultSet result = statement.executeQuery(dataBase.loadDB());
					dataBase.dataBaseDelete(person);
					view.setTableInput();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			view.getViewer().refresh();
		}
	}
	

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
	}

}