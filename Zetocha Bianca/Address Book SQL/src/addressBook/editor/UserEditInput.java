package addressBook.editor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

public class UserEditInput implements IEditorInput {

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
	       return "User Edit";
	   }

	   @Override
	   public IPersistableElement getPersistable() {
	       return null;
	   }

	   @Override
	   public String getToolTipText() {
	       return "User Edit";
	   }

	}
