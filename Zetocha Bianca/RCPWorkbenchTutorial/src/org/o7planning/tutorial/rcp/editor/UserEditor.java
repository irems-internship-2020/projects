package org.o7planning.tutorial.rcp.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

public class UserEditor extends EditorPart {

public static final String ID = "org.wikiict.tutorial.rcp.editor.user";
 private Text text;
 private Text text_1;

 public UserEditor() {

 }

 @Override
 public void doSave(IProgressMonitor monitor) {

 }

 @Override
 public void doSaveAs() {

 }

 /**
  * Important!!!
  */
 @Override
 public void init(IEditorSite site, IEditorInput input)
         throws PartInitException {
     if (!(input instanceof UserEditorInput)) {
         throw new PartInitException("Invalid Input: Must be "
                 + UserEditorInput.class.getName());
     }
     setSite(site);
     setInput(input);
 }

 @Override
 public boolean isDirty() {
     return false;
 }

 @Override
 public boolean isSaveAsAllowed() {
     return false;
 }

 @Override
 public void createPartControl(Composite parent) {
     // Add Code.
     // If you want to design with WindowBuilder Designer
     // Change code like:  (Important!!!)
	
	    GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		parent.setLayout(gridLayout);
		GridData gridData = new GridData(GridData.FILL, GridData.CENTER, true, false);
		gridData.horizontalSpan = 2;
		parent.setLayoutData(gridData);
		
		new Label(parent, SWT.NONE).setText("UserName:");
		Text userName = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userName.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
		
		new Label(parent, SWT.NONE).setText("Email:");
		Text userEmail = new Text(parent, SWT.SINGLE | SWT.BORDER);
		userEmail.setLayoutData(new GridData(GridData.FILL, GridData.CENTER, true, false));
	
 }

 @Override
 public void setFocus() {

 }
}