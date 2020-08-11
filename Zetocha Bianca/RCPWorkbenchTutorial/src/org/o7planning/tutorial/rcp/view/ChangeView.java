package org.o7planning.tutorial.rcp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.o7planning.tutorial.rcp.dialog.AddPersonDialog;
import org.o7planning.tutorial.rcp.view.model.ModelProvider;


	public class ChangeView extends ViewPart {
	    public static final String ID = "org.o7planning.tutorial.rcp.view.change";
	    private Button btnNewButton;

	    @Override
	    public void createPartControl(Composite parent) {
	        parent.setLayout(new GridLayout(1, false));

	        btnNewButton = new Button(parent, SWT.PUSH);
	        btnNewButton.addSelectionListener(new SelectionAdapter() {
	            @Override
	            public void widgetSelected(SelectionEvent e) {

	                ModelProvider persons = ModelProvider.INSTANCE;
	                AddPersonDialog dialog = new AddPersonDialog(getViewSite()
	                        .getShell());
	                dialog.open();
	                if (dialog.getPerson() != null) {
	                    persons.getPersons().add(dialog.getPerson());
	                    TableView part = (TableView) getViewSite().getPage()
	                            .findView(TableView.ID);
	                    // Updating the display in the view
	                    part.refresh();
	                }
	            }
	        });
	        btnNewButton.setText("Add");

	        Button btnDelete = new Button(parent, SWT.PUSH);
	        btnDelete.setText("Delete");

	    }

	    @Override
	    public void setFocus() {
	        btnNewButton.setFocus();
	    }
}

