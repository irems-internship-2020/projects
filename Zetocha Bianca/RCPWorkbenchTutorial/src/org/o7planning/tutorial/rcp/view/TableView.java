package org.o7planning.tutorial.rcp.view;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.ViewPart;
import org.o7planning.tutorial.rcp.view.edit.FirstNameEditingSupport;
import org.o7planning.tutorial.rcp.view.edit.GenderEditingSupport;
import org.o7planning.tutorial.rcp.view.edit.LastNameEditingSupport;
import org.o7planning.tutorial.rcp.view.edit.MarriedEditingSupport;
import org.o7planning.tutorial.rcp.view.filter.PersonFilter;
import org.o7planning.tutorial.rcp.view.model.ColumTitle;
import org.o7planning.tutorial.rcp.view.model.ContactsLabelProvider;
import org.o7planning.tutorial.rcp.view.model.ModelProvider;
import org.o7planning.tutorial.rcp.view.model.Person;
import org.o7planning.tutorial.rcp.view.sorter.MyViewerComparator;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

public class TableView extends ViewPart {
	
	public static final String ID = "org.o7planning.tutorial.rcp.view.table";

	private TableViewer viewer;
	private MyViewerComparator comparator;
	private PersonFilter filter;

	// We use icons
	private static final Image CHECKED = getImageDescriptor("icons/checked.gif").createImage();
	private static final Image UNCHECKED = getImageDescriptor("icons/unchecked.gif").createImage();
	List<TableViewerColumn> columnList = new ArrayList<TableViewerColumn>();

	private Text searchText;
	public void createPartControl(Composite parent) {
        GridLayout layout = new GridLayout(2, false);
        parent.setLayout(layout);
        Label searchLabel = new Label(parent, SWT.NONE);
        searchLabel.setText("Search: ");
        searchText = new Text(parent, SWT.BORDER | SWT.SEARCH);
        searchText.setLayoutData(new GridData(GridData.GRAB_HORIZONTAL
                | GridData.HORIZONTAL_ALIGN_FILL));
        createViewer(parent);
        // Set the sorter for the table
        comparator = new MyViewerComparator();
        viewer.setComparator(comparator);

        // New to support the search
        searchText.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent ke) {
                filter.setSearchText(searchText.getText());
                viewer.refresh();
                getSite().setSelectionProvider(viewer);
            }
         

        });
        filter = new PersonFilter();
        viewer.addFilter(filter);
	}
	   public void refresh() {
           viewer.refresh();
       } 

	private void createViewer(Composite parent) {
		viewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL | SWT.FULL_SELECTION | SWT.BORDER);
		createColumns(parent, viewer);
		final Table table = viewer.getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		viewer.setContentProvider(new ArrayContentProvider());
		// Get the content for the viewer, setInput will call getElements in the
		// contentProvider
		viewer.setInput(ModelProvider.INSTANCE.getPersons());
		// make the selection available to other views
		getSite().setSelectionProvider(viewer);
		// Set the sorter for the table

		// Layout the viewer
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

	// This will create the columns for the table


		 private void createColumns(final Composite parent, final TableViewer viewer) {
	        String[] titles = { "First name", "Last name", "Gender", "Married" };
	        int[] bounds = { 100, 100, 100, 100 };

	        // First column is for the first name
	        TableViewerColumn col = createTableViewerColumn(titles[0], bounds[0], 0);
	        col.setLabelProvider(new CellLabelProvider() {
	            @Override
	            public void update(ViewerCell cell) {
	                cell.setText(((Person) cell.getElement()).getFirstName());
	            }
	        });
	        col.setEditingSupport(new FirstNameEditingSupport(viewer));

	        // Second column is for the last name
	        col = createTableViewerColumn(titles[1], bounds[1], 1);
	        col.setLabelProvider(new CellLabelProvider() {
	            @Override
	            public void update(ViewerCell cell) {
	                cell.setText(((Person) cell.getElement()).getLastName());
	            }
	        });
	        col.setEditingSupport(new LastNameEditingSupport(viewer));

	        // now the gender
	        col = createTableViewerColumn(titles[2], bounds[2], 2);
	        col.setLabelProvider(new ColumnLabelProvider() {
	            @Override
	            public String getText(Object element) {
	                Person p = (Person) element;
	                return p.getGender();
	            }
	        });
	        col.setEditingSupport(new GenderEditingSupport(viewer));
	        // now the status married
	        col = createTableViewerColumn(titles[3], bounds[3], 3);
	        col.setLabelProvider(new ColumnLabelProvider() {
	            @Override
	            public String getText(Object element) {
	                return null;
	            }

	            @Override
	            public Image getImage(Object element) {
	                if (((Person) element).isMarried()) {
	                    return CHECKED;
	                } else {
	                    return UNCHECKED;
	                }
	            }
	        });
	        col.setEditingSupport(new MarriedEditingSupport(viewer));


	}

	private TableViewerColumn createTableViewerColumn(String title, int bound, final int colNumber) {
		final TableViewerColumn viewerColumn = new TableViewerColumn(viewer, SWT.NONE);
		final TableColumn column = viewerColumn.getColumn();
		column.setText(title);
		column.setWidth(bound);
		column.setResizable(true);
		column.setMoveable(true);
		column.addSelectionListener(getSelectionAdapter(column, colNumber));
		return viewerColumn;
	}

	private SelectionAdapter getSelectionAdapter(final TableColumn column, final int index) {
		SelectionAdapter selectionAdapter = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				comparator.setColumn(index);
				int dir = comparator.getDirection();
				viewer.getTable().setSortDirection(dir);
				viewer.getTable().setSortColumn(column);
				viewer.refresh();
			}
		};
		return selectionAdapter;
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	private static ImageDescriptor getImageDescriptor(String file) {
		// assume that the current class is called View.java
		Bundle bundle = FrameworkUtil.getBundle(Viewer.class);
		URL url = FileLocator.find(bundle, new Path("icons/" + file), null);
		return ImageDescriptor.createFromURL(url);

	}
}
