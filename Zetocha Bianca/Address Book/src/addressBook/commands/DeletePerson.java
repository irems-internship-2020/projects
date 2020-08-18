package addressBook.commands;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.handlers.HandlerUtil;

import Address.Book.Views.ContactView;
import addressBook.tableviewer.model.Contact;
import addressBook.tableviewer.model.ModelProvider;

public class DeletePerson extends AbstractHandler {
    @SuppressWarnings("unchecked")
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        IWorkbenchPage page = window.getActivePage();
        ContactView view = (ContactView) page.findView(ContactView.ID);
        ISelection selection = view.getSite().getSelectionProvider()
                .getSelection();

        if (selection != null && selection instanceof IStructuredSelection) {
            List<Contact> persons = ModelProvider.INSTANCE.getContacts();
            IStructuredSelection sel = (IStructuredSelection) selection;

            for (Iterator<Contact> iterator = sel.iterator(); iterator.hasNext();) {
                Contact person = iterator.next();
                persons.remove(person);
            }
            view.getViewer().refresh();
        }
        return null;
    }
}
