package rcpworkbenchtutorial.comm;

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

import rcpworkbenchtutorial.view.FilterView;
import rcpworkbenchtutorial.view.ModelProvider;
import rcpworkbenchtutorial.view.Person;

public class DeletePerson extends AbstractHandler {
    @SuppressWarnings("unchecked")
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        IWorkbenchPage page = window.getActivePage();
        FilterView view = (FilterView) page.findView(FilterView.ID);
        ISelection selection = view.getSite().getSelectionProvider()
                .getSelection();

        if (selection != null && selection instanceof IStructuredSelection) {
            List<Person> persons = ModelProvider.INSTANCE.getPersons();
            IStructuredSelection sel = (IStructuredSelection) selection;

            for (Iterator<Person> iterator = sel.iterator(); iterator.hasNext();) {
                Person person = iterator.next();
                persons.remove(person);
            }
            view.getViewer().refresh();
        }
        return null;
    }
}