package rcpworkbenchtutorial.filter;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import rcpworkbenchtutorial.view.Person;

public class PersonFilter extends ViewerFilter {

    private String searchString;
    
    public void setSearchText(String s) {
        // ensure that the value can be used for matching
        this.searchString = ".*" + s + ".*";
    }

    @Override
    public boolean select(Viewer viewer,
            Object parentElement,
            Object element) {
        if (searchString == null || searchString.length() == 0) {
            return true;
        }
        Person p = (Person) element;
        if (p.getFirstName().matches(searchString)) {
            return true;
        }
        if (p.getLastName().matches(searchString)) {
            return true;
        }

        return false;
    }

}