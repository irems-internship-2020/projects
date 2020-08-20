package addressBook.comparator;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;

import addressBook.tableviewer.model.Contact;

public class MyViewerComparator extends ViewerComparator {
    private int propertyIndex;
    private static final int DESCENDING = 1;
    private int direction = DESCENDING;

    public MyViewerComparator() {
        this.propertyIndex = 0;
        direction = DESCENDING;
    }

    public int getDirection() {
        return direction == 1 ? SWT.DOWN : SWT.UP;
    }

    public void setColumn(int column) {
        if (column == this.propertyIndex) {
            direction = 1 - direction;
        } else {
            this.propertyIndex = column;
            direction = DESCENDING;
        }
    }

    @Override
    public int compare(Viewer viewer, Object e1, Object e2) {
        Contact p1 = (Contact) e1;
        Contact p2 = (Contact) e2;
        int rc = 0;
        switch (propertyIndex) {
        case 0:
            rc = p1.getId().compareTo(p2.getId());
            break;
        case 1:
            rc = p1.getFirstName().compareTo(p2.getFirstName());
            break;
        case 2:
            rc = p1.getLastName().compareTo(p2.getLastName());
            break;
        case 3:
            rc = p1.getAddress().getStreet().compareTo(p2.getAddress().getStreet());
            break;
        case 4:
            rc = p1.getPhoneNumber().compareTo(p2.getPhoneNumber());
            break;
        case 5:
            rc = p1.getEmail().compareTo(p2.getEmail());
            break;
        case 6:
            rc = p1.getAddress().getCountry().compareTo(p2.getAddress().getCountry());
            break;
        case 7:
            rc = p1.getAddress().getCity().compareTo(p2.getAddress().getCity());
            break;
        case 8:
            rc = p1.getAddress().getPostalCode().compareTo(p2.getAddress().getPostalCode());
            break;
        default:
            rc = 0;
        }
        if (direction == DESCENDING) {
            rc = -rc;
        }
        return rc;
    }

}