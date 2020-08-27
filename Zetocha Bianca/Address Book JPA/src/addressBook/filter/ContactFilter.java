package addressBook.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import addressBook.tableviewer.model.Contact;

public class ContactFilter extends ViewerFilter {

    private String searchString;
    
    public void setSearchText(String s) {
        this.searchString = ".*" + s + ".*";
    }

    @Override
    public boolean select(Viewer viewer,
            Object parentElement,
            Object element) {
        if (searchString == null || searchString.length() == 0) {
            return true;
        }
        Contact p = (Contact) element;
        if (p.getFirstName().matches(searchString)) {
            return true;
        }
        if (p.getLastName().matches(searchString)) {
            return true;
        }

        return false;
    }

}