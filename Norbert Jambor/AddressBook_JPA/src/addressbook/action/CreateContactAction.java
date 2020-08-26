package addressbook.action;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import addressbook.editor.Editor;
import addressbook.view.ContactsView;

public class CreateContactAction implements IViewActionDelegate {
	
	@SuppressWarnings("unused")
	private ContactsView contactsView;

	@Override
	public void run(IAction action) {
		Editor.openCreateEditor();
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
	}

	@Override
	public void init(IViewPart view) {
		contactsView = (ContactsView) view;
	}
}
