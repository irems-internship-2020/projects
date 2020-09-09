package rcpaddressbookapp;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;

import addressBook.Views.ContactView;
import addressBook.editor.Editor;

public class EditAction implements IViewActionDelegate {
	
	private ContactView contactsView;

	@Override
	public void run(IAction action) {
		try {
			Editor.openEditor(contactsView.firstElement());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(IViewPart view) {
		contactsView = (ContactView) view;
	}

}
