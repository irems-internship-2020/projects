package rcpworkbenchtutorial.editor;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

public class UserEditor extends EditorPart {

 public static final String ID = "rcpworkbenchtutorial.editor.user";

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
     parent.setLayout(new FillLayout());
     Composite body = new Composite(parent, SWT.NONE);
 }

 @Override
 public void setFocus() {

 }

}