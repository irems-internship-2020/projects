package test2;


import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

   public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
       super(configurer);
   }

   public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
       return new ApplicationActionBarAdvisor(configurer);
   }
   
   public void preWindowOpen() {
       IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
       configurer.setInitialSize(new Point(400, 300));
       
       // Show MenuBar
       configurer.setShowMenuBar(true);
       // Show CoolBar.
       configurer.setShowCoolBar(true);
       // Show Status Line.
       configurer.setShowStatusLine(true);
       // Show PerspectiveBar
       configurer.setShowPerspectiveBar(true);
       // Show FastViewBars
       configurer.setShowFastViewBars(true);
       // Show ProgressIndicator
       configurer.setShowProgressIndicator(true);
       
       configurer.setTitle("Hello RCP"); //$NON-NLS-1$
   }
}