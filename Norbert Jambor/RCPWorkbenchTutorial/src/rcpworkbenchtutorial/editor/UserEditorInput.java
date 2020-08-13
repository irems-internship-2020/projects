package rcpworkbenchtutorial.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class UserEditorInput implements IEditorInput {

   @SuppressWarnings({ "rawtypes", "unchecked" })
   @Override
   public Object getAdapter(Class adapter) {
       return null;
   }

   @Override
   public boolean exists() {
       return false;
   }

   @Override
   public ImageDescriptor getImageDescriptor() {
       return null;
   }

   @Override
   public String getName() {
       return "User Editor";
   }

   @Override
   public IPersistableElement getPersistable() {
       return null;
   }

   @Override
   public String getToolTipText() {
       return "User Editor";
   }

}