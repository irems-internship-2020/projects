package rcpworkbenchtutorial.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;

public class LabelProvider implements ITableLabelProvider {
	
    private List<TableViewerColumn> columnList = new ArrayList<TableViewerColumn>();
	
	LabelProvider(final List<TableViewerColumn> columnList){
		this.columnList = columnList;
	}

	@Override
	public void addListener(ILabelProviderListener listener) {		
	}

	@Override
	public void dispose() {		
	}

	@Override
	public boolean isLabelProperty(Object element, String property) {
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener listener) {		
	}

	@Override
	public Image getColumnImage(Object element, int columnIndex) {
		return null;
	}

	@Override
	public String getColumnText(Object element, int columnIndex) {
		if(element instanceof Person) {
			Person person = (Person) element;
		switch (columnList.get(columnIndex).getColumn().getText())
	    {
	    case "First name":
		return person.getFirstName();
		
	    case "Last name":
		return person.getLastName();
		
	    case "Gender":
		return person.getGender();
		
	    case "Married":
		return "1";
		
		default:
			throw new IllegalArgumentException("Not implemented");
	    }
	}
		return null;
	}
}
