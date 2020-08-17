package rcpworkbenchtutorial.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.part.ViewPart;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

public class TableView extends ViewPart {
    public static final String ID = "rcpworkbenchtutorial.view.table";

    private TableViewer viewer;
    
    private TitleProvider titleProvider;
    
    List<TableViewerColumn> columnList = new ArrayList<TableViewerColumn>();
    
    // We use icons
    private static final Image CHECKED = getImageDescriptor(
            "icons/check.png").createImage();
    private static final Image UNCHECKED = getImageDescriptor(
            "icons/uncheck.png").createImage();
    
	public static ImageDescriptor getImageDescriptor(String file) {
	    Bundle bundle = FrameworkUtil.getBundle(TableView.class);
	    URL url = FileLocator.find(bundle, new org.eclipse.core.runtime.Path("icons/" + file), null);
	    return ImageDescriptor.createFromURL(url);
	}

    public void createPartControl(Composite parent) {
        GridLayout layout = new GridLayout(2, false);
        parent.setLayout(layout);
        Label searchLabel = new Label(parent, SWT.NONE);
        searchLabel.setText("Search: ");
        final Text searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
        searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL));
        createViewer(parent);
        
    }

    private void createViewer(Composite parent) {
        viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
                | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
        createColumns(parent, viewer);
        final Table table = viewer.getTable();
        table.setHeaderVisible(true);
        table.setLinesVisible(true);

        viewer.setContentProvider(new ArrayContentProvider());

        viewer.setInput(ModelProvider.INSTANCE.getPersons());
        getSite().setSelectionProvider(viewer);

        GridData gridData = new GridData();
        gridData.verticalAlignment = GridData.FILL;
        gridData.horizontalSpan = 2;
        gridData.grabExcessHorizontalSpace = true;
        gridData.grabExcessVerticalSpace = true;
        gridData.horizontalAlignment = GridData.FILL;
        viewer.getControl().setLayoutData(gridData);
    }

    public TableViewer getViewer() {
        return viewer;
    }

    private void createColumns(final Composite parent, final TableViewer tableViewer) {
        int counter = 0;

        for(TitleProvider title : titleProvider.values()) {
        	TableViewerColumn col = createTableViewerColumn(title.getText(), 100, counter);
            columnList.add(col);
            System.out.println(columnList.size());
            counter++;
            System.out.println(counter);
        }
        
    	tableViewer.setLabelProvider(new LabelProvider(columnList));
    }

    private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
        final TableViewerColumn viewerColumn = new TableViewerColumn(viewer,
                SWT.NONE);
        final TableColumn column = viewerColumn.getColumn();
        column.setText(title);
        column.setWidth(bound);
        column.setResizable(true);
        column.setMoveable(true);
        return viewerColumn;

    }

    public void setFocus() {
        viewer.getControl().setFocus();
    }
}