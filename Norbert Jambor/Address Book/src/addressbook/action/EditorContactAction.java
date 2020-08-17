package addressbook.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import addressbook.editor.CreateContact;
import addressbook.view.ContactsView;

public class EditorContactAction implements IViewActionDelegate {
	
	private ContactsView contactsView;

	@Override
	public void run(IAction action) {
		CreateContact.openEditor(contactsView.firstElement());
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void init(IViewPart view) {
		contactsView = (ContactsView) view;
	}
}
